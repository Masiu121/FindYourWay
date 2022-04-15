package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.screens.GameIntroScreen;
import com.oxology.findyourway.screens.MainGameScreen;
import com.oxology.findyourway.utils.blocksystem.blocks.*;
import com.oxology.findyourway.utils.menuComponents.Button;
import com.oxology.findyourway.utils.menuComponents.Clickable;
import com.oxology.findyourway.world.entities.Player;
import com.oxology.findyourway.world.entities.Npc;

import java.util.ArrayList;
import java.util.List;

import static com.oxology.findyourway.utils.blocksystem.blocks.Block.BLOCK_HEIGHT;

public class Paper {
    private FindYourWay game;

    private List<Block> blocks;
    private TextCard card;

    Player player;

    private boolean visible;

    private float x, y;

    public Paper(FindYourWay game, Player player) {
        this.game = game;

        this.player = player;

        card = new TextCard(1f, GameData.TEXT_CARD, "Nacisnij ENTER", "aby kontynuowac");

        this.blocks = new ArrayList<>();


        addBlock(new StartBlock((int) x, (int) y+70, this, game));
        addBlock(new StopBlock((int) x, (int) y+55, this, game));
        addBlock(new DivideBlock((int) x, (int) y+40, this, game));
        addBlock(new ADeclarationBlock((int) x, (int) y+25, this, game));
        addBlock(new BDeclarationBlock((int) x, (int) y+10, this, game));
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
            batch.draw(GameData.BLACK, x-122f, 0, 241, 135);
            batch.draw(GameData.PAPER, x, y);
            card.draw(batch, x - 108, 30);

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
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                if(checkBlocks()) {
                    player.setTicket(true);
                    visible = false;
                }
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

    public float getX() {
        return x;
    }

    private boolean checkBlocks() {
        Block startBlock = null;
        for(Block block : blocks) {
            if(block instanceof StartBlock) {
                startBlock = block;
            }
        }

        try {
            if (startBlock.getNextBlock() instanceof ADeclarationBlock) {
                if (startBlock.getNextBlock().getNextBlock() instanceof BDeclarationBlock) {
                    if (startBlock.getNextBlock().getNextBlock().getNextBlock() instanceof DivideBlock) {
                        if (startBlock.getNextBlock().getNextBlock().getNextBlock().getNextBlock() instanceof StopBlock) {
                            return true;
                        }
                    }
                }
            } else if (startBlock.getNextBlock() instanceof BDeclarationBlock) {
                if (startBlock.getNextBlock().getNextBlock() instanceof ADeclarationBlock) {
                    if (startBlock.getNextBlock().getNextBlock().getNextBlock() instanceof DivideBlock) {
                        if (startBlock.getNextBlock().getNextBlock().getNextBlock().getNextBlock() instanceof StopBlock) {
                            return true;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }
}
