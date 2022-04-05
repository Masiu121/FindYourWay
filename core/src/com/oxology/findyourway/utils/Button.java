package com.oxology.findyourway.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameTexture;

public class Button {
    private final FindYourWay game;

    private int x, y;
    private int width, height;
    private final float scale;
    private Texture texture;
    private final Texture defaultTexture;
    private final Texture hoverTexture;
    private final BitmapFont font;

    private final String text;
    private final int textWidth;
    private final int textHeight;

    private final Screen screen;

    public Button(FindYourWay game, int x, int y, float scale, String text, Screen screen) {
        this.game = game;
        this.screen = screen;

        this.x = x;
        this.y = y;
        this.scale = scale;

        this.defaultTexture = GameTexture.MENU_BUTTON;
        this.hoverTexture = GameTexture.MENU_BUTTON_HOVER;
        this.texture = defaultTexture;

        calculateSize();

        this.font = new BitmapFont(Gdx.files.internal("Menu/PixelFont.fnt"));
        font.getData().setScale(this.scale/2.5f);

        this.text = text;

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);

        this.textWidth = (int) layout.width;
        this.textHeight = (int) layout.height;
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
        boolean horizontalHover = Gdx.input.getX()/FindYourWay.WINDOW_VIEWPORT_X_PROP > this.x && Gdx.input.getX()/FindYourWay.WINDOW_VIEWPORT_X_PROP < this.x + this.width;
        boolean verticalHover = 270-(Gdx.input.getY()/FindYourWay.WINDOW_VIEWPORT_Y_PROP) > this.y && 270-(Gdx.input.getY()/FindYourWay.WINDOW_VIEWPORT_Y_PROP) < this.y + this.height;

        if(horizontalHover && verticalHover) {
            texture = hoverTexture;
            if(Gdx.input.justTouched()) {
                if(screen != null) {
                    this.game.setScreen(screen);
                } else {
                    Gdx.app.exit();
                }
            }
        } else {
            texture = defaultTexture;
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
