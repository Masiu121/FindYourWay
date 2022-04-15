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
            }
        });

        exitButton = new Button(game, 0, 0, 1f, "Exit", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                Gdx.app.exit();
            }
        });

        int buttonX = (int) camera.viewportWidth/2-resumeButton.getWidth()/2;

        resumeButton.move(buttonX, 80);
        menuButton.move(buttonX, 45);
        exitButton.move(buttonX, 10);
    }

    @Override
    public void render(float delta) {
        update();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameData.MENU_BACKGROUND, 0, 0);

        resumeButton.draw(game.batch);
        menuButton.draw(game.batch);
        exitButton.draw(game.batch);
        game.batch.draw(GameData.LOGO, camera.viewportWidth/2f - GameData.LOGO.getWidth()*1.8f/2f, 155, GameData.LOGO.getWidth() * 1.8f, GameData.LOGO.getHeight() * 1.8f);

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
}
