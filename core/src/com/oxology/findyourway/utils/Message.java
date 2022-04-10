package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.GameData;

public class Message {
    int x, y;

    String text;
    BitmapFont font;

    int width, height;
    float scale;

    public Message(int x, int y, String text, float scale) {
        this.x = x;
        this.y = y;

        this.scale = scale;

        font = new BitmapFont(GameData.FONT);
        font.getData().setScale(scale/2.5f);
        this.text = text;

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, this.text);

        this.width = (int) layout.width;
        this.height = (int) layout.height;
    }

    public void draw(SpriteBatch batch) {
        for(float i = 0; i < width; i+= scale) {
            batch.draw(GameData.MESSAGE_MIDDLE, x+i, y, GameData.MESSAGE_MIDDLE.getWidth()*scale, GameData.MESSAGE_MIDDLE.getHeight()*scale);
        }
        batch.draw(GameData.MESSAGE_RIGHT, x+width, y, GameData.MESSAGE_RIGHT.getWidth()*scale, GameData.MESSAGE_RIGHT.getHeight()*scale);
        font.draw(batch, text, x+11*scale, y+height+10*scale);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
