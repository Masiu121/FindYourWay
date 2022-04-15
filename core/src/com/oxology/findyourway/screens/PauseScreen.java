package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.menuComponents.Button;
import com.oxology.findyourway.utils.menuComponents.Clickable;

public class PauseScreen implements Screen {
    public static boolean PAUSE = false;

    FindYourWay game;
    OrthographicCamera camera;

    Button resumeButton;
    Button menuButton;
    Button exitButton;

    public boolean playedBefore;


    int screen;

    public PauseScreen(FindYourWay game, int screen) {
        this.game = game;
        this.screen = screen;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        playedBefore = false;

        resumeButton = new Button(game, 0, 0, 1f, "Resume", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                if(screen == 0)
                    game.setScreen(game.mainGameScreen);
                else
                    game.setScreen(game.metroScreen);
            }
        });

        menuButton = new Button(game, 0, 0, 1f, "Menu", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                game.setScreen(new MainMenuScreen(game));
                playedBefore = true;
            }
        });

        exitButton = new Button(game, 0, 0, 1f, "Exit", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                Gdx.app.exit();
            }
        });

        int buttonX = (int) camera.viewportWidth/2-resumeButton.getWidth()/2;
        int buttonY = (int) camera.viewportHeight / 2 - resumeButton.getHeight() / 2;

        resumeButton.move(buttonX, buttonY + 35);
        menuButton.move(buttonX, buttonY);
        exitButton.move(buttonX, buttonY - 35);
    }

    @Override
    public void render(float delta) {
        update();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameData.MENU_BACKGROUND, 0, 0);
        game.batch.draw(GameData.PAUSE_VIGNETTE , 0 , 0);

        resumeButton.draw(game.batch);
        menuButton.draw(game.batch);
        exitButton.draw(game.batch);

        game.batch.end();
    }

    public void update() {
        resumeButton.update();
        menuButton.update();
        exitButton.update();
    }

    @Override
    public void resize(int width, int height) {
        game.setup();
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

    public boolean isPlayedBefore() {
        return playedBefore;
    }
}
