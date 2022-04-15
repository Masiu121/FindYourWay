package com.oxology.findyourway.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Quest;
import com.oxology.findyourway.world.MetroStation;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Entity;
import com.oxology.findyourway.world.entities.Npc;
import com.oxology.findyourway.world.entities.Train;

public class MetroScreen implements Screen {
    FindYourWay game;

    OrthographicCamera camera;
    World world;

    Npc npc;

    int cameraXOffset;
    int cameraYOffset;

    int cameraMaxXOffset;
    int cameraMaxYOffset;

    MetroStation station;

    public MetroScreen(FindYourWay game) {
        this.game = game;

        station = new MetroStation(game);

        world = new World(game, 40, -180);

        npc = new Npc(20, 40, GameData.MAIN_CHAR_IDLE_1, 0f, game , new Quest() , true);
        Entity npc1 = new Entity(30, 39, GameData.NPC_1, 1, 5, true, game);
        npc1.setTiming(1f/1.5f);
        world.addGameObject(npc1);
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

        System.out.println(npc.questMapVisibility);

        if(npc.questMapVisibility <= 100){
            npc.questVisibility = true;
        } else {
            npc.questVisibility = false;
        }

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();

        station.draw(game.batch);

        npc.draw(game.batch);

        world.draw(game.batch);

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
        game.batch.end();
    }

    public void update(float deltaTime) {
        station.update(deltaTime);

        npc.update(deltaTime);

        world.update(deltaTime);

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
