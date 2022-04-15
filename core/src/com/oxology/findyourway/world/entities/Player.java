package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

public class Player extends Entity {
    public boolean jump;
    private boolean touchingGround;

    TextureRegion[][] idleAnimationFrames1;
    Animation<TextureRegion> idleAnimation1;

    TextureRegion[][] idleAnimationFrames2;
    Animation<TextureRegion> idleAnimation2;

    TextureRegion[][] walkAnimationFrames1;
    Animation<TextureRegion> walkAnimation1;

    TextureRegion[][] walkAnimationFrames2;
    Animation<TextureRegion> walkAnimation2;

    private boolean ticket;

    private int animationStatus;
    /*
    0 - idle
    1 - walk
    2 - run
    3 - jump
     */

    private int direction;
    /*
    0 - right
    1 - left
     */

    private int baseY;

    public Player(int x, Texture texture, float scale, FindYourWay game, int baseY) {
        super(x, baseY, texture, scale, 9, true, game);

        this.animationStatus = 0;
        this.direction = 0;

        this.baseY = baseY;

        createAnimations();

        this.jump = false;
    }

    public void createAnimations() {
        int tileWidth = 0;
        int tileHeight = 0;
        Texture idle1 = null;
        Texture idle2 = null;
        Texture walk1 = null;
        Texture walk2 = null;

        switch (game.mainCharacter) {
            case 0:
                tileWidth = 14;
                tileHeight = 45;

                idle1 = GameData.MAIN_CHAR_1_IDLE_1;
                idle2 = GameData.MAIN_CHAR_1_IDLE_2;

                walk1 = GameData.MAIN_CHAR_1_WALK_1;
                walk2 = GameData.MAIN_CHAR_1_WALK_2;
                break;
            case 1:
                tileWidth = 13;
                tileHeight = 45;

                idle1 = GameData.MAIN_CHAR_2_IDLE_1;
                idle2 = GameData.MAIN_CHAR_2_IDLE_2;

                walk1 = GameData.MAIN_CHAR_2_WALK_1;
                walk2 = GameData.MAIN_CHAR_2_WALK_2;
                System.out.println("Adsaaaaaaaaaaaaaaaaaaaaaaaaaa");
                break;
            case 2:
                tileWidth = 13;
                tileHeight = 45;

                idle1 = GameData.MAIN_CHAR_3_IDLE_1;
                idle2 = GameData.MAIN_CHAR_3_IDLE_2;

                walk1 = GameData.MAIN_CHAR_3_WALK_1;
                walk2 = GameData.MAIN_CHAR_3_WALK_2;
                break;
        }

        this.idleAnimationFrames1 = TextureRegion.split(idle1, tileWidth, tileHeight);
        this.idleAnimation1 = new Animation<TextureRegion>(1f/4f, idleAnimationFrames1[0]);

        this.idleAnimationFrames2 = TextureRegion.split(idle2, tileWidth, tileHeight);
        this.idleAnimation2 = new Animation<TextureRegion>(1f/4f, idleAnimationFrames2[0]);

        this.walkAnimationFrames1 = TextureRegion.split(walk1, 20, tileHeight);
        this.walkAnimation1 = new Animation<TextureRegion>(1f/8f, walkAnimationFrames1[0]);

        this.walkAnimationFrames2 = TextureRegion.split(walk2, 20, tileHeight);
        this.walkAnimation2 = new Animation<TextureRegion>(1f/8f, walkAnimationFrames2[0]);
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)/* && getX() >= ((MainGameScreen) game.getScreen()).getCamera().position.x-240/2f*/) {
            super.setxSpeed(-super.getDefaultXSpeed());
            direction = 0;
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)/* && getX()+getDefaultXSpeed()*deltaTime <= ((MainGameScreen) game.getScreen()).getCamera().position.x + 240*/) {
            super.setxSpeed(super.getDefaultXSpeed());
            direction = 1;
        } else {
            super.setxSpeed(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && touchingGround) {
            super.setySpeed(super.getDefaultYSpeed());
            this.jump = true;
        }

        if(super.getY() == baseY) {
            touchingGround = true;
        }

        if(jump) {
            touchingGround = false;
            super.setySpeed(super.getySpeed() - game.gravity);
            if(super.getY()+super.getySpeed()*deltaTime < baseY) {
                jump = false;
                move((int) super.getX(), baseY);
                super.setySpeed(0);
            }
        }

        if(super.isMoving()) {
            if(direction == 0) {
                setAnimation(walkAnimation1, 20, 45, true);
            } else if(direction == 1) {
                setAnimation(walkAnimation2, 20, 45, true);
            }
        } else {
            if(direction == 0) {
                setAnimation(idleAnimation1, 14, 45, true);
            } else if(direction == 1) {
                setAnimation(idleAnimation2, 14, 45, true);
            }
        }

        super.update(deltaTime);
    }

    public void setTicket(boolean ticket) {
        this.ticket = ticket;
    }

    public boolean isTicket() {
        return ticket;
    }
}
