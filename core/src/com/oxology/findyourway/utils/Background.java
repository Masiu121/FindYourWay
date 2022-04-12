package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;

import java.util.ArrayList;
import java.util.List;

public class Background {
    List<BackgroundElement> elements;
    float cameraSpeed;

    public Background(float cameraSpeed) {
        this.elements = new ArrayList<>();
        elements.add(new BackgroundElement(0, 0, GameData.BG_SUN, GameData.BG_GRADIENT));
        elements.add(new BackgroundElement(0, 0, GameData.BG_BUILDING_1));
        elements.add(new BackgroundElement(0, 0, GameData.BG_BUILDING_2));
        elements.add(new BackgroundElement(0, 0, GameData.BG_BUILDING_3));
        elements.add(new BackgroundElement(0, 0, GameData.BG_TREE));
        elements.add(new BackgroundElement(0, 0, GameData.BG_RAILINGS));

        this.cameraSpeed = cameraSpeed;
    }

    public void update(float deltaTime) {
        if(cameraSpeed != 0) {
            float speed = cameraSpeed*deltaTime;
            int elementLevel = elements.size();
            for(BackgroundElement element : elements) {
                if(elementLevel != 1) {
                    element.move(-speed / elementLevel, 0);
                    elementLevel--;
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for(BackgroundElement element : elements) {
            element.draw(batch);
        }
    }

    public void setCameraSpeed(float cameraSpeed) {
        this.cameraSpeed = cameraSpeed;
    }
}
