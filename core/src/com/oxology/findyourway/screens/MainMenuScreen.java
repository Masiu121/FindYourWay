package com.oxology.findyourway.screens;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.utils.Button;

public class MainMenuScreen implements Screen {
    FindYourWay game;
    BitmapFont font;
    float scale;

    public MainMenuScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont(/*Gdx.files.internal("Menu/PixelFont.ttf")*/);
        scale = 5;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(GameTexture.MENU_BACKGROUND, 0, 0, FindYourWay.WINDOW_WIDTH, FindYourWay.WINDOW_HEIGHT);

        Button button = new Button(10, 10, 1);
        button.draw(game.batch);
        font.draw(game.batch, "Version: " + FindYourWay.VERSION, 100, 100);
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
