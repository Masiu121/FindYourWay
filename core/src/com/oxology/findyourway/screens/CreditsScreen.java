package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.utils.Button;

public class CreditsScreen implements Screen {
    FindYourWay game;
    BitmapFont font;

    Button backButton;

    public CreditsScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont(Gdx.files.internal("Menu/PixelFont.fnt"));

        backButton = new Button(game, 0, 0, 4, "Back", new MainMenuScreen(game));

        int buttonX = Gdx.graphics.getBackBufferWidth()/2-backButton.getWidth()/2;

        backButton.move(buttonX, 50);
    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameTexture.MENU_BACKGROUND, 0, 0, FindYourWay.WINDOW_WIDTH, FindYourWay.WINDOW_HEIGHT);

        backButton.draw(game.batch);
        font.draw(game.batch, "Code: Maksuu121, dsadadas", 1, 25);

        game.batch.end();
    }

    public void update() {
        backButton.update();
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
