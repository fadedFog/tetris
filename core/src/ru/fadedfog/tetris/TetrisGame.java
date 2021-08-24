package ru.fadedfog.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.Dot;
import ru.fadedfog.tetris.models.GameField;
import ru.fadedfog.tetris.screens.GameScreen;

public class TetrisGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Screen screen;
	private GameField gameField;
	private GameConfig config;
	private Dot dot;
	private long lastTime;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		screen = new GameScreen(this);
		config = GameConfig.getInstance();
		createModels();
		lastTime = System.currentTimeMillis();
	}
	
	private void createModels() {
		gameField = new GameField();
		dot = new Dot();
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(0, 0, 0, 1);
		screen.render(1);
	}
	
	private void update() {
		fallShape();
		dot.move();
		collision();
	}
	
	private void fallShape() {
		if (didTimedPass(1)) {
				dot.fall();	
			lastTime = System.currentTimeMillis();
		}
	}
	
	private boolean didTimedPass(int second) {
		return (System.currentTimeMillis() - lastTime) / 1000 >= second;
	}
	
	private void collision() {
		int sizePartSharp = config.getSizePartShap();
		if (dot.getX() - sizePartSharp < gameField.getX()) {
			dot.setX(gameField.getX());
		}
		if (dot.getX() >= gameField.getX() + gameField.getWidth()) {
			dot.setX(gameField.getX() + gameField.getWidth() - sizePartSharp);
		}
		if (dot.getY() - sizePartSharp < gameField.getY()) {
			dot.setY(gameField.getY());
		}
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

	public GameField getGameField() {
		return gameField;
	}

	public void setGameField(GameField gameField) {
		this.gameField = gameField;
	}

	public Dot getDot() {
		return dot;
	}

	public void setDot(Dot dot) {
		this.dot = dot;
	}

}
