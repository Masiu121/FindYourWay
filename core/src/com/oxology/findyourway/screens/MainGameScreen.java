package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.world.entities.Entity;

public class MainGameScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    Entity player;
    TextureRegion[][] animationFrames;
    Animation<TextureRegion> animation;
    float timeElapsed;

    public MainGameScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        player = new Entity(10, 10, GameTexture.MAIN_CHAR_IDLE);

        animationFrames = TextureRegion.split(player.getTexture(), 14, 45);
        animation = new Animation<TextureRegion>(1f/4f, animationFrames[0]);
    }

    @Override
    public void render(float deltaTime) {
        timeElapsed += Gdx.graphics.getDeltaTime();

        update(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(animation.getKeyFrame(timeElapsed, true), player.getX(), player.getY());
        game.batch.end();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
