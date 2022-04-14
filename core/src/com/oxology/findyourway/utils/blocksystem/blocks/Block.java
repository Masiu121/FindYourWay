package com.oxology.findyourway.utils.blocksystem.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.GameObject;
import com.oxology.findyourway.utils.blocksystem.Paper;

import java.util.List;

public class Block extends GameObject {
    public static int BLOCK_HEIGHT = 13;

    BlockType type;
    Block previous;
    Block next;
    boolean snapped;
    public float[] mousePos;
    Texture shadow;
    Block toConnect;
    Paper paper;

    float cameraOffset;

    public Block(float x, float y, Texture texture, BlockType type, Texture shadow, Paper paper, FindYourWay game) {
        super(x, y, texture, 1f, game);
        this.cameraOffset = 0;
        this.type = type;
        this.previous = null;
        this.next = null;
        this.snapped = false;
        this.mousePos = new float[] {0, 0};
        this.shadow = shadow;
        this.toConnect = null;
        this.paper = paper;
    }

    public boolean checkForHover(int xOffset, int yOffset) {
        boolean horizontal = game.getGameX()+cameraOffset+xOffset >= getX() && game.getGameX()+cameraOffset+xOffset <= getX()+super.getTexture().getWidth();
        boolean vertical = game.getGameY()+yOffset >= getY() && game.getGameY()+yOffset <= getY()+super.getTexture().getHeight();

        return horizontal && vertical;
    }

    private float[] getMousePos() {
        float mouseX = game.getGameX() - getX();
        float mouseY = game.getGameY() - getY();

        return new float[] {mouseX, mouseY};
    }

    public void checkForConnection(List<Block> blocks) {
        for(Block block : blocks) {
            if(toConnect == null) {
                if (this.isSnapped() && block.checkForHover(0, BLOCK_HEIGHT) && block != this && block.getNextBlock() == null) {
                    toConnect = block;
                }
            } else if(!toConnect.checkForHover(0, BLOCK_HEIGHT) && block != this) {
                toConnect = null;
            }
        }

        if(!isSnapped() && toConnect != null) {
            setPreviousBlock(toConnect);
            toConnect.setNextBlock(this);
            toConnect = null;

            float moveX = getPreviousBlock().getX();
            float moveY = getPreviousBlock().getY()-BLOCK_HEIGHT;
            moveWithNextBlocks(moveX, moveY);
        }
    }

    public void toggleSnap(boolean snapped) {
        this.snapped = snapped;
    }

    public boolean isSnapped() {
        return snapped;
    }

    public void setNextBlock(Block next) {
        this.next = next;
    }

    public void unsetNextBlock() {
        this.next = null;
    }

    public Block getNextBlock() {
        return next;
    }

    public void setPreviousBlock(Block previous) {
        this.previous = previous;
    }

    public void unsetPreviousBlock() {
        this.previous = null;
    }

    public Block getPreviousBlock() {
        return previous;
    }

    public Block getToConnect() {
        return toConnect;
    }

    public Texture getShadow() {
        return shadow;
    }

    private void moveWithNextBlocks(float moveX, float moveY) {
        this.move(moveX, moveY);

        Block nextBlock = getNextBlock();
        int blockNumber = 1;
        while(nextBlock != null) {
            nextBlock.move(moveX, moveY-BLOCK_HEIGHT*blockNumber);
            blockNumber++;
            nextBlock = nextBlock.getNextBlock();
        }
    }

    public void update() {
        if(Gdx.input.justTouched() && this.checkForHover(0, 0)) {
            this.toggleSnap(true);
            this.mousePos = this.getMousePos();
        }

        if(this.isSnapped()) {
            float moveX = game.getGameX() - mousePos[0];
            float moveY = game.getGameY() - mousePos[1];
            moveWithNextBlocks(moveX, moveY);

            if(getPreviousBlock() != null) {
                getPreviousBlock().setNextBlock(null);
                setPreviousBlock(null);
            }
        }

        if(!Gdx.input.isTouched()) {
            this.toggleSnap(false);
        }
    }

    public void setCameraOffset(float cameraOffset) {
        this.cameraOffset = cameraOffset;
    }
}
