package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;

public class GameObject {
    public FindYourWay game;
    private float x, y;
    private int width, height;
    private Texture texture;
    private boolean visible;
    private final float scale;

    public GameObject(float x, float y, Texture texture, float scale, FindYourWay game) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.visible = true;
        this.scale = scale;
        this.game = game;
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

    public void setY(float y) {
        this.y = y;
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

    public void update(float deltaTime) {}

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public float getScale() {
        return scale;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

