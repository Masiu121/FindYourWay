package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;

public class Paper {
    public Paper() {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(GameData.PAPER, 121, 2);
    }
}
