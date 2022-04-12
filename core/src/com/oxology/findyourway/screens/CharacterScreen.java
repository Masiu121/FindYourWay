package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Button;
import com.oxology.findyourway.utils.Clickable;

public class CharacterScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;

    Button back;
    Button arrowNext;
    Button arrowPrevious;

    Texture player;

    public int heroCount = 3;

    public CharacterScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        back = new Button(game, 0, 0, 1f, "Back", GameData.MENU_BUTTON, GameData.MENU_BUTTON_HOVER, new Clickable() {
            @Override
            public void onClick() {
                Gdx.app.exit();
            }
        });

        int buttonX = (int) camera.viewportWidth/2-back.getWidth()/2;

        back.move(buttonX, 10);

        arrowNext = new Button(game, 0, 0, 1f, GameData.ARROW_RIGHT, GameData.ARROW_RIGHT_HOVER, new Clickable() {
            @Override
            public void onClick() {
                next();
            }
        });

        arrowPrevious = new Button(game, 0, 0, 1f, GameData.ARROW_LEFT, GameData.ARROW_LEFT_HOVER, new Clickable() {
            @Override
            public void onClick() {
                previous();
            }
        });

        int buttonY = (int) camera.viewportHeight/2-arrowNext.getHeight()/2;

        arrowNext.move(20, buttonY);
        arrowPrevious.move((int) camera.viewportWidth-20, buttonY);

        //goToMenu = new Button(game , game.menuViewportWidth / 2 - 60 , game.menuViewportHeight / 14 , 1f , "Menu" , new MainMenuScreen(game));
        player = GameData.MAIN_CHAR_IDLE_CHOOSE_1;
    }

    @Override
    public void render(float delta) {

        update();

        game.batch.begin();

        game.batch.draw(GameData.MENU_BACKGROUND, 0, 0);

        arrowNext.draw(game.batch);
        arrowPrevious.draw(game.batch);
        back.draw(game.batch);

        game.batch.draw(player , game.menuViewportWidth / 2f - player.getWidth() / 2f , game.menuViewportHeight / 2f - player.getHeight() / 2f);

        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            next();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            previous();
        }

        if(game.mainCharacter == 1){
            player = GameData.MAIN_CHAR_IDLE_CHOOSE_1;
        } else if(game.mainCharacter == 2){
            player = GameData.MAIN_CHAR_IDLE_CHOOSE_2;
        } else if(game.mainCharacter == 3){
            player = GameData.MAIN_CHAR_IDLE_CHOOSE_3;
        }

    }

    public void next() {

    }

    public void previous() {

    }

    public void update(){
        arrowNext.update();
        arrowPrevious.update();
        back.update();
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
