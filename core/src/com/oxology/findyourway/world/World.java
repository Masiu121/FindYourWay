package com.oxology.findyourway.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameTexture;
import com.oxology.findyourway.utils.GameObject;
import com.oxology.findyourway.world.entities.Barrel;
import com.oxology.findyourway.world.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<GameObject> gameObjects;
    private final Player player;

    public World() {
        this.gameObjects = new ArrayList<>();
        player = new Player(10, 3, GameTexture.MAIN_CHAR_IDLE_1, 1f);
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
            if(player.getX() + player.getWidth() > gameObject.getX() && player.getX() < gameObject.getX()+ gameObject.getWidth()) {
                if(gameObject instanceof Barrel) {
                    if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                        ((Barrel) gameObject).toggleFire();
                    }
                }
            }
        }
        player.update(deltaTime);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
