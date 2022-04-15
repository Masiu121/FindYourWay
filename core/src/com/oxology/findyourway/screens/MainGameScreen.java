package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Quest;
import com.oxology.findyourway.utils.blocksystem.Paper;
import com.oxology.findyourway.utils.Background;
import com.oxology.findyourway.utils.menuComponents.Button;
import com.oxology.findyourway.utils.menuComponents.Clickable;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Entity;
import com.oxology.findyourway.world.entities.Npc;

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

    public boolean firstRun;
    float cameraSpeed;

    public MainGameScreen(final FindYourWay game) {
        this.game = game;
        firstRun = true;

        cameraXOffset = 0;
        cameraYOffset = 0;

        cameraMaxXOffset = 30;
        cameraMaxYOffset = 30;

        paper = new Paper(game);

        Barrel barrel = new Barrel(50, 7, GameData.BARREL, 1f, game);
        world = new World(game, 3, (int) (GameData.BG_GRADIENT.getWidth()/2f - GameData.MAIN_CHAR_1_IDLE_1.getWidth()/9f/2f));

        world.addGameObject(barrel);
        cameraSpeed = world.getPlayer().getxSpeed()/2f;
        background = new Background(cameraSpeed);
        npc = new Npc(-100, 3, GameData.HOTDOGS, 1f, game , new Quest() , true , 1);

        game.batch.setColor(1, 1, 1, 1);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        if(!firstRun && !PauseScreen.PAUSE) {
            world.getPlayer().setX(300);
            camera.position.set(240, camera.position.y, 0);
        }

        if(PauseScreen.PAUSE)
            PauseScreen.PAUSE = false;

        if(firstRun) {
            firstRun = false;
        }
    }

    @Override
    public void render(float deltaTime) {

        update(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);

        game.batch.begin();
        background.draw(game.batch);
        npc.draw(game.batch);
        world.draw(game.batch);
        game.batch.draw(GameData.METRO_ENTRY, 360-GameData.METRO_ENTRY.getWidth(), 0);
        game.batch.draw(GameData.VIGNETTE, camera.position.x - GameData.VIGNETTE.getWidth() / 2f, camera.position.y - GameData.VIGNETTE.getHeight() / 2f);

        if(world.getPlayer().getX() > 360-GameData.METRO_ENTRY.getWidth()) {
            float dist = (360-60)-(360-GameData.METRO_ENTRY.getWidth());
            float dist2 = (360-60)-world.getPlayer().getX();
            float result = dist2/dist;

            game.batch.setColor(result, result, result, 1f);

            world.getPlayer().setY(3+((1-result)*-20));

            if(world.getPlayer().getX() > 310) {
                game.setScreen(game.metroScreen);
            }
        } else if(!world.getPlayer().jump) {
            world.getPlayer().setY(3);
        }

        paper.draw(game.batch);

        game.batch.end();
    }

    public void update(float deltaTime) {
        paper.update(deltaTime);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            PauseScreen.PAUSE = true;
            game.setScreen(new PauseScreen(game, 0));
        }

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

    public World getWorld() {
        return world;
    }
}
