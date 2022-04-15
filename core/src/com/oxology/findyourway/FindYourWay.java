package com.oxology.findyourway;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.screens.MainMenuScreen;

public class FindYourWay extends Game {
	public Music music;
	public SpriteBatch batch;

	public int menuViewportWidth;
	public int menuViewportHeight;

	public int gameViewportWidth;
	public int gameViewportHeight;

	private float menuViewportXProp;
	private float menuViewportYProp;

	public float gameViewportXProp;
	public float gameViewportYProp;

	public int mainCharacter;

	public float gravity;

	@Override
	public void create () {
		music = Gdx.audio.newMusic(GameData.BACKGROUND_MUSIC);
		music.setLooping(true);
		music.setVolume(0.2f);
		music.play();


		batch = new SpriteBatch();

		menuViewportWidth = 480;
		menuViewportHeight = 270;

		gameViewportWidth = 240;
		gameViewportHeight = 135;
		setup();

		mainCharacter = 0;

		gravity = 15f;

		this.setScreen(new MainMenuScreen(this));
	}

	public void setup() {
		menuViewportXProp = (float) Gdx.graphics.getBackBufferWidth() / menuViewportWidth;
		menuViewportYProp = (float) Gdx.graphics.getBackBufferHeight() / menuViewportHeight;

		gameViewportXProp = (float) Gdx.graphics.getBackBufferWidth() / gameViewportWidth;
		gameViewportYProp = (float) Gdx.graphics.getBackBufferHeight() / gameViewportHeight;
	}

	@Override
	public void render () {
		super.render();
		if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		GameData.MENU_BACKGROUND.dispose();
		GameData.MENU_BUTTON.dispose();
		GameData.MENU_BUTTON_HOVER.dispose();
	}

	public float getMenuX() {
		return Gdx.input.getX()/ menuViewportXProp;
	}

	public float getGameX() {
		return Gdx.input.getX()/ gameViewportXProp;
	}

	public float getMenuY() {
		return menuViewportHeight-Gdx.input.getY()/ menuViewportYProp;
	}

	public float getGameY() {
		return gameViewportHeight-Gdx.input.getY()/ gameViewportYProp;
	}
}
