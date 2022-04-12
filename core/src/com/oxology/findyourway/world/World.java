package com.oxology.findyourway.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.GameObject;
import com.oxology.findyourway.utils.Quest;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class World {
    private FindYourWay game;
    private List<GameObject> gameObjects;
    private final Player player;



    public World(FindYourWay game) {
        this.game = game;
        this.gameObjects = new ArrayList<>();

        player = new Player(((int) (GameData.BG_GRADIENT.getWidth()/2f - GameData.MAIN_CHAR_IDLE_1.getWidth()/9f/2f)), 3, GameData.MAIN_CHAR_IDLE_1, 1f, this.game);
    }

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void draw(SpriteBatch batch) {
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
        player.draw(batch);
    }

    public void update(float deltaTime) {
        for(GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
            if(gameObject instanceof Barrel) {
                Barrel barrel = (Barrel) gameObject;
                if(player.getX() + player.getWidth() + 5 > gameObject.getX() && player.getX() < gameObject.getX() + gameObject.getWidth() + 5) {
                    barrel.showText();

                    if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                        barrel.toggleFire();
                    }
                } else {
                    barrel.hideText();
                }
            }
        }
        player.update(deltaTime);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Player getPlayer() {
        return player;
    }
}
