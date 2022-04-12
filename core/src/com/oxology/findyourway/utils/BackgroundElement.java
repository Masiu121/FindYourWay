package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundElement {
    float x, y;
    Texture[] textures;

    public BackgroundElement(float x, float y, Texture... textures) {
        this.textures = textures;
    }

    public void draw(SpriteBatch batch) {
        for(Texture texture : textures) {
            batch.draw(texture, x, y);
        }
    }
}
