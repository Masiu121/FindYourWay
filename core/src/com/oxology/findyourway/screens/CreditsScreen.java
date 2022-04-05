package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.utils.Button;
import com.oxology.findyourway.utils.CenteredText;

public class CreditsScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    Button backButton;
    BitmapFont font;

    CenteredText creditsText1;
    CenteredText creditsText2;
    CenteredText creditsText3;

    CenteredText creditsText4;
    CenteredText creditsText5;
    CenteredText creditsText6;

    CenteredText creditsText7;
    CenteredText creditsText8;

    public CreditsScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        backButton = new Button(game, 0, 0, 1f, "Back", new MainMenuScreen(game));
        int buttonX = (int) camera.viewportWidth/2-backButton.getWidth()/2;
        backButton.move(buttonX, 10);

        font = new BitmapFont(Gdx.files.internal("Menu/PixelFont.fnt"));

        creditsText1 = new CenteredText((int) camera.viewportWidth/2, 260, 1.5f, "Developer:");
        creditsText2 = new CenteredText((int) camera.viewportWidth/2, 235, 1, "Maksymilian Kapolka");
        creditsText3 = new CenteredText((int) camera.viewportWidth/2, 220, 1, "Ivan Gnatyshen");

        creditsText4 = new CenteredText((int) camera.viewportWidth/2, 195, 1.5f, "Artist:");
        creditsText5 = new CenteredText((int) camera.viewportWidth/2, 170, 1, "Jakub Borowski");
        creditsText6 = new CenteredText((int) camera.viewportWidth/2, 155, 1, "Dominik Kruk");

        creditsText7 = new CenteredText((int) camera.viewportWidth/2, 125, 1.5f, "Project Creator:");
        creditsText8 = new CenteredText((int) camera.viewportWidth/2, 100, 1, "Patryk Wojdanowski");
    }

    @Override
    public void render(float delta) {
        update();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameTexture.MENU_BACKGROUND, 0, 0);

        backButton.draw(game.batch);

        font.getData().setScale(0.2f);
        font.draw(game.batch, "NullGroupGames 2022", 1, 6);

        creditsText1.draw(game.batch);
        creditsText2.draw(game.batch);
        creditsText3.draw(game.batch);

        creditsText4.draw(game.batch);
        creditsText5.draw(game.batch);
        creditsText6.draw(game.batch);

        creditsText7.draw(game.batch);
        creditsText8.draw(game.batch);

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
