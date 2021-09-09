package ru.fadedfog.tetris.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import ru.fadedfog.tetris.TetrisGame;
import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.Dot;

public class GameScreen implements Screen {
	private GameConfig config;
	private TetrisGame game;
	private SpriteBatch batch;
	private Texture spriteBorderField;
	private Texture spriteDot;
	private ShapeRenderer shapeRenderer;
	
	public GameScreen(TetrisGame game) {
		this.game = game;
		config = GameConfig.getInstance();
		batch = game.getBatch();
		shapeRenderer = new ShapeRenderer();
		createSprites();
	}
	
	private void createSprites() {
		spriteBorderField = new Texture(Gdx.files.internal("border_field.png"));
		spriteDot = new Texture(Gdx.files.internal("dot_green.png"));
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		drawBoundField();
		drawGridField();
		drawDots();
	}
	
	private void drawBoundField() {
		batch.begin();
		batch.draw(spriteBorderField, game.getGameField().getX() - 4, game.getGameField().getY() - 4, 
				game.getGameField().getWidth() + 8, game.getGameField().getHeight() + 32);
		batch.end();
	}
	
	private void drawGridField() {
		shapeRenderer.begin(ShapeType.Line);
		Color colorGrid = new Color(0.3f, 0.28f, 0.1f, 0);
		int sizePartSharp = config.getSizePartShap(); 
		int columnsNumber = config.getColumnsNumber();
		int rowsNumber = config.getRowsNumber();
		
		shapeRenderer.setColor(colorGrid);
		for (int i = 0; i < rowsNumber; i++) {
			for (int j = 0; j < columnsNumber; j++) {
				shapeRenderer.rect(game.getGameField().getX() + (j * sizePartSharp),
						game.getGameField().getY() + (i * sizePartSharp), sizePartSharp, sizePartSharp);
			}
		}
		
		shapeRenderer.end();
	}

	private void drawDots() {
		batch.begin();
		List<Dot> dots = game.getGameField().getDots();
		for (Dot dot: dots) {
			batch.draw(spriteDot, dot.getX(), dot.getY());
		}
		
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
