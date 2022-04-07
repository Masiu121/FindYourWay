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
    private final boolean animate;

    private TextureRegion[][] animationFrames;
    private Animation<TextureRegion> animation;
    private float timeElapsed;

    private final int frameWidth;
    private final int frameHeight;

    public Entity(int x, int y, Texture texture, float scale, boolean animate, int frames) {
        super(x, y, texture, scale);

        this.animate = animate;

        this.frameWidth = getTexture().getWidth()/frames;
        this.frameHeight = getTexture().getHeight();

        if(animate) {
            animationFrames = TextureRegion.split(super.getTexture(), frameWidth, frameHeight);
            animation = new Animation<TextureRegion>(1f / 4f, animationFrames[0]);
        }
    }

    public void update(float deltaTime) {
        if(animate) {
            timeElapsed += deltaTime;
        }
    }

    public void draw(SpriteBatch batch) {
        if(animate) {
            batch.draw(animation.getKeyFrame(timeElapsed, true), super.getX(), super.getY(), frameWidth * super.getScale(), frameHeight * super.getScale());
        } else {
            batch.draw(getTexture(), getX(), getY());
        }
    }
}
