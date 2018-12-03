package com.kothead.sacrifice.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kothead.sacrifice.GodGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		GodGame game = new GodGame();

		config.width = game.getConfiguration().height;
		config.height = game.getConfiguration().width;
		config.samples = 8;
		config.resizable = false;
		new LwjglApplication(game, config);
	}
}
