package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.utils.Clickable;

public class TextCard {
    private final FindYourWay game;

    private int x, y;
    private int width, height;
    private final float scale;
    private Texture texture;
    private final Texture card;

    private BitmapFont font;
    private String text;
    private int textWidth;
    private int textHeight;


    public TextCard(FindYourWay game, int x, int y, float scale, Texture card) {
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

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y, this.width, this.height);

        if(font != null) {
            int textX = this.x + this.width / 2 - textWidth / 2;
            int textY = this.y + this.height / 2 + textHeight / 2;
            font.draw(batch, text, textX, textY);
        }
    }
}
