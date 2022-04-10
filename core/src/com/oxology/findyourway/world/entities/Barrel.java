package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.CenteredText;
import com.oxology.findyourway.utils.Message;

public class Barrel extends Entity {
    private Entity fire;
    private boolean fired;
    private boolean showText;
    private Message message;

    TextureRegion[][] fireStartFrames;
    Animation<TextureRegion> fireStartAnimation;

    TextureRegion[][] fireEndFrames;
    Animation<TextureRegion> fireEndAnimation;

    TextureRegion[][] fireFrames;
    Animation<TextureRegion> fireAnimation;

    private boolean fireStarted;

    Sound fireStartSound;
    Sound fireSound;
    Sound zippoSound;
    long fireSoundId;

    public Barrel(int x, int y, Texture texture, float scale, FindYourWay game) {
        super(x, y, texture, scale, 1, false, game);
        this.fire = new Entity((int) super.getX()-2, (int) super.getY()+19, GameData.FIRE, 1f, 5, true, game);
        this.fired = false;
        this.fireStarted = false;

        this.message = new Message(50+GameData.BARREL.getWidth(), 30, "Press enter to fire it up!", 0.2f);

        this.showText = false;

        fireStartFrames = TextureRegion.split(GameData.FIRE_START, 21, 25);
        fireStartAnimation = new Animation<TextureRegion>(1f/12f, fireStartFrames[0]);

        fireEndFrames = TextureRegion.split(GameData.FIRE_END, 21, 25);
        fireEndAnimation = new Animation<TextureRegion>(1f/4f, fireEndFrames[0]);

        fireFrames = TextureRegion.split(GameData.FIRE, 21, 25);
        fireAnimation = new Animation<TextureRegion>(1f/8f, fireFrames[0]);
        fire.setAnimation(null, 21, 25, false);

        fireStartSound = Gdx.audio.newSound(GameData.FIRE_START_SOUND);
        fireSound = Gdx.audio.newSound(GameData.FIRE_SOUND);
        zippoSound = Gdx.audio.newSound(GameData.ZIPPO_SOUND);

        resumeAnimation();
    }

    public void toggleFire() {
        if(fire.getAnimation() == null || fire.getAnimation() == fireAnimation) {
            if (fired) {
                fire.setTimeElapsed(0);
                this.fire.setAnimation(fireEndAnimation, 21, 25, false);
                this.fireStarted = false;
                fireSound.setVolume(fireSoundId, 0.4f);
            } else {
                fire.setTimeElapsed(0);
                this.fire.setAnimation(fireStartAnimation, 21, 25, false);
                fireStartSound.play(1f);
                zippoSound.play(1f);
                this.fired = true;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(fired) {
            fire.draw(batch);
        }

        if(showText) {
            message.draw(batch);
        }
    }

    public void update(float deltaTime) {
        fire.update(deltaTime);
        super.update(deltaTime);
        if(fire.getAnimation() != null && fire.getAnimation().isAnimationFinished(fire.getTimeElapsed())) {
            if(fire.getAnimation() == fireStartAnimation) {
                zippoSound.stop();
                fireSoundId = fireSound.play();
                fireSound.setLooping(fireSoundId, true);
                fireStarted = true;
            }
            if(fire.getAnimation() == fireEndAnimation) {
                fireSound.stop();
                this.fired = false;
                fire.setAnimation(null, 21, 25, false);
            }
        }

        if(fireStarted) {
            fire.setAnimation(fireAnimation, 21, 25, true);
        }
    }

    public void showText() {
        this.showText = true;
    }

    public void hideText() {
        this.showText = false;
    }

    public Message getMessage() {
        return message;
    }
}
