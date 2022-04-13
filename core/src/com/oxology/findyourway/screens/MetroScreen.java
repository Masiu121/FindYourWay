package com.oxology.findyourway.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Train;

public class MetroScreen implements Screen {
    FindYourWay game;

    OrthographicCamera camera;
    World world;

    int cameraXOffset;
    int cameraYOffset;

    int cameraMaxXOffset;
    int cameraMaxYOffset;

    public Train train;

    public MetroScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        game.batch.setColor(1, 1, 1, 1);

        world = new World(game, 40);

        cameraXOffset = 0;
        cameraYOffset = 0;

        cameraMaxXOffset = 30;
        cameraMaxYOffset = 30;

        train = new Train(0, 8, 1, game);
    }

    @Override
    public void render(float delta) {
        update(delta);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(GameData.METRO_BRICKS, -240, 0);
        game.batch.draw(GameData.METRO_BRICKS, 0, 0);
        game.batch.draw(GameData.METRO_BRICKS, 240, 0);

        game.batch.draw(GameData.METRO_PLATFORM, -240, 0);
        game.batch.draw(GameData.METRO_PLATFORM, 0, 0);
        game.batch.draw(GameData.METRO_PLATFORM, 240, 0);

        world.draw(game.batch);

        train.draw(game.batch);

        game.batch.end();
    }

    public void update(float deltaTime) {
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

        train.update(deltaTime);
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
