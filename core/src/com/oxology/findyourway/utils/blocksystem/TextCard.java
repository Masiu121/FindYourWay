package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.Clickable;

public class TextCard {
    private final FindYourWay game;

    private float x, y;
    private int width, height;
    private final float scale;
    private Texture texture;
    private final Texture card;

    private BitmapFont font;
    private String text;
    private int textWidth;
    private int textHeight;


    public TextCard(FindYourWay game, float x, float y, float scale, Texture card) {
        this.game = game;

        this.x = x;
        this.y = y;
        this.scale = scale;

        this.card = card;
        this.texture = card;

        calculateSize();
    }

    public void calculateSize() {
        this.width = (int) (this.texture.getWidth()*this.scale);
        this.height = (int) (this.texture.getHeight()*this.scale);
    }

    public void draw(SpriteBatch batch , float posX , float posY) {
        batch.draw(this.texture, posX, posY, this.width, this.height);

        if(font != null) {
            float textX = this.x + this.width / 2 - textWidth / 2;
            float textY = this.y + this.height / 2 + textHeight / 2;
            font.draw(batch, text, textX, textY);
        }
    }
}
