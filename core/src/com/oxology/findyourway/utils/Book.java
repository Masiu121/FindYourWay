package com.oxology.findyourway.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.world.entities.Entity;

public class Book extends Entity {
    TextureRegion[][] bookAnimationFrames;
    Animation<TextureRegion> bookAnimation;

    private float timeElapsed;

    public Book(int x, int y, Texture texture, float scale, FindYourWay game) {
        super(x, y, texture, scale, 9, true, game);
        this.bookAnimationFrames = TextureRegion.split(GameData.BOOK_OPEN_ANIMATION, 240, 135);
        this.bookAnimation = new Animation<TextureRegion>(1f/4f, bookAnimationFrames[0]);
        timeElapsed = 0;
    }

    public void update(float deltaTime){

        if(Gdx.input.isButtonJustPressed(Input.Keys.O)){
            animationStart = true;
        }

        if(animationStart){
            timeElapsed += deltaTime;
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(bookAnimation.getKeyFrame(timeElapsed, false), super.getX(), super.getY() , GameData.BOOK_OPEN_ANIMATION.getWidth() / 8f * getScale() , GameData.BOOK_OPEN_ANIMATION.getHeight() * getScale());

    }
}
