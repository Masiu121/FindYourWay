package com.oxology.findyourway.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Arrow;
import com.oxology.findyourway.utils.Button;

public class CharacterScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;
    Button goToMenu;

    Arrow arrow_next;
    Arrow arrow_previous;

    Texture player;

    public int textureNum = 1;
    public int heroCount = 3;

    public CharacterScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(480, 270);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        arrow_next = new Arrow(game, game.menuViewportWidth / 2 + 65, game.menuViewportHeight / 2 - 25, 1f, "", new CharacterScreen(game) , true);
        arrow_previous = new Arrow(game, game.menuViewportWidth / 2 - 100, game.menuViewportHeight / 2 - 25, 1f, "", new CharacterScreen(game) , false);

        goToMenu = new Button(game , game.menuViewportWidth / 2 - 60 , game.menuViewportHeight / 14 , 1f , "Menu" , new MainMenuScreen(game));
        player = GameData.MAIN_CHAR_IDLE_CHOOSE_1;
    }

    @Override
    public void render(float delta) {

        update();

        game.batch.begin();

        game.batch.draw(GameData.MENU_BACKGROUND, 0, 0);

        arrow_next.draw(game.batch);
        arrow_previous.draw(game.batch);
        goToMenu.draw(game.batch);

        game.batch.draw(player , game.menuViewportWidth / 2f - player.getWidth() / 2f , game.menuViewportHeight / 2f - player.getHeight() / 2f);

        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            if(textureNum < heroCount){
                textureNum++;
            } else {
                textureNum = 1;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            if(textureNum <= heroCount && textureNum > 1){
                textureNum--;
            } else {
                textureNum = heroCount;
            }
        }

        if(Gdx.input.justTouched()){
            textureNum = arrow_next.changeHero(textureNum , heroCount);
            textureNum = arrow_previous.changeHero(textureNum , heroCount);
        }

        if(textureNum == 1){
            player = GameData.MAIN_CHAR_IDLE_CHOOSE_1;
        } else if(textureNum == 2){
            player = GameData.MAIN_CHAR_IDLE_CHOOSE_2;
        } else if(textureNum == 3){
            player = GameData.MAIN_CHAR_IDLE_CHOOSE_3;
        }

    }

    public void update(){
        arrow_next.update();
        arrow_previous.update();
        goToMenu.update();
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
