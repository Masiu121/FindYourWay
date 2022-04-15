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

public class MainMenuScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;
    Button playButton;
    Button characterButton;
    Button creditsButton;
    Button exitButton;




    Animation<TextureRegion> background;

    public MainMenuScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        playButton = new Button(game, 0, 0, 1f, "Play", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                game.setScreen(new GameIntroScreen(game));
            }
        });

        characterButton = new Button(game, 0, 0, 1f, "Character", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                game.setScreen(new CharacterScreen(game));
            }
        });

        creditsButton = new Button(game, 0, 0, 1f, "Credits", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                game.setScreen(new CreditsScreen(game));
            }
        });

        exitButton = new Button(game, 0, 0, 1f, "Exit", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                Gdx.app.exit();
            }
        });

        int buttonX = (int) camera.viewportWidth/2-playButton.getWidth()/2;

        playButton.move(buttonX, 115);
        characterButton.move(buttonX, 80);
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

        game.batch.draw(GameData.MENU_BACKGROUND, 0, 0);

        playButton.draw(game.batch);
        characterButton.draw(game.batch);
        creditsButton.draw(game.batch);
        exitButton.draw(game.batch);
        game.batch.draw(GameData.LOGO, camera.viewportWidth/2f - GameData.LOGO.getWidth()*1.8f/2f, 155, GameData.LOGO.getWidth() * 1.8f, GameData.LOGO.getHeight() * 1.8f);

        game.batch.end();
    }

    public void update() {
        playButton.update();
        characterButton.update();
        creditsButton.update();
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
