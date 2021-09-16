package ru.fadedfog.tetris;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.Dot;
import ru.fadedfog.tetris.models.GameField;
import ru.fadedfog.tetris.models.Shape;
import ru.fadedfog.tetris.screens.GameScreen;

public class TetrisGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Screen screen;
	private GameField gameField;
	private Rectangle dotOfPast; 
	private GameConfig config;
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
		gameField.createNewShape();
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(0, 0, 0, 1);
		screen.render(1);
	}
	
	private void update() {
		setPrevCoords();
		fallShape();
		gameField.getUsedShape().move();
		collision();
		checkingStopShape();
	}
	
	private void setPrevCoords() {
		for (Dot dot: gameField.getUsedShape().getDots()) {
			dot.setPreCoord();
		}
	}
	
	private void fallShape() {
		if (didTimedPass(1)) {
			gameField.getUsedShape().fall();
			lastTime = System.currentTimeMillis();
		}
	}
	
	private boolean didTimedPass(int second) {
		return (System.currentTimeMillis() - lastTime) / 1000 >= second;
	}
	
	private void collision() {
		int sizePartShape = config.getSizePartShap();
		Shape usedShape = gameField.getUsedShape();
		collisionBoundsField(usedShape, sizePartShape);
//		collisionFaceShapes(usedDot);
	}
	
	private void collisionBoundsField(Shape usedShape, int sizePartShape) {
		for (Dot dot: usedShape.getDots()) {
			if (isDotCollisionBottomBoundField(dot)) {
				setPrevCoords(usedShape.getDots());
			}
			
			if (isDotCollisionLeftBound(dot)) {
				setPositionDotsByLeftBound(usedShape, sizePartShape);
			}
			
			if (isDotCollisionRightBound(dot)) {
				setPositionDotsByRightBound(usedShape, sizePartShape);
			}
 		}
	}
	
	private boolean isDotCollisionBottomBoundField(Dot usedDot) {
		return usedDot.getY() < gameField.getY();
	}
	
	private void setPrevCoords(Dot[] dots) {
		for (Dot dot: dots) {
			dot.setY((int) dot.getPreviousCoord().getY());
		}
	}
	
	private boolean isDotCollisionLeftBound(Dot dot) {
		return dot.getX() < gameField.getX();
	}
	
	private void setPositionDotsByLeftBound(Shape usedShape, int sizePartShape) {
		for (Dot dot: usedShape.getDots()) {
			dot.setX(dot.getX() + sizePartShape);
		}
	}
	
	private boolean isDotCollisionRightBound(Dot dot) {
		return dot.getX() >= gameField.getX() + config.getWidthGameField();
	}
	
	private void setPositionDotsByRightBound(Shape usedShape, int sizePartShape) {
		for (Dot dot: usedShape.getDots()) {
			dot.setX(dot.getX() - sizePartShape);
		}
	}
	
	private void collisionBoundsField(Dot usedDot, int sizePartShape) {
		if (usedDot.getX() - sizePartShape < gameField.getX()) {
			usedDot.setX(gameField.getX());
		}
		if (usedDot.getX() >= gameField.getX() + gameField.getWidth()) {
			usedDot.setX(gameField.getX() + gameField.getWidth() - sizePartShape);
		}
		if (isDotCollisionBottomBoundField(usedDot)) {
			usedDot.setY(gameField.getY());
		}
	}
	
	private void collisionFaceShapes(Dot usedDot) {
		List<Dot> dots = gameField.getDots();
		for (Dot dot: dots) {
			if (isCollisionFaseShape(usedDot, dot)) {
				if (dot.getOnRow() < usedDot.getOnRow()) {
					dotOfPast.setY(dotOfPast.getY());
					usedDot.setRectangle(dotOfPast);
					gameField.setShapeCollisionShape(true);	
				} else {
					usedDot.setRectangle(dotOfPast);
				}
			}
		}
		
	}
	
	private boolean isCollisionFaseShape(Dot usedDot, Dot anotherDot) {
		boolean isCollision = false;
		if (!anotherDot.equals(usedDot)) {
			isCollision = anotherDot.getRectangle().overlaps(usedDot.getRectangle());
		}
		return isCollision;
	}
	
	private void checkingStopShape() {
		Shape usedShape = gameField.getUsedShape();
		for (Dot dot: usedShape.getDots()) {
			if (isWasCollisionBottomBoundField(dot)) {
				gameField.createNewShape();
				break;
			}
		}
	}
	
	private boolean isWasCollisionBottomBoundField(Dot dot) {
		return dot.getY() == gameField.getY();
	}
	
	private void removeRowOfDots() {
		Map<Integer, List<Dot>> rowsOfDots = gameField.getRowsOfDots();
		for (Map.Entry<Integer, List<Dot>> row: rowsOfDots.entrySet()) {
			if (row.getValue().size() == config.getColumnsNumber()) {
				removeDots(row.getValue());
			}
		}
	}
	
	private void removeDots(List<Dot> removeDots) {
		for (Dot dot: removeDots) {
			gameField.getDots().remove(dot);
		}
		fallAllDots();
	}
	
	private void fallAllDots() {
		for (Dot dot: gameField.getDots()) {
			dot.fall();
			dot.updateOnRow();
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

}
