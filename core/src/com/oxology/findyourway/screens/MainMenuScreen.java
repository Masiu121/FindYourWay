package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.utils.Button;

public class MainMenuScreen implements Screen {
    FindYourWay game;
    BitmapFont font;

    Button playButton;
    Button creditsButton;
    Button exitButton;

    public MainMenuScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont(Gdx.files.internal("Menu/PixelFont.fnt"));

        playButton = new Button(game, 0, 0, 4, "Play", new MainGameScreen());
        creditsButton = new Button(game, 0, 0, 4, "Credits", new CreditsScreen(game));
        exitButton = new Button(game, 0, 0, 4, "Exit", null);

        int buttonX = Gdx.graphics.getBackBufferWidth()/2-playButton.getWidth()/2;

        playButton.move(buttonX, 350);
        creditsButton.move(buttonX, 200);
        exitButton.move(buttonX, 50);
    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameTexture.MENU_BACKGROUND, 0, 0, FindYourWay.WINDOW_WIDTH, FindYourWay.WINDOW_HEIGHT);

        playButton.draw(game.batch);
        creditsButton.draw(game.batch);
        exitButton.draw(game.batch);
        font.draw(game.batch, "Version: " + FindYourWay.VERSION, 1, 25);

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
