package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            drawPaper = !drawPaper;
        }
    }

    public boolean isDrawPaper() {
        return drawPaper;
    }
}
