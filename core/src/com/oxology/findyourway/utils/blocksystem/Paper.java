package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;

public class Paper {

    public boolean drawPaper;

    public Paper(boolean drawPaper) {
        this.drawPaper = drawPaper;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(GameData.PAPER, 0, 2);
    }

    public boolean isDrawPaper() {
        return drawPaper;
    }
}
