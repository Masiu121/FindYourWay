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

import static com.oxology.findyourway.utils.blocksystem.blocks.Block.BLOCK_HEIGHT;

public class Paper {
    private FindYourWay game;

    private List<Block> blocks;
    private TextCard card;

    private boolean visible;

    private float x, y;

    public Paper(FindYourWay game) {
        this.game = game;

        card = new TextCard(1f, GameData.TEXT_CARD, "sdaasd");

        this.blocks = new ArrayList<>();
    }

    public void setPos(float x, float y) {
        for (Block block : blocks) {
            block.moveBy(x - this.x, 0);
            block.setCameraOffset(x - game.gameViewportWidth / 2f);
        }

        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            batch.draw(GameData.PAPER, x, y);
            card.draw(batch, x - 111, 70);

            for (Block block : blocks) {
                Block toConnect = block.getToConnect();
                if (block.isSnapped()) {
                    if (toConnect != null) {
                        if (block.getShadow() != null) {
                            batch.draw(block.getShadow(), toConnect.getX(), toConnect.getY() - BLOCK_HEIGHT);

                            int blockNumber = 2;
                            Block nextBlock = block.getNextBlock();
                            while (nextBlock != null) {
                                if (nextBlock.getShadow() != null)
                                    batch.draw(block.getShadow(), toConnect.getX(), toConnect.getY() - BLOCK_HEIGHT * blockNumber);

                                blockNumber++;
                                nextBlock = nextBlock.getNextBlock();
                            }
                        }
                    }
                }
                batch.draw(block.getTexture(), block.getX(), block.getY());
            }
        }
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            visible = !visible;
        }

        if (visible) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
                addBlock(new StartBlock((int) x, (int) y, this, game));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
                addBlock(new StopBlock((int) x, (int) y, this, game));
            }

            for (Block block : blocks) {
                block.update();
                block.checkForConnection(blocks);
            }


        }
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public boolean isVisible() {
        return visible;
    }
}
