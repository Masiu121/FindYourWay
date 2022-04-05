package com.oxology.findyourway;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.oxology.findyourway.FindYourWay;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setResizable(false);
		//config.setFullscreenMode(Gdx.graphics.getDisplayMode());
		config.setWindowedMode(FindYourWay.WINDOW_WIDTH, FindYourWay.WINDOW_HEIGHT);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new FindYourWay(), config);
	}
}
