package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundElement {
    float x, y;
    Texture[] textures;

    public BackgroundElement(float x, float y, Texture... textures) {
        this.x = x;
        this.y = y;

        this.textures = textures;
    }

    public void draw(SpriteBatch batch) {
        for(Texture texture : textures) {
            batch.draw(texture, x, y);
            batch.draw(texture, x + texture.getWidth(), y);
            batch.draw(texture, x - texture.getWidth(), y);
        }
    }

    public void move(float xOffset, float yOffset) {
        this.x += xOffset;
        this.y += yOffset;
    }
}
