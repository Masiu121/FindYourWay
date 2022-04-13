package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Book;
import com.oxology.findyourway.utils.GameObject;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Entity;
import com.oxology.findyourway.world.entities.Player;

import java.util.List;

public class GameIntroScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    private List<GameObject> gameObjects;

    private Book book;

    public GameIntroScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {

        // game.setScreen(new MainGameScreen(game));

        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        book = new Book(GameData.INTRO_BG.getWidth() / 2 - 220 / 2,
                GameData.INTRO_BG.getHeight() / 2 + 20 - GameData.BOOK_OPEN_ANIMATION.getHeight() / 2 ,
                GameData.BOOK_NEXT_PAGE_ANIMATION , 2f , game);
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            game.setScreen(new MainGameScreen(game));
        }
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(GameData.INTRO_BG , 0 , 0 , camera.viewportWidth , camera.viewportHeight);

        book.draw(game.batch);

        game.batch.end();

        book.update(delta);
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
