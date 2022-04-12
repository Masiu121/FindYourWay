package com.oxology.findyourway.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Button;
import com.oxology.findyourway.utils.Clickable;
import com.oxology.findyourway.utils.Message;
import com.oxology.findyourway.utils.Quest;
import com.oxology.findyourway.utils.blocksystem.Paper;
import com.oxology.findyourway.utils.blocksystem.TextCard;
import com.oxology.findyourway.world.Background;
import com.oxology.findyourway.world.World;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Npc;
import com.badlogic.gdx.graphics.Texture;

public class MainGameScreen implements Screen {
    FindYourWay game;
    OrthographicCamera camera;
    World world;

    Texture middleBg;
    public Texture leftBg;
    public Texture rightBg;
    TextCard card;

    int cameraXOffset;
    int cameraYOffset;

    int cameraMaxXOffset;
    int cameraMaxYOffset;


    Background middle_bg = new Background(0 , 0 , GameData.GAME_BACKGROUND);

    public Background left_bg = new Background(-240 , 0 , GameData.GAME_BACKGROUND);

    public Background right_bg = new Background(240 , 0 , GameData.GAME_BACKGROUND);

    Npc npc;

    Paper paper;

    public MainGameScreen(FindYourWay game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(240, 135);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        cameraXOffset = 0;
        cameraYOffset = 0;

        cameraMaxXOffset = 30;
        cameraMaxYOffset = 30;

        paper = new Paper(false);

        Barrel barrel = new Barrel(50, 7, GameData.BARREL, 1f, game);

        // middleBg = new Texture((TextureData) middle_bg.getBgTexture());

        world = new World(game);

        world.addGameObject(barrel);

        card = new TextCard(game , -105 , game.menuViewportHeight / 2 - 70 , 1f , GameData.TEXT_CARD);
        npc = new Npc(20, 3, GameData.MAIN_CHAR_IDLE_1, 1f, game, new Quest());
    }

    @Override
    public void render(float deltaTime) {

        update(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1, 1, 1, 1);

        game.batch.begin();
        game.batch.draw(GameData.GAME_BACKGROUND, middle_bg.getBgPositionX(), middle_bg.getBgPositionY());
        game.batch.draw(GameData.GAME_BACKGROUND , right_bg.getBgPositionX() , right_bg.getBgPositionY());
        game.batch.draw(GameData.GAME_BACKGROUND , left_bg.getBgPositionX() , left_bg.getBgPositionY());
        world.draw(game.batch);
        game.batch.draw(GameData.VIGNETTE, camera.position.x-GameData.VIGNETTE.getWidth()/2f, camera.position.y-GameData.VIGNETTE.getHeight()/2f);
        npc.draw(game.batch);
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            if(paper.isDrawPaper() == true){
                paper.drawPaper = false;
            } else {
                paper.drawPaper = true;
            }
        }

        if(paper.isDrawPaper() == true){
            paper.draw(game.batch);
            card.draw(game.batch);
        }
        game.batch.end();
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.E))
            game.setScreen(new MainMenuScreen(game));

        world.update(deltaTime);
        npc.update(deltaTime);


            if(Math.abs(world.getPlayer().getX()-camera.position.x) < cameraMaxXOffset) {
                if(world.getPlayer().getX()-camera.position.x > 0)
                    cameraXOffset = -cameraMaxXOffset;
                else
                    cameraXOffset = cameraMaxXOffset;
            } else {

                float offset = camera.position.x - world.getPlayer().getX()-cameraXOffset;

                if(camera.position.x - offset > left_bg.getBgPositionX() / 2f && camera.position.x - offset < right_bg.getBgPositionX() + 120) {
                    camera.position.set(world.getPlayer().getX() + cameraXOffset, camera.position.y, 0);
                } else {
                    if(camera.position.x > 0)
                        camera.position.x = right_bg.getBgPositionX() + 120;
                    else
                        camera.position.x = left_bg.getBgPositionX() / 2f;
                }

                System.out.println(offset);
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
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
