package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.GameObject;

public class Entity extends GameObject {

    private boolean jump;
    private boolean touchingGround;

    private float xSpeed;
    private float ySpeed;

    private final float defaultXSpeed;
    private final float defaultYSpeed;

    TextureRegion[][] animationFrames;
    Animation<TextureRegion> animation;
    float timeElapsed;

    public Entity(int x, int y, Texture texture, float scale) {
        super(x, y, texture, scale);

        this.defaultXSpeed = 100f*scale;
        this.defaultYSpeed = 200f*scale;

        xSpeed = 0;

        this.jump = false;

        animationFrames = TextureRegion.split(super.getTexture(), 14, 45);
        animation = new Animation<TextureRegion>(1f/4f, animationFrames[0]);
    }

    public void update(float deltaTime) {
        timeElapsed += deltaTime;

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
    }

    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(timeElapsed, true), super.getX(), super.getY(), 14*super.getScale(), 45*super.getScale());
    }
}
