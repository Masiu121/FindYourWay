package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class Paper {
    private List<Block> blocks;
    private TextCard card;

    private boolean visible;

    private float x, y;

    public Paper() {
        card = new TextCard(1f , GameData.TEXT_CARD, "sdaasd");

        this.blocks = new ArrayList<>();
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch) {
        if(visible) {
            batch.draw(GameData.PAPER, x, y);
            card.draw(batch, x - 111, 70);
        }
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            visible = !visible;
        }
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public boolean isVisible() {
        return visible;
    }
}
