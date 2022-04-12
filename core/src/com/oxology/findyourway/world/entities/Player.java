package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

public class Player extends Entity {
    private boolean jump;
    private boolean touchingGround;

    TextureRegion[][] idleAnimationFrames1;
    Animation<TextureRegion> idleAnimation1;

    TextureRegion[][] idleAnimationFrames2;
    Animation<TextureRegion> idleAnimation2;

    TextureRegion[][] walkAnimationFrames1;
    Animation<TextureRegion> walkAnimation1;

    TextureRegion[][] walkAnimationFrames2;
    Animation<TextureRegion> walkAnimation2;



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

    public Player(int x, int y, Texture texture, float scale, FindYourWay game) {
        super(x, y, texture, scale, 9, true, game);

        this.animationStatus = 0;
        this.direction = 0;

        this.idleAnimationFrames1 = TextureRegion.split(GameData.MAIN_CHAR_IDLE_1, 14, 45);
        this.idleAnimation1 = new Animation<TextureRegion>(1f/4f, idleAnimationFrames1[0]);

        this.idleAnimationFrames2 = TextureRegion.split(GameData.MAIN_CHAR_IDLE_2, 14, 45);
        this.idleAnimation2 = new Animation<TextureRegion>(1f/4f, idleAnimationFrames2[0]);

        this.walkAnimationFrames1 = TextureRegion.split(GameData.MAIN_CHAR_WALK_1, 20, 45);
        this.walkAnimation1 = new Animation<TextureRegion>(1f/8f, walkAnimationFrames1[0]);

        this.walkAnimationFrames2 = TextureRegion.split(GameData.MAIN_CHAR_WALK_2, 20, 45);
        this.walkAnimation2 = new Animation<TextureRegion>(1f/8f, walkAnimationFrames2[0]);

        this.jump = false;
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)/*  && ((MainGameScreen) game.getScreen()).left_bg.getBgPositionX() <= getX()*/) {
            super.setxSpeed(-super.getDefaultXSpeed());
            direction = 0;
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)/* && ((MainGameScreen) game.getScreen()).right_bg.getBgPositionX() * 2 >= getX()+getWidth()*/) {
            super.setxSpeed(super.getDefaultXSpeed());
            direction = 1;
        } else {
            super.setxSpeed(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && touchingGround) {
            super.setySpeed(super.getDefaultYSpeed());
            this.jump = true;
        }

        if(super.getY() == 3) {
            touchingGround = true;
        }

        if(jump) {
            touchingGround = false;
            super.setySpeed(super.getySpeed() - game.gravity);
            if(super.getY()+super.getySpeed()*deltaTime < 3) {
                jump = false;
                move((int) super.getX(), 3);
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
}
