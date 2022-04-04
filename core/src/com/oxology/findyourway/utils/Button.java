package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;

public class Button {
    private int x, y;
    private int width, height;
    private float scale;
    private Texture texture;

    public Button(int x, int y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.texture = GameTexture.MENU_BUTTON;
        calculateSize();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y, this.width, this.y);
    }

    public void calculateSize() {
        this.width = (int) (this.texture.getWidth()*this.scale);
        this.height = (int) (this.width*FindYourWay.HW_PROP);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }
}
