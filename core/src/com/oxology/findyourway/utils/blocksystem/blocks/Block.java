package com.oxology.findyourway.utils.blocksystem.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.GameObject;

public class Block extends GameObject {
    public Block(float x, float y, Texture texture, float scale, FindYourWay game) {
        super(x, y, texture, scale, game);
    }
}
