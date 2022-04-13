package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.blocks.Block;
import com.oxology.findyourway.utils.blocksystem.blocks.StartBlock;
import com.oxology.findyourway.utils.blocksystem.blocks.StopBlock;

import java.util.ArrayList;
import java.util.List;

public class Paper {
    private FindYourWay game;

    private List<Block> blocks;
    private TextCard card;

    private boolean visible;

    private float x, y;

    public Paper(FindYourWay game) {
        this.game = game;

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

            for(Block block : blocks) {
                block.draw(batch);
            }
        }
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            visible = !visible;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            addBlock(new StartBlock((int) x, (int) y, this, game));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            addBlock(new StopBlock((int) x, (int) y, this, game));
        }

        for(Block block : blocks) {
            block.update();
        }
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public boolean isVisible() {
        return visible;
    }
}
