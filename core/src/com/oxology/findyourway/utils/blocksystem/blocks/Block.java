package com.oxology.findyourway.utils.blocksystem.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.GameObject;
import com.oxology.findyourway.utils.blocksystem.Paper;

import java.util.List;

public class Block extends GameObject {
    public static int BLOCK_HEIGHT = 28;

    BlockType type;
    Block previous;
    Block next;
    boolean snapped;
    public int[] mousePos;
    Texture shadow;
    Block toConnect;
    Paper paper;

    private float hoverX;
    private float hoverY;

    public Block(float x, float y, Texture texture, BlockType type, Texture shadow, Paper paper, FindYourWay game) {
        super(x, y, texture, 1f, game);
        hoverX = x;
        hoverY = y;
        this.type = type;
        this.previous = null;
        this.next = null;
        this.snapped = false;
        this.mousePos = new int[] {0, 0};
        this.shadow = shadow;
        this.toConnect = null;
        this.paper = paper;
    }

    public boolean checkForHover(int xOffset, int yOffset) {
        boolean horizontal = game.getGameX()+xOffset >= hoverX && game.getGameX()+xOffset <= hoverX+super.getTexture().getWidth();
        boolean vertical = game.getGameY()+yOffset >= hoverY && game.getGameY()+yOffset <= hoverY+super.getTexture().getHeight();

        return horizontal && vertical;
    }

    private int[] getMousePos() {
        int mouseX = (int) (game.getGameX() - hoverX);
        int mouseY = (int) (game.getGameY() - hoverY);

        return new int[] {mouseX, mouseY};
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
            moveWithNextBlocks((int) moveX, (int) moveY);
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
        this.move((int) moveX, (int) moveY);

        Block nextBlock = getNextBlock();
        int blockNumber = 1;
        while(nextBlock != null) {
            nextBlock.move((int) moveX, (int) moveY-BLOCK_HEIGHT*blockNumber);
            blockNumber++;
            nextBlock = nextBlock.getNextBlock();
        }
    }

    public void update() {
        if(Gdx.input.justTouched() && this.checkForHover(0, 0)) {
            this.toggleSnap(true);
            this.mousePos = this.getMousePos();
        }

        if(checkForHover(0, 0))
            System.out.println("Hover");

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
}
