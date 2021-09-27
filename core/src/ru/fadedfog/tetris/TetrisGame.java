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
		gameField.getUsedShape().rotate();
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
		collisionFaceShapes(usedShape);
	}
	
	private void collisionBoundsField(Shape usedShape, int sizePartShape) {
		for (Dot dot: usedShape.getDots()) {
			if (isDotCollisionBottomBoundField(dot)) {
				setPrevCoords(usedShape.getDots());
				gameField.setShapeCollisionShape(true);
			}
			
			if (isDotCollisionLeftBound(dot)) {
				setPositionDotsByLeftBound(usedShape, sizePartShape);
				gameField.setShapeCollisionShape(true);
			}
			
			if (isDotCollisionRightBound(dot)) {
				setPositionDotsByRightBound(usedShape, sizePartShape);
				gameField.setShapeCollisionShape(true);
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
	
	private void collisionFaceShapes(Shape usedShape) {
		List<Dot> dotsField = gameField.getDots();
		if (isCollisionFaceOnSameRow(usedShape, dotsField)) {
			for (Dot dot: usedShape.getDots()) {
				dot.setRectangle(dot.getPreviousCoord());
			}
		} else if (isCollisionUpperRow(usedShape, dotsField)) {
			for (Dot dot: usedShape.getDots()) {
				dot.setRectangle(dot.getPreviousCoord());
			}
			gameField.setShapeCollisionShape(true);	
		}
		
		
//		List<Dot> dots = gameField.getDots();
//		for (Dot dot: dots) {
//			if (isCollisionFaseShape(usedShape, dot)) {
//				gameField.setShapeCollisionShape(true);	
//			}
//		}
	}
	
	private int isRowDotBiggerAnother(Dot dot, Dot anotherDot) {
		int result = 0;
		if (dot.getOnRow() > anotherDot.getOnRow()) {
			result = 1;
		} else if (dot.getOnRow() < anotherDot.getOnRow()) {
			result = -1;
		}
		return result;
	}
	
	private boolean isCollisionFaceOnSameRow(Shape usedShape, List<Dot> dotsField) {
		return false;
	}
	
	
	private boolean isCollisionUpperRow(Shape usedShape, List<Dot> dotsField) {
		return false;
	}
	
	private boolean isCollisionFaseShape(Shape usedShape, Dot anotherDot) {
		boolean isCollision = false;
		for (Dot dotShape: usedShape.getDots()) {
			if (!anotherDot.equals(dotShape)) {
				isCollision = anotherDot.getRectangle().overlaps(dotShape.getRectangle());
				break;
			}	
		}
		
		return isCollision;
	}
	
	private void checkingStopShape() {
//		Shape usedShape = gameField.getUsedShape();
//		for (Dot dot: usedShape.getDots()) {
//			if (isWasCollisionBottomBoundField(dot)) {
//				gameField.createNewShape();
//				break;
//			}
//		}
		if (gameField.isShapeCollisionShape()) {
			gameField.createNewShape();
		}
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
