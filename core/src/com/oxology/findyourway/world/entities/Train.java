package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

import java.util.Random;

public class Train extends Entity {
    private boolean doorsMoving;
    private boolean doorsOpened;
    private boolean doorsClosed;

    private final float doorMaxOffset;
    private float doorOffset;

    private final float doorsOpenDefaultSpeed;
    private float doorsOpenSpeed;

    private final float maxSpeed;
    private final float acceleration;
    private final float deceleration;
    private float speed;

    private Player player;

    Sound startSound;
    Sound stopSound;

    //NPC vars
    private int state;
    /* 0 - idling
     * 1 - accelerating
     * 2 - decelerating
     */

    private int direction;
    /* 0 - right
     * 1 - left
     */

    private float timeElapsed;
    private boolean trainStopped;

    public Train(int x, int y, float scale, FindYourWay game) {
        super(x, y, GameData.METRO_TRAIN, scale, game);

        this.doorsMoving = false;
        this.doorsOpened = false;
        this.doorsClosed = true;

        this.doorMaxOffset = 12;
        this.doorsOpenDefaultSpeed = 15f;

        this.doorOffset = 0;
        this.doorsOpenSpeed = 0;

        maxSpeed = 800f;
        acceleration = 30f;
        deceleration = 10f;
        speed = 0;
    }

    public Train(FindYourWay game, int direction) {
        super(0, 8, GameData.METRO_TRAIN, 1f, game);

        Random random = new Random();

        if(direction == 1)
            super.setX(600 + random.nextInt(20));
        else
            super.setX(-700 + random.nextInt(20));

        this.doorsMoving = false;
        this.doorsOpened = false;
        this.doorsClosed = true;

        this.doorMaxOffset = 12;
        this.doorsOpenDefaultSpeed = 15f;

        this.doorOffset = 0;
        this.doorsOpenSpeed = 0;

        maxSpeed = 800f;
        acceleration = 30f;
        deceleration = 10f;

        if(direction == 1)
            speed = -maxSpeed/7f;
        else
            speed = maxSpeed/7f;

        state = 2;
        this.direction = direction;

        trainStopped = false;

        startSound = Gdx.audio.newSound(GameData.TRAIN_START);
        stopSound = Gdx.audio.newSound(GameData.TRAIN_STOP);
        stopSound.play(0.2f);
    }

    public void update(float deltaTime) {
        doorsMoving = doorsOpenSpeed != 0;

        if(doorsMoving) {
            if(doorsOpenSpeed > 0) {
                if (doorOffset + doorsOpenSpeed * deltaTime > doorMaxOffset) {
                    doorOffset = doorMaxOffset;
                    doorsOpenSpeed = 0;
                    doorsOpened = true;
                } else {
                    doorOffset += doorsOpenSpeed * deltaTime;
                }
            } else {
                if (doorOffset + doorsOpenSpeed * deltaTime < 0) {
                    doorOffset = 0;
                    doorsOpenSpeed = 0;
                    doorsClosed = true;
                } else {
                    doorOffset += doorsOpenSpeed * deltaTime;
                }
            }
        }

        moveBy(speed*deltaTime, 0);

        if(player != null) {
            player.moveBy(speed*deltaTime, 0f);
        }

        switch (state) {
            case 0:
                if(!trainStopped)
                    timeElapsed = 0;

                trainStopped = true;
                idle(deltaTime);
                break;
            case 1:
                accelerate(deltaTime);
                break;
            case 2:
                decelerate(deltaTime);
                break;
        }

        timeElapsed += deltaTime;
    }

    public void draw(SpriteBatch batch) {
        if(direction == 1) {
            drawRightDoors(batch);
            drawLeftDoors(batch);
        } else {
            drawLeftDoors(batch);
            drawRightDoors(batch);
        }

        batch.draw(super.getTexture(), getX(), getY());
    }

    public void drawLeftDoors(SpriteBatch batch) {
        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 39, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 51, getY() + 32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 120, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 132, getY() + 32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 200, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 212, getY() + 32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 281, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 293, getY() + 32);
    }

    public void drawRightDoors(SpriteBatch batch) {
        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 39 - doorOffset, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 51 + doorOffset, getY() + 32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 120 - doorOffset, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 132 + doorOffset, getY() + 32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 200 - doorOffset, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 212 + doorOffset, getY() + 32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX() + 281 - doorOffset, getY() + 32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX() + 293 + doorOffset, getY() + 32);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void toggleDoors() {
        if(!doorsMoving) {
            if(doorsClosed) {
                doorsOpenSpeed = doorsOpenDefaultSpeed;
                doorsClosed = false;
            } else if(doorsOpened) {
                doorsOpenSpeed = -doorsOpenDefaultSpeed;
                doorsOpened = false;
            }
        }
    }

    private void decelerate(float deltaTime) {
        if(speed > 0) {
            if(speed - deceleration*deltaTime < 0) {
                speed = 0;
                state = 0;
            } else {
                speed -= deceleration * deltaTime;
            }
        } else {
            if (speed + deceleration * deltaTime > 0) {
                speed = 0;
                state = 0;
            }
            else {
                speed += deceleration * deltaTime;
            }
        }
    }

    private void accelerate(float deltaTime) {
        if(direction == 1) {
            if (speed - acceleration * deltaTime < -maxSpeed)
                speed = -maxSpeed;
            else
                speed -= acceleration * deltaTime;
        } else {
            if (speed + acceleration * deltaTime > maxSpeed)
                speed = maxSpeed;
            else
                speed += acceleration * deltaTime;
        }
    }

    private void idle(float deltaTime) {
        if(timeElapsed >= 8) {
            startSound.play(0.2f);
            state = 1;
        } else if(timeElapsed >= 7) {
            doorsOpenSpeed = -doorsOpenDefaultSpeed;
            doorsOpened = false;
        } else if(timeElapsed >= 1) {
            doorsOpenSpeed = doorsOpenDefaultSpeed;
            doorsClosed = false;
        }
    }

    public int getDirection() {
        return direction;
    }

    public boolean areDoorsOpened() {
        return doorsOpened;
    }
}
