package ru.fadedfog.tetris.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.Dot;
import ru.fadedfog.tetris.models.Shape;

public class MovementShape {
	private final int A_KEY = Input.Keys.A;
	private final int D_KEY = Input.Keys.D;
	private final int ENTER_KEY = Input.Keys.ENTER;
	private final int SPACE = Input.Keys.SPACE;
	private final int ANY_KEY = Input.Keys.ANY_KEY;
	private GameConfig config;
	private PositionsDots positionsDots;
	private boolean isAnyKeyNotPressed;
	
	public MovementShape() {
		config = GameConfig.getInstance();
		positionsDots = new PositionsDots();
	}
	
	public void rotate(Shape shape) {
		if (isKeyPressed(ENTER_KEY)) {
			rotateShape(shape);
		}
		
		isAnyKeyNotPressed = !Gdx.input.isKeyPressed(ANY_KEY);
	}
	
	private void rotateShape(Shape shape) {
		if (isAnyKeyNotPressed) {
			
			int[] xPositions = positionsDots.getXPositionsDots(shape.getTypeShape(), 
					shape.getNumberSide());
			int[] yPositions = positionsDots.getYPositionsDots(shape.getTypeShape(), 
					shape.getNumberSide());
			int idMainDot = shape.getIdMainDot();
			Dot mainDot = shape.getDots()[idMainDot];
			Dot[] dotsShape = shape.getDots(); 
			
			for (int i = 0; i < dotsShape.length; i++) {
				dotsShape[i].setX(mainDot.getX() + xPositions[i]);
				dotsShape[i].setY(mainDot.getY() + yPositions[i]);
			}
			
			shape.setNumberSide(shape.getNumberSide() + 1);
		}
	}
	
	public void move(Rectangle areaShape) {
		if (isKeyPressed(A_KEY) || isKeyPressed(D_KEY)) {
			moveShapeLeftRight(areaShape);
		}
		moveDown(areaShape);
		isAnyKeyNotPressed = !Gdx.input.isKeyPressed(ANY_KEY);
	}
	
	private boolean isKeyPressed(int keyCode) {
		return Gdx.input.isKeyPressed(keyCode);
	}
	
	private void  moveShapeLeftRight(Rectangle areaShape) {
		if (isAnyKeyNotPressed) {
			if (Gdx.input.isKeyPressed(A_KEY)) {
				areaShape.x -= config.getSizePartShap();
			}
			if (Gdx.input.isKeyPressed(D_KEY)) {
				areaShape.x += config.getSizePartShap();
			}
		}
	}
	
	private void moveDown(Rectangle areaShape) {
		if (Gdx.input.isKeyPressed(SPACE)) {
			fall(areaShape);
		}
	}
	
	public void fall(Rectangle areaShape) {
		areaShape.y -= areaShape.height;
	}

}
