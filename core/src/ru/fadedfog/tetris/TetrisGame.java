package ru.fadedfog.tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.Dot;
import ru.fadedfog.tetris.models.GameField;
import ru.fadedfog.tetris.models.Shape;
import ru.fadedfog.tetris.movement.PositionsDots;
import ru.fadedfog.tetris.screens.GameScreen;

public class TetrisGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Screen screen;
	private GameField gameField;
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
		rotateShape();
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
	
	private void rotateShape() {
		try {
			checkOverlaps();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();	}
	}
	
	private void checkOverlaps() throws CloneNotSupportedException {
		Shape shape = gameField.getUsedShape();
		Shape cloneShape = (Shape) shape.clone();
		rotateCloneShape(cloneShape);

		List<Dot> dotsWithoutShape = getDotsWithoutShapeDots(shape, gameField.getDots());
		
		if (!isShapeOverlapsDot(cloneShape, dotsWithoutShape)) {
			shape.rotate();
		}
		
	}
	
	private void rotateCloneShape(Shape cloneShape) {
		PositionsDots positionsDots = new PositionsDots();
		int[] xPositions = positionsDots.getXPositionsDots(cloneShape.getTypeShape(), 
				cloneShape.getNumberSide());
		int[] yPositions = positionsDots.getYPositionsDots(cloneShape.getTypeShape(), 
				cloneShape.getNumberSide());
		
		int idMainDot = cloneShape.getIdMainDot();
		Dot mainDot = cloneShape.getDots()[idMainDot];
		Dot[] dotsShape = cloneShape.getDots(); 
		
		for (int i = 0; i < dotsShape.length; i++) {
			dotsShape[i].setX(mainDot.getX() + xPositions[i]);
			dotsShape[i].setY(mainDot.getY() + yPositions[i]);
		}
	}
	
	private boolean isShapeOverlapsDot(Shape shape, List<Dot> dots) {
		for (Dot dot: dots) {
			for (Dot dotShape: shape.getDots()) {
				if (dotShape.equalsCoords(dot)) {
					return true;
				}
			}
		}
		
		return false;
	} 
	
	private void collision() {
		int sizePartShape = config.getSizePartShape();
		Shape usedShape = gameField.getUsedShape();
		collisionBoundsField(usedShape, sizePartShape);
		collisionFaceShapes(usedShape);
	}
	
	private void collisionBoundsField(Shape usedShape, int sizePartShape) {
		
		for (Dot dot: usedShape.getDots()) {
			if (isDotCollisionBottomBoundField(dot)) {
				setPrevCoords(usedShape.getDots());
				if (dot.getY() <= gameField.getY()) {
					gameField.setShapeCollisionShape(true);	
				}
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
			dot.setX((int) dot.getPreviousCoord().getX());
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
		List<Dot> dotsField = getDotsWithoutShapeDots(usedShape, gameField.getDots());
		if (isCollisionUpperRow(usedShape, dotsField)) {
			for (Dot dot: usedShape.getDots()) {
				dot.setY(dot.getY() + config.getSizePartShape());
			}
			
			gameField.setShapeCollisionShape(true);	
		} 
		if (isRightSideShapeCollisionDot(usedShape, dotsField)) {
			changeXDotsShape(usedShape.getDots(), -config.getSizePartShape());
		}
		if (isLeftSideShapeCollisionDot(usedShape, dotsField)) {
			changeXDotsShape(usedShape.getDots(), config.getSizePartShape());
		}
		
	}
	
	private List<Dot> getDotsWithoutShapeDots(Shape shape, List<Dot> dots) {
		List<Integer> idDotShape = new ArrayList<>();
		for (int i = 0; i < shape.getDots().length; i++) {
			for (int j = 0; j < dots.size(); j++) {
				if (shape.getDots()[i].equals(dots.get(j))) {
					idDotShape.add(j);
				}
			}
		}
		
		List<Dot> resultDots = new ArrayList<>();
		for (int i = 0; i < dots.size(); i++) {
			if (!idDotShape.contains(i)) {
				resultDots.add(dots.get(i));
			}
		}
		
		return resultDots;
	}
	
	private boolean isCollisionUpperRow(Shape usedShape, List<Dot> dotsField) {
		boolean isCollision = false;
		for (Dot dot: dotsField) {
			if (isUpperShapeCollisionDot(usedShape, dot)) {
				isCollision = true;
				break;
			}
		}
		return isCollision;
	}
	
	private boolean isUpperShapeCollisionDot(Shape usedShape, Dot dot) {
		boolean isUpperCollision = false;
		for (Dot dotShape: usedShape.getDots()) {
			if (dotShape.getY() == dot.getY() && dotShape.getPreviousCoord().x == dot.getX()) {
				isUpperCollision = true;
			}
			
		}
		return isUpperCollision;
	}
	
	private boolean isRightSideShapeCollisionDot(Shape usedShape, List<Dot> dots) {
		for (Dot dot: dots) {
			for (Dot dotShape: usedShape.getDots()) {
				float xPrevCoord = dotShape.getPreviousCoord().getX();
				if (dotShape.getX() == dot.getX() && xPrevCoord < dot.getX() && dotShape.getPreviousCoord().getY() == dot.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isLeftSideShapeCollisionDot(Shape usedShape, List<Dot> dots) {
		for (Dot dot: dots) {
			for (Dot dotShape: usedShape.getDots()) {
				float xPrevCoord = dotShape.getPreviousCoord().getX();
				if (dotShape.getX() == dot.getX() && xPrevCoord > dot.getX() && dotShape.getPreviousCoord().getY() == dot.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void changeXDotsShape(Dot[] dots, int to) {
		for (Dot dot: dots) {
			dot.setX(dot.getX() + to);
		}
	}
	
	private void checkingStopShape() {
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
