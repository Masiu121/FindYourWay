package com.oxology.findyourway.screens;

import com.badlogic.gdx.Screen;
import com.oxology.findyourway.FindYourWay;

public class GameIntroScreen implements Screen {
    FindYourWay game;

    public GameIntroScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.setScreen(new MainGameScreen(game));
    }

    @Override
    public void render(float delta) {

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
