package com.oxology.findyourway.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Player;

public class MainGameScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;
    World world;

    public MainGameScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        Barrel barrel = new Barrel(50, 7, GameTexture.BARREL, 1f);

        world = new World();

        world.addGameObject(barrel);
    }

    @Override
    public void render(float deltaTime) {

        update(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);

        game.batch.begin();
        game.batch.draw(GameTexture.GAME_BACKGROUND, 0, 0);
        world.draw(game.batch);
        game.batch.end();
    }

    public void update(float deltaTime) {
        world.update(deltaTime);
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
