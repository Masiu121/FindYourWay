package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.GameTexture;

public class Barrel extends Entity {
    private Entity fire;
    private boolean fired;

    TextureRegion[][] fireStartFrames;
    Animation<TextureRegion> fireStartAnimation;

    TextureRegion[][] fireEndFrames;
    Animation<TextureRegion> fireEndAnimation;

    TextureRegion[][] fireFrames;
    Animation<TextureRegion> fireAnimation;

    private boolean fireStarted;

    public Barrel(int x, int y, Texture texture, float scale) {
        super(x, y, texture, scale, 1, false);
        this.fire = new Entity((int) super.getX()-2, (int) super.getY()+19, GameTexture.FIRE, 1f, 5, true);
        this.fired = false;
        this.fireStarted = false;

        fireStartFrames = TextureRegion.split(GameTexture.FIRE_START, 21, 25);
        fireStartAnimation = new Animation<TextureRegion>(1f/12f, fireStartFrames[0]);

        fireEndFrames = TextureRegion.split(GameTexture.FIRE_END, 21, 25);
        fireEndAnimation = new Animation<TextureRegion>(1f/4f, fireEndFrames[0]);

        fireFrames = TextureRegion.split(GameTexture.FIRE, 21, 25);
        fireAnimation = new Animation<TextureRegion>(1f/8f, fireFrames[0]);

        resumeAnimation();
    }

    public void toggleFire() {
        if(fired) {
            this.fire.setTimeElapsed(0);
            this.fire.setAnimation(fireEndAnimation, 21, 25, false);
            this.fireStarted = false;
        } else {
            this.fire.setTimeElapsed(0);
            this.fire.setAnimation(fireStartAnimation, 21, 25, false);
            this.fired = true;
        }
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(fired) {
            fire.draw(batch);
        }
    }

    public void update(float deltaTime) {
        fire.update(deltaTime);
        super.update(deltaTime);
        if(fire.getAnimation().isAnimationFinished(fire.getTimeElapsed())) {
            if(fire.getAnimation() == fireStartAnimation) {
                fireStarted = true;
            }
            if(fire.getAnimation() == fireEndAnimation) {
                this.fired = false;
            }
        }

        if(fireStarted) {
            fire.setAnimation(fireAnimation, 21, 25, true);
        }
    }
}
