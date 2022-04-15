package com.oxology.findyourway.utils.blocksystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;

public class TextCard {
    private float x, y;
    private int width, height;
    private final float scale;
    private Texture texture;

    private BitmapFont font;
    private String text;
    private String text2;
    private int textWidth;
    private int textHeight;


    public TextCard(float scale, Texture texture, String text, String text2) {
        this.scale = scale;
        this.texture = texture;
        this.text = text;
        this.text2 = text2;

        this.font = new BitmapFont(GameData.FONT);
        font.getData().setScale(this.scale/7f);

        this.text = text;

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);

        this.textWidth = (int) layout.width;
        this.textHeight = (int) layout.height;

        calculateSize();
    }

    public void calculateSize() {
        this.width = (int) (this.texture.getWidth()*this.scale);
        this.height = (int) (this.texture.getHeight()*this.scale);
    }

    public void draw(SpriteBatch batch , float posX , float posY) {
        batch.draw(this.texture, posX, posY, this.width, this.height);

        if(font != null) {
            float textX = posX + this.width / 2f - textWidth / 2f;
            float textY = posY + this.height / 2f + textHeight / 2f;
            font.draw(batch, text, textX-3, textY-10);
            font.draw(batch, text2, textX-3, textY-15);
        }
    }
}
