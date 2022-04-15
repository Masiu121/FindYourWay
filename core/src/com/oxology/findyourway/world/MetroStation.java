package com.oxology.findyourway.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.world.entities.Train;

import java.util.Random;

public class MetroStation {
    private FindYourWay game;

    private Train rightTrain;
    private Train leftTrain;

    private Random random;

    public MetroStation(FindYourWay game) {
        this.game = game;
        random = new Random();
    }

    public void draw(SpriteBatch batch) {
        game.batch.draw(GameData.METRO_BRICKS, -240, 0);
        game.batch.draw(GameData.METRO_BRICKS, 0, 0);
        game.batch.draw(GameData.METRO_BRICKS, 240, 0);

        if (rightTrain != null)
            rightTrain.draw(batch);

        game.batch.draw(GameData.METRO_PLATFORM, -240, 0);
        game.batch.draw(GameData.METRO_PLATFORM, 0, 0);
        game.batch.draw(GameData.METRO_PLATFORM, 240, 0);
    }

    public void drawTop(SpriteBatch batch) {
        if (leftTrain != null)
            leftTrain.draw(batch);
    }

    public void update(float deltaTime) {

        if (random.nextFloat() > 0.999 && rightTrain == null) {
            rightTrain = new Train(game, 0);
        }

        if (random.nextFloat() > 0.999 && leftTrain == null) {
            leftTrain = new Train(game, 1);
        }

        if (leftTrain != null) {
            leftTrain.update(deltaTime);
            if (leftTrain.getX() > 1000 || leftTrain.getX() < -1000) {
                leftTrain = null;
            }
        }

        if (rightTrain != null) {
            rightTrain.update(deltaTime);
            if (rightTrain.getX() > 1000 || rightTrain.getX() < -1000) {
                rightTrain = null;
            }
        }
    }
}
