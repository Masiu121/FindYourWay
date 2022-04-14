package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.GameObject;

public class Entity extends GameObject {
    private boolean animate;

    private TextureRegion[][] animationFrames;
    private Animation<TextureRegion> animation;
    private float timeElapsed;

    private boolean moving;

    private int frameWidth;
    private int frameHeight;

    private float xSpeed;
    private float ySpeed;

    private boolean animationPaused;

    private final float defaultXSpeed;
    private final float defaultYSpeed;
    private boolean looping;

    public Entity(int x, int y, Texture texture, float scale, int frames, boolean looping, FindYourWay game) {
        super(x, y, texture, scale, game);

        this.moving = false;

        this.defaultXSpeed = 50f*scale;
        this.defaultYSpeed = 200f*scale;

        this.animationPaused = false;


        xSpeed = 0;

        this.animate = true;

        this.frameWidth = getTexture().getWidth()/frames;
        this.frameHeight = getTexture().getHeight();

        animationFrames = TextureRegion.split(super.getTexture(), frameWidth, frameHeight);
        animation = new Animation<TextureRegion>(1f / 4f, animationFrames[0]);

        super.setSize(animationFrames[0][0].getRegionWidth(), animationFrames[0][0].getRegionHeight());

        this.looping = looping;
    }

    public Entity(int x, int y, Texture texture, float scale, FindYourWay game) {
        super(x, y, texture, scale, game);

        this.moving = false;

        this.animate = false;

        this.defaultXSpeed = 80f*scale;
        this.defaultYSpeed = 150f*scale;

        this.xSpeed = 0;

        animationPaused = true;
    }

    public void update(float deltaTime) {
        if(!animationPaused) {
            timeElapsed += deltaTime;
        }

        if(this.getxSpeed() != 0 || this.getySpeed() != 0) {
            moving = true;
        } else {
            moving = false;
        }

        moveBy(getxSpeed()*deltaTime, getySpeed()*deltaTime);
    }

    public void draw(SpriteBatch batch) {
        if(animate && animation != null) {
            batch.draw(animation.getKeyFrame(timeElapsed, this.looping), super.getX(), super.getY(), frameWidth * super.getScale(), frameHeight * super.getScale());
        } else {
            batch.draw(getTexture(), getX(), getY());
        }
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public void setAnimation(Animation<TextureRegion> animation, int frameWidth, int frameHeight, boolean looping) {
        this.animation = animation;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.looping = looping;
    }

    public boolean isMoving() {
        return moving;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public float getDefaultXSpeed() {
        return defaultXSpeed;
    }

    public float getDefaultYSpeed() {
        return defaultYSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public float getTimeElapsed() {
        return timeElapsed;
    }

    public void pauseAnimation() {
        animationPaused = true;
    }

    public void resumeAnimation() {
        animationPaused = false;
    }

    public void setTimeElapsed(float timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public void setAnimationPaused(boolean animationPaused) {
        this.animationPaused = animationPaused;
    }
}
