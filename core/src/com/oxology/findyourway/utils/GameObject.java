package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
    private float x, y;
    private Texture texture;
    private boolean visible;
    private final float scale;

    public GameObject(float x, float y, Texture texture, float scale) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.visible = true;
        this.scale = scale;
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, texture.getWidth()*scale, texture.getHeight()*scale);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(float xOffset, float yOffset) {
        this.x += xOffset;
        this.y += yOffset;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void update() {}

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public float getScale() {
        return scale;
    }
}

