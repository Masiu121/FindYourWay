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
import com.oxology.findyourway.utils.menuComponents.Button;
import com.oxology.findyourway.utils.menuComponents.Clickable;

import java.util.List;

public class GameIntroScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    private List<GameObject> gameObjects;

    Button toMenu;
    Button toGame;
    Button black;

    private boolean paperVisible;

    private Book book;

    public GameIntroScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        book = new Book(GameData.INTRO_BG.getWidth() / 2 - 245 / 2,
                GameData.INTRO_BG.getHeight() / 2 - 10 - GameData.BOOK_OPEN_ANIMATION.getHeight() / 2 ,
                GameData.BOOK_NEXT_PAGE_ANIMATION , 4f , game);

        toMenu = new Button(game, 30, 15, 0.7f, "Menu", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        toGame = new Button(game,  (int)(camera.viewportWidth + 10 - GameData.MENU_BUTTON.getWidth()), 15, 0.7f, "Start", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                game.setScreen(game.mainGameScreen);
            }
        });

        black = new Button(game, (int)(camera.viewportWidth / 2 - GameData.BLACK.getWidth()  / 2), (int)(camera.viewportHeight / 2 - GameData.BLACK.getHeight() / 2) + 10, 1f, "", GameData.BLACK, GameData.BLACK, new Clickable() {
            @Override
            public void onClick() {
                paperVisible = !paperVisible;
            }
        });
    }

    @Override
    public void render(float delta) {

        update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            game.setScreen(game.mainGameScreen);
        }
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        black.draw(game.batch);

        game.batch.draw(GameData.INTRO_BG , 0 , 0 , camera.viewportWidth , camera.viewportHeight);

        book.draw(game.batch);

        toMenu.draw(game.batch);

        if(paperVisible)
            toGame.draw(game.batch);

        if(paperVisible){
            game.batch.draw(GameData.INTRO_PAPER , camera.viewportWidth / 2 - GameData.INTRO_PAPER.getWidth() / 2f , camera.viewportHeight / 2 - GameData.INTRO_PAPER.getHeight() / 2f);
        }

        game.batch.end();

        book.update(delta);
    }

    public void update(){
        toMenu.update();

        if(paperVisible)
            toGame.update();

        black.update();
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
