package ru.fadedfog.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.fadedfog.tetris.TetrisGame;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "T e t r i s";
		config.width = 336;
		config.height = 696;
		config.y += 1;
		config.resizable = false;
		new LwjglApplication(new TetrisGame(), config);
	}
}
