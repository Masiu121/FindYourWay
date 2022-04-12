package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

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
        acceleration = 0.3f;
        deceleration = 0.15f;
        speed = 0;
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            toggleDoors();
        }

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

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(speed - acceleration < -maxSpeed)
                speed = -maxSpeed;
            else
                speed -= acceleration;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(speed + acceleration > maxSpeed)
                speed = maxSpeed;
            else
                speed += acceleration;
        }

        if(speed != 0 && !Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(speed > 0) {
                if(speed - deceleration < 0)
                    speed = 0;
                else
                    speed -= deceleration;
            } else {
                if(speed + deceleration > 0)
                    speed = 0;
                else
                    speed += deceleration;
            }
        }

        move(speed*deltaTime, 0);

        if(player != null) {
            player.move(speed*deltaTime, 0f);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(GameData.METRO_DOOR_LEFT, getX()+39-doorOffset, getY()+32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX()+51+doorOffset, getY()+32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX()+120-doorOffset, getY()+32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX()+132+doorOffset, getY()+32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX()+200-doorOffset, getY()+32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX()+212+doorOffset, getY()+32);

        batch.draw(GameData.METRO_DOOR_LEFT, getX()+281-doorOffset, getY()+32);
        batch.draw(GameData.METRO_DOOR_RIGHT, getX()+293+doorOffset, getY()+32);

        batch.draw(super.getTexture(), getX(), getY());
    }

    public void setPlayer(Player player) {
        this.player = player;
        if(this.player != null)
            this.player.move(32, 32);
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
}
