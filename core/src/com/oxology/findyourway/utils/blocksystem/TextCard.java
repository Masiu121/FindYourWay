package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.Clickable;

public class TextCard {
    private float x, y;
    private int width, height;
    private final float scale;
    private Texture texture;

    private BitmapFont font;
    private String text;
    private int textWidth;
    private int textHeight;


    public TextCard(float scale, Texture texture, String text) {
        this.scale = scale;
        this.texture = texture;
        this.text = text;

        calculateSize();
    }

    public void calculateSize() {
        this.width = (int) (this.texture.getWidth()*this.scale);
        this.height = (int) (this.texture.getHeight()*this.scale);
    }

    public void draw(SpriteBatch batch , float posX , float posY) {
        batch.draw(this.texture, posX, posY, this.width, this.height);

        if(font != null) {
            float textX = this.x + this.width / 2f - textWidth / 2f;
            float textY = this.y + this.height / 2f + textHeight / 2f;
            font.draw(batch, text, textX, textY);
        }
    }
}
