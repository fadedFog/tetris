package ru.fadedfog.tetris.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;

public class MovementShape {
	private final int A_KEY = Input.Keys.A;
	private final int D_KEY = Input.Keys.D;
	private final int SPACE = Input.Keys.SPACE;
	private final int ANY_KEY = Input.Keys.ANY_KEY;
	private GameConfig config;
	private boolean isAnyKeyNotPressed;
	
	public MovementShape() {
		config = GameConfig.getInstance();
	}
	
	public void fall(Rectangle areaShape) {
		areaShape.y -= areaShape.height;
	}
	
	public void move(Rectangle areaShape) {
		if (isKeyAorDPressed()) { 
			moveShapeLeftRight(areaShape);
		}
		moveDown(areaShape);
		isAnyKeyNotPressed = !Gdx.input.isKeyPressed(ANY_KEY);
	}
	
	private boolean isKeyAorDPressed() {
		return Gdx.input.isKeyPressed(A_KEY) || Gdx.input.isKeyPressed(D_KEY);
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
	
	

}
