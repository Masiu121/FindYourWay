package com.oxology.findyourway.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.world.entities.Train;

import java.util.List;

public class MetroStation {
    private FindYourWay game;
    private List<Train> trains;

    public MetroStation(FindYourWay game) {
        this.game = game;
    }

    public void draw(SpriteBatch batch) {
        for(Train train : trains) {
            train.draw(batch);
        }
    }

    public void update(float deltaTime) {
        for(Train train : trains) {
            train.update(deltaTime);
        }
    }
}
