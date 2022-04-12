package com.oxology.findyourway.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

public class Button {
    private final FindYourWay game;

    private int x, y;
    private int width, height;
    private final float scale;
    private Texture texture;
    private final Texture button;
    private final Texture buttonHover;

    private BitmapFont font;
    private String text;
    private int textWidth;
    private int textHeight;

    private Clickable clickable;

    public Button(FindYourWay game, int x, int y, float scale, String text, Texture button, Texture buttonHover, Clickable clickable) {
        this.game = game;

        this.x = x;
        this.y = y;
        this.scale = scale;

        this.button = button;
        this.buttonHover = buttonHover;
        this.texture = button;

        calculateSize();

        this.font = new BitmapFont(GameData.FONT);
        font.getData().setScale(this.scale/2.5f);

        this.text = text;

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);

        this.textWidth = (int) layout.width;
        this.textHeight = (int) layout.height;

        this.clickable = clickable;
    }

    public Button(FindYourWay game, int x, int y, float scale, Texture button, Texture buttonHover, Clickable clickable) {
        this.game = game;

        this.x = x;
        this.y = y;
        this.scale = scale;

        this.button = button;
        this.buttonHover = buttonHover;
        this.texture = button;

        calculateSize();

        this.clickable = clickable;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y, this.width, this.height);

        int textX = this.x + this.width/2 - textWidth/2;
        int textY = this.y + this.height/2 + textHeight/2;
        font.draw(batch, text, textX, textY);
    }

    public void calculateSize() {
        this.width = (int) (this.texture.getWidth()*this.scale);
        this.height = (int) (this.texture.getHeight()*this.scale);
    }

    public void update() {
        boolean horizontalHover = Gdx.input.getX()/game.windowViewportXProp > this.x && Gdx.input.getX()/game.windowViewportXProp < this.x + this.width;
        boolean verticalHover = 270-(Gdx.input.getY()/game.windowViewportYProp) > this.y && 270-(Gdx.input.getY()/game.windowViewportYProp) < this.y + this.height;

        if(horizontalHover && verticalHover) {
            texture = buttonHover;
            if(Gdx.input.justTouched()) {
                clickable.onClick();
            }
        } else {
            texture = button;
        }
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
}
