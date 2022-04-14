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

    TextureRegion[][] bookAnimationFrames2;
    Animation<TextureRegion> bookAnimation2;

    public Book(int x, int y, Texture texture, float scale, FindYourWay game) {
        super(x, y, texture, scale, 9, true, game);
        this.bookAnimationFrames = TextureRegion.split(GameData.BOOK_OPEN_ANIMATION, 240, 135);
        this.bookAnimation = new Animation<TextureRegion>(1f/4f, bookAnimationFrames[0]);

        this.bookAnimationFrames2 = TextureRegion.split(GameData.BOOK_NEXT_PAGE_ANIMATION, 240, 135);
        this.bookAnimation2 = new Animation<TextureRegion>(1f/4f, bookAnimationFrames2[0]);

        setAnimationPaused(true);
        setAnimation(bookAnimation, 240, 135, false);
    }

    public void update(float deltaTime){
        setAnimationPaused(false);

        // setTimeElapsed(0);

//        if(getAnimation().isAnimationFinished(getTimeElapsed())){
//            setAnimation(bookAnimation2, 240, 135, false);
//        }

        super.update(deltaTime);
    }

    public void draw(SpriteBatch batch){
        super.draw(batch);
    }
}