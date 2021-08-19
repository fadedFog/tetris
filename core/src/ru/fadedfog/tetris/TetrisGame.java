package ru.fadedfog.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.fadedfog.tetris.screens.GameScreen;

public class TetrisGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Screen screen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		screen = new GameScreen();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		screen.render(1);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
}
