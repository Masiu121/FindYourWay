package com.oxology.findyourway.screens;

import com.badlogic.gdx.Screen;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;

public class MainMenuScreen implements Screen {
    FindYourWay game;

    public MainMenuScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(GameTexture.MENU_BACKGROUND, 0, 0, FindYourWay.WINDOW_WIDTH, FindYourWay.WINDOW_HEIGHT);
        game.batch.end();
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
