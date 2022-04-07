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
import com.oxology.findyourway.world.entities.Player;

public class MainGameScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    Player player;
    Entity barrel;
    Entity fire;

    public MainGameScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        player = new Player(10, 10, GameTexture.MAIN_CHAR_IDLE, 1f);
        barrel = new Entity(50, 10, GameTexture.BARREL, 1f, false, 1);
        fire = new Entity(48, 29, GameTexture.FIRE, 1f, true, 5);
    }

    @Override
    public void render(float deltaTime) {

        update(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        player.draw(game.batch);
        barrel.draw(game.batch);
        fire.draw(game.batch);
        game.batch.end();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
        fire.update(deltaTime);
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
