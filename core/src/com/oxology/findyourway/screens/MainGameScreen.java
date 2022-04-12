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
import com.oxology.findyourway.utils.blocksystem.TextCard;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Barrel;
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

        paper = new Paper(false);
        card = new TextCard(game , 0 , 0 , 1f , GameData.TEXT_CARD);

        Barrel barrel = new Barrel(50, 7, GameData.BARREL, 1f, game);

        world = new World(game);

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
        game.batch.draw(GameData.VIGNETTE, camera.position.x - GameData.VIGNETTE.getWidth() / 2f, camera.position.y - GameData.VIGNETTE.getHeight() / 2f);
        npc.draw(game.batch);
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            if(paper.isDrawPaper()){
                paper.drawPaper = false;
            } else {
                paper.drawPaper = true;
            }

            System.out.println("Position: " + camera.position.x);
        }

        if(paper.isDrawPaper()){
            paper.draw(game.batch , camera.position.x , camera.position.y - GameData.PAPER.getHeight() / 2);
            card.draw(game.batch , camera.position.x - 110 , camera.position.y);
        }
        game.batch.end();
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E))
            game.setScreen(new MainMenuScreen(game));

        world.update(deltaTime);
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
                background.setCameraSpeed(world.getPlayer().getxSpeed()/4f);
                camera.position.set(world.getPlayer().getX() + cameraXOffset, camera.position.y, 0);
            }
        }

        if((camera.position.x) != (world.getPlayer().getX()+cameraXOffset) || world.getPlayer().getxSpeed()==0) {
            background.setCameraSpeed(0);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("Pause");
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
