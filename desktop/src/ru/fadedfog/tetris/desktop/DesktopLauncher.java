package ru.fadedfog.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.fadedfog.tetris.TetrisGame;
import ru.fadedfog.tetris.config.GameConfig;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		GameConfig gameConfig = GameConfig.getInstance();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = gameConfig.getTitleWindow();
		config.width = gameConfig.getWidthWindow();
		config.height = gameConfig.getHeightWindow();
		config.y += gameConfig.getyWindow();
		config.resizable = gameConfig.isResizableWindow();
		new LwjglApplication(new TetrisGame(), config);
	}
}
