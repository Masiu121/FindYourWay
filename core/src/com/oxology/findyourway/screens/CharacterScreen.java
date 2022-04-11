package com.oxology.findyourway.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

public class CharacterScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    public CharacterScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {

        game.batch.begin();

        game.batch.draw(GameData.MENU_BACKGROUND, 0, 0);

        game.batch.draw(GameData.ARROW_PREVIOUS , 0 , 0);
        game.batch.draw(GameData.ARROW_NEXT , 50 , 0);

        game.batch.end();
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
