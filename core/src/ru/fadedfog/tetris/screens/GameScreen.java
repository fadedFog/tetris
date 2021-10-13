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
	private Texture spriteDotRed;
	private Texture spriteDotGreen;
	private Texture spriteDotBlue;
	private Texture spriteDotOrange;
	private Texture spriteDotCaye;
	private Texture spriteDotPink;
	private Texture spriteDotYellow;
	private Texture spriteScore;
	private Texture[] spritesNumbers;
	private Texture spriteMultiple;
	private int[] score;
	private final int SIZE_SCORE_ARRAY = 9;
	private ShapeRenderer shapeRenderer;
	
	public GameScreen(TetrisGame game) {
		this.game = game;
		config = GameConfig.getInstance();
		batch = game.getBatch();
		shapeRenderer = new ShapeRenderer();
		createSprites();
		score = new int[SIZE_SCORE_ARRAY];
	}
	
	private void createSprites() {
		initSpritesField();
		spriteScore = new Texture(Gdx.files.internal("score.png"));
		initSpritesNumbers();
		spriteMultiple = new Texture(Gdx.files.internal("x.png"));
	}
	
	private void initSpritesField() {
		spriteBorderField = new Texture(Gdx.files.internal("border_field.png"));
		spriteDotRed = new Texture(Gdx.files.internal("dot_red.png"));
		spriteDotGreen = new Texture(Gdx.files.internal("dot_green.png"));
		spriteDotBlue = new Texture(Gdx.files.internal("dot_blue.png"));
		spriteDotOrange = new Texture(Gdx.files.internal("dot_orange.png"));
		spriteDotCaye = new Texture(Gdx.files.internal("dot_caye.png"));
		spriteDotPink = new Texture(Gdx.files.internal("dot_pink.png"));
		spriteDotYellow = new Texture(Gdx.files.internal("dot_yellow.png"));
	}
	
	private void initSpritesNumbers() {
		spritesNumbers = new Texture[10];
		for (int i = 0; i < spritesNumbers.length; i++) {
			String nameSpreite = "n" + i + ".png";
			spritesNumbers[i] = new Texture(Gdx.files.internal(nameSpreite));
		}
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		drawBoundField();
		drawGridField();
		drawDots();
		drawScore();
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
		int sizePartSharp = config.getSizePartShape(); 
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
			Texture spriteDot = chooseSpriteDotByTypeShape(dot);
			batch.draw(spriteDot, dot.getX(), dot.getY());
		}
		
		batch.end();
	}
	
	private Texture chooseSpriteDotByTypeShape(Dot dot) {
		Texture sprite;
		switch (dot.getDotInTypeShape()) {
			case I:
				sprite = spriteDotCaye;
				break;
			
			case J:
				sprite = spriteDotPink;
				break;

			case L:
				sprite = spriteDotBlue;
				break;

			case T:
				sprite = spriteDotRed;
				break;

			case S:
				sprite = spriteDotYellow;
				break;

			case Z:
				sprite = spriteDotOrange;
				break;
			
			default:
				sprite = spriteDotGreen;
				break;
		}
		
		return sprite;
	}
	
	private void drawScore() { // TODO from config file
		int xScore = config.getxGameField() - 36;
		int yScore = game.getGameField().getY() - 24;
		int widthScore = spriteScore.getWidth() / 2;
		int heightScore = spriteScore.getHeight() / 2;
		int xNumber = xScore + widthScore;
		int widthNumber = spritesNumbers[0].getWidth() / 2;
		int heightumber = spritesNumbers[0].getHeight() / 2;
		
		batch.begin();
		batch.draw(spriteScore, xScore,  yScore,
				widthScore, heightScore);
		
		updateScoreGame();
		for (int i = 0; i < score.length; i++) {
			Texture spriteNumber = spritesNumbers[score[i]];
			batch.draw(spriteNumber, xNumber + 12 * (i + 1), yScore, 
					widthNumber, heightumber);
		}
		
		drawCombo((float) (xNumber + 12 * 11), yScore);

		batch.end();
	}
	
	private void drawCombo(float xLastNuber, float yScore) { // TODO from config file
		Texture spriteCombo = spritesNumbers[game.getPubCombo()];
		float widthMultiply = (float)(spriteMultiple.getWidth() / 1.7);
		float heightMultiply = (float)(spriteMultiple.getHeight() / 1.7);
		float widthCombo = (float)(spriteCombo.getWidth() / 1.7);
		float heightCombo = (float)(spriteCombo.getHeight() / 1.7);
		
		batch.draw(spriteMultiple, xLastNuber, yScore, widthMultiply, heightMultiply);
		batch.draw(spriteCombo, xLastNuber + 15, yScore, widthCombo, heightCombo);
	}
	
	private void updateScoreGame() {
		String scoreChars = String.valueOf(game.getScoreGame());
		if (scoreChars.length() > 9) {
			game.setScoreGame(0);
			scoreChars = String.valueOf(game.getScoreGame());
		}
		
		int[] numbersScore = convertScoreCharsToArrayScore(scoreChars);
		
		for (int i = score.length - numbersScore.length, j = 0; i < score.length; i++, j++) {
			score[i] = numbersScore[j];
		}
	}
	
	private int[] convertScoreCharsToArrayScore(String scoreChars) {
		int[] result = new int[scoreChars.length()];
		String[] chars = scoreChars.split("");
		
		for (int i = 0; i < chars.length; i++) {
			result[i] = Integer.parseInt(chars[i]);
		}
		
		return result;
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
