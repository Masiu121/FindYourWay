package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.FindYourWay;

public class Player extends Entity {
    private boolean jump;
    private boolean touchingGround;

    private float xSpeed;
    private float ySpeed;

    private final float defaultXSpeed;
    private final float defaultYSpeed;

    public Player(int x, int y, Texture texture, float scale) {
        super(x, y, texture, scale, true, 9);

        this.defaultXSpeed = 80f*scale;
        this.defaultYSpeed = 150f*scale;

        xSpeed = 0;

        this.jump = false;
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.xSpeed = -defaultXSpeed;
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.xSpeed = defaultXSpeed;
        } else {
            this.xSpeed = 0;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && touchingGround) {
            ySpeed = defaultYSpeed;
            this.jump = true;
        }

        if(super.getY() == 10) {
            touchingGround = true;
        }

        if(jump) {
            touchingGround = false;
            ySpeed = ySpeed - FindYourWay.GRAVITY;
            if(super.getY()+ySpeed*deltaTime < 10) {
                jump = false;
                move((int) super.getX(), 10);
                ySpeed = 0;
            }
        }

        move(xSpeed*deltaTime, ySpeed*deltaTime);

        super.update(deltaTime);
    }
}
