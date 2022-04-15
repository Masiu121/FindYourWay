package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Background;
import com.oxology.findyourway.utils.Quest;
import com.oxology.findyourway.utils.blocksystem.Paper;
import com.oxology.findyourway.world.MetroStation;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Entity;
import com.oxology.findyourway.world.entities.Npc;

public class MetroScreen implements Screen {
    FindYourWay game;

    OrthographicCamera camera;
    World world;

    Npc npc;

    Paper paper;

    Background background;
    int cameraXOffset;
    int cameraYOffset;

    int cameraMaxXOffset;
    int cameraMaxYOffset;

    MetroStation station;

    float cameraSpeed;

    public MetroScreen(FindYourWay game) {
        this.game = game;

        station = new MetroStation(game);

        world = new World(game, 40, -180);

        cameraSpeed = world.getPlayer().getxSpeed()/2f;
        background = new Background(cameraSpeed);
        paper = new Paper(game, world.getPlayer());

        npc = new Npc(30, 39, GameData.NPC_1, 1f, game , new Quest() , true , 0);
        cameraXOffset = 0;
        cameraYOffset = 0;

        cameraMaxXOffset = 30;
        cameraMaxYOffset = 30;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        camera.position.set(-60, camera.position.y, 0);
    }

    @Override
    public void render(float delta) {

        update(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            PauseScreen.PAUSE = true;
            game.setScreen(new PauseScreen(game, 1));
        }

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();

        for(int i = 480; i < 480+240*10; i+=240)
            game.batch.draw(GameData.METRO_TUNNEL, i, 0);

        if(station.getRightTrain() != null && station.getRightTrain().getPlayer() == null)
            station.draw(game.batch);
        else if(station.getRightTrain() == null) {
            station.draw(game.batch);
        }
        background.draw(game.batch);

        station.draw(game.batch);

        npc.draw(game.batch);

        world.draw(game.batch);

        if(station.getRightTrain() != null && station.getRightTrain().getPlayer() != null)
            station.draw(game.batch);

        npc.draw(game.batch);

        if(world.getPlayer().getX() < -185+GameData.METRO_EXIT.getWidth()) {
            float dist = (-185+GameData.METRO_EXIT.getWidth()) + (185-20);
            float dist2 = world.getPlayer().getX() + (185-20);
            float result = dist2/dist;

            game.batch.setColor(result, result, result, 1f);

            world.getPlayer().setY(40+((1-result)*20));

            if(world.getPlayer().getX() < -180) {
                game.setScreen(game.mainGameScreen);
            }
        } else if(!world.getPlayer().jump) {
            world.getPlayer().setY(40);
        }

        game.batch.draw(GameData.METRO_EXIT, -185, 40);

        station.drawTop(game.batch);

        paper.draw(game.batch);

        for(int i = 480; i < 480+240*10; i+=240)
            game.batch.draw(GameData.METRO_TUNNEL_BLACKOUT, i, 0);

        game.batch.end();
    }

    public void update(float deltaTime) {
        paper.update(deltaTime);

        if(!paper.isVisible()) {
            world.update(deltaTime);
        } else {
            paper.setPos(camera.position.x + 1f, 2f);
            background.setCameraSpeed(0);
        }
        background.update(deltaTime);

        station.update(deltaTime);

        npc.update(deltaTime);

        world.update(deltaTime);

        if(station.getRightTrain() != null && station.getRightTrain().getPlayer() == null) {
            if (Math.abs(world.getPlayer().getX() - camera.position.x) < cameraMaxXOffset) {
                if (world.getPlayer().getX() - camera.position.x > 0)
                    cameraXOffset = -cameraMaxXOffset;
                else
                    cameraXOffset = cameraMaxXOffset;
            } else {
                float offset = camera.position.x - world.getPlayer().getX() - cameraXOffset;
                if (camera.position.x - offset > -120 / 2f && camera.position.x - offset < 120 + 120) {
                    camera.position.set(world.getPlayer().getX() + cameraXOffset, camera.position.y, 0);
                }
            }
        } else if(station.getRightTrain() != null && station.getRightTrain().getPlayer() != null) {
            camera.position.set(world.getPlayer().getX() + cameraXOffset, camera.position.y, 0);
        } else if(station.getRightTrain() == null) {
            if (Math.abs(world.getPlayer().getX() - camera.position.x) < cameraMaxXOffset) {
                if (world.getPlayer().getX() - camera.position.x > 0)
                    cameraXOffset = -cameraMaxXOffset;
                else
                    cameraXOffset = cameraMaxXOffset;
            } else {
                float offset = camera.position.x - world.getPlayer().getX() - cameraXOffset;
                if (camera.position.x - offset > -120 / 2f && camera.position.x - offset < 120 + 120) {
                    camera.position.set(world.getPlayer().getX() + cameraXOffset, camera.position.y, 0);
                }
            }
        }

        if(station.getRightTrain() != null && station.getRightTrain().areDoorsOpened()) {
            if(world.getPlayer().getX() >= station.getRightTrain().getX() && world.getPlayer().getX() <= station.getRightTrain().getX() + GameData.METRO_TRAIN.getWidth()) {
                if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                    station.getRightTrain().setPlayer(world.getPlayer());
                }
            }
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

    public World getWorld() {
        return world;
    }
}
