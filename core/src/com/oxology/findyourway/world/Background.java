package com.oxology.findyourway.world;

import com.badlogic.gdx.graphics.Texture;

public class Background {
    int bgPositionX;
    int bgPositionY;

    Texture bgTexture;

    public Background(int bgPositionX, int bgPositionY, Texture bgTexture) {
        this.bgPositionX = bgPositionX;
        this.bgPositionY = bgPositionY;
        this.bgTexture = bgTexture;
    }

    public Texture getBgTexture() {
        return bgTexture;
    }

    public int getBgPositionX() {
        return bgPositionX;
    }

    public int getBgPositionY() {
        return bgPositionY;
    }
}
