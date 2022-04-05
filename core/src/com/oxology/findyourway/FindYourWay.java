package com.oxology.findyourway;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.screens.MainMenuScreen;

public class FindYourWay extends Game {
	public static final int VIEWPORT_WIDTH = 480;
	public static final int VIEWPORT_HEIGHT = 270;

	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;

	public static final float WINDOW_VIEWPORT_Y_PROP = (float) WINDOW_HEIGHT/VIEWPORT_HEIGHT;
	public static final float WINDOW_VIEWPORT_X_PROP = (float) WINDOW_WIDTH/VIEWPORT_WIDTH;

	public static final String VERSION = "0.01a";

	public SpriteBatch batch;

	@Override
	public void create () {
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
