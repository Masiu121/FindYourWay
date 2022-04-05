package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.utils.Button;

public class MainMenuScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    Button playButton;
    Button creditsButton;
    Button exitButton;

    public MainMenuScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        playButton = new Button(game, 0, 0, 1f, "Play", new MainGameScreen(game));
        creditsButton = new Button(game, 0, 0, 1f, "Credits", new CreditsScreen(game));
        exitButton = new Button(game, 0, 0, 1f, "Exit", null);

        int buttonX = (int) camera.viewportWidth/2-playButton.getWidth()/2;

        playButton.move(buttonX, 80);
        creditsButton.move(buttonX, 45);
        exitButton.move(buttonX, 10);
    }

    @Override
    public void render(float delta) {
        update();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameTexture.MENU_BACKGROUND, 0, 0);

        playButton.draw(game.batch);
        creditsButton.draw(game.batch);
        exitButton.draw(game.batch);

        game.batch.end();
    }

    public void update() {
        playButton.update();
        creditsButton.update();
        exitButton.update();
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
