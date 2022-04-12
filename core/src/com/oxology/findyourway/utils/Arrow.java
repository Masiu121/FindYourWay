package com.oxology.findyourway.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;

public class Arrow {
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
    private final boolean isRight;

    public Arrow(FindYourWay game, int x, int y, float scale, String text, Screen screen ,boolean isRight) {
        this.game = game;
        this.screen = screen;


        this.x = x;
        this.y = y;
        this.scale = scale;

        this.isRight = isRight;

        if(this.isRight){
            this.defaultTexture = GameData.ARROW_RIGHT;
            this.hoverTexture = GameData.ARROW_RIGHT_HOVER;
        } else {
            this.defaultTexture = GameData.ARROW_LEFT;
            this.hoverTexture = GameData.ARROW_LEFT_HOVER;
        }
        this.texture = defaultTexture;

        calculateSize();

        this.font = new BitmapFont(GameData.FONT);
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
        boolean horizontalHover = Gdx.input.getX()/game.windowViewportXProp > this.x && Gdx.input.getX()/game.windowViewportXProp < this.x + this.width;
        boolean verticalHover = 270-(Gdx.input.getY()/game.windowViewportYProp) > this.y && 270-(Gdx.input.getY()/game.windowViewportYProp) < this.y + this.height;

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

    public int changeHero(int textureNum , int heroCount){
        boolean horizontalHover = Gdx.input.getX()/game.windowViewportXProp > this.x && Gdx.input.getX()/game.windowViewportXProp < this.x + this.width;
        boolean verticalHover = 270-(Gdx.input.getY()/game.windowViewportYProp) > this.y && 270-(Gdx.input.getY()/game.windowViewportYProp) < this.y + this.height;

        if(horizontalHover && verticalHover) {
            if(Gdx.input.justTouched()){
                if(textureNum < heroCount){
                    textureNum++;
                } else {
                    textureNum = 1;
                }
                if(textureNum <= heroCount && textureNum > 1){
                    textureNum--;
                } else {
                    textureNum = heroCount;
                }
            }
        }

        return textureNum;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
