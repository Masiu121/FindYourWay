package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;

public class Paper {

    public boolean drawPaper;

    public Paper(boolean drawPaper) {
        this.drawPaper = drawPaper;
    }

    public void draw(SpriteBatch batch , float x , float y) {
        batch.draw(GameData.PAPER, x, y);
    }

    public boolean isDrawPaper() {
        return drawPaper;
    }
}
