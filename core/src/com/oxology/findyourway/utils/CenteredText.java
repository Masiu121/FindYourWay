package com.oxology.findyourway.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CenteredText {
    private int x, y;
    private int width, height;
    private final float scale;
    private BitmapFont font;

    private String text;

    public CenteredText(int x, int y, float scale, String text) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.text = text;

        this.font = new BitmapFont(Gdx.files.internal("Menu/PixelFont.fnt"));
        font.getData().setScale(this.scale/2.5f);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);

        this.width = (int) layout.width;
        this.height = (int) layout.height;

        this.x = this.x - this.width/2;
        this.y = this.y - this.height/2;
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, text, x, y);
    }
}
