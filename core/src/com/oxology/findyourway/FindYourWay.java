package com.oxology.findyourway;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.screens.MainMenuScreen;

public class FindYourWay extends Game {
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final float HW_PROP = (float)WINDOW_HEIGHT/WINDOW_WIDTH;

	public static final String VERSION = "0.01a";

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
