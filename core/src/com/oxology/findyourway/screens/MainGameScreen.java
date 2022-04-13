package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Quest;
import com.oxology.findyourway.utils.blocksystem.Paper;
import com.oxology.findyourway.utils.Background;
import com.oxology.findyourway.utils.blocksystem.TextCard;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Npc;
import com.oxology.findyourway.world.entities.Train;

public class MainGameScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;
    World world;

    int cameraXOffset;
    int cameraYOffset;

    int cameraMaxXOffset;
    int cameraMaxYOffset;

    Background background;
    Npc npc;

    Paper paper;
    TextCard card;

    float cameraSpeed;

    public MainGameScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        cameraXOffset = 0;
        cameraYOffset = 0;

        cameraMaxXOffset = 30;
        cameraMaxYOffset = 30;

        paper = new Paper();

        Barrel barrel = new Barrel(50, 7, GameData.BARREL, 1f, game);
        world = new World(game, 3);

        world.addGameObject(barrel);
        cameraSpeed = world.getPlayer().getxSpeed()/2f;
        background = new Background(cameraSpeed);
        npc = new Npc(20, 3, GameData.MAIN_CHAR_IDLE_1, 1f, game, new Quest());
    }

    @Override
    public void render(float deltaTime) {

        update(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);

        game.batch.begin();
        background.draw(game.batch);
        world.draw(game.batch);
        game.batch.draw(GameData.METRO_ENTRY, 360-GameData.METRO_ENTRY.getWidth(), 0);
        game.batch.draw(GameData.VIGNETTE, camera.position.x - GameData.VIGNETTE.getWidth() / 2f, camera.position.y - GameData.VIGNETTE.getHeight() / 2f);
        npc.draw(game.batch);

        if(world.getPlayer().getX() > 360-GameData.METRO_ENTRY.getWidth()) {
            float dist = (360-60)-(360-GameData.METRO_ENTRY.getWidth());
            float dist2 = (360-60)-world.getPlayer().getX();
            float result = dist2/dist;

            game.batch.setColor(result, result, result, 1f);

            world.getPlayer().setY(3+((1-result)*-20));

            if(world.getPlayer().getX() > 310) {
                game.setScreen(new MetroScreen(game));
            }
        } else if(!world.getPlayer().jump) {
            world.getPlayer().setY(3);
        }

        paper.draw(game.batch);

        game.batch.end();
    }

    public void update(float deltaTime) {
        paper.update(deltaTime);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E))
            game.setScreen(new MainMenuScreen(game));

        if(!paper.isVisible()) {
            world.update(deltaTime);
        } else {
            paper.setPos(camera.position.x + 1f, 2f);
            background.setCameraSpeed(0);
        }
        npc.update(deltaTime);
        background.update(deltaTime);

        if (Math.abs(world.getPlayer().getX() - camera.position.x) < cameraMaxXOffset) {
            if (world.getPlayer().getX() - camera.position.x > 0)
                cameraXOffset = -cameraMaxXOffset;
            else
                cameraXOffset = cameraMaxXOffset;
        } else {


            float offset = camera.position.x - world.getPlayer().getX() - cameraXOffset;
            if (camera.position.x - offset > -120 / 2f && camera.position.x - offset < 120 + 120) {
                background.setCameraSpeed(world.getPlayer().getxSpeed()/2f);
                camera.position.set(world.getPlayer().getX() + cameraXOffset, camera.position.y, 0);
            }
        }

        if((camera.position.x) != (world.getPlayer().getX()+cameraXOffset) || world.getPlayer().getxSpeed()==0) {
            background.setCameraSpeed(0);
        }
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

    public OrthographicCamera getCamera() {
        return camera;
    }
}
