package com.oxology.findyourway;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.screens.MainGameScreen;
import com.oxology.findyourway.screens.MainMenuScreen;

public class FindYourWay extends Game {
	public Music music;
	public SpriteBatch batch;

	public int menuViewportWidth;
	public int menuViewportHeight;

	public float windowViewportXProp;
	public float windowViewportYProp;

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
		setup();

		mainCharacter = 0;

		gravity = 8f;

		this.setScreen(new MainMenuScreen(this));
	}

	public void setup() {
		windowViewportXProp = (float) Gdx.graphics.getBackBufferWidth()/ menuViewportWidth;
		windowViewportYProp = (float) Gdx.graphics.getBackBufferHeight()/ menuViewportHeight;
	}

	@Override
	public void render () {
		super.render();


		if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		GameData.MENU_BACKGROUND.dispose();
		GameData.MENU_BUTTON.dispose();
		GameData.MENU_BUTTON_HOVER.dispose();
	}
}
