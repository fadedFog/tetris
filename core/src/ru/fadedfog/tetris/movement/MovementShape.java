package ru.fadedfog.tetris.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;

public class MovementShape {
	private final int A_KEY = Input.Keys.A;
	private final int D_KEY = Input.Keys.D;
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
			moveShape(areaShape);
		}
		isAnyKeyNotPressed = !Gdx.input.isKeyPressed(ANY_KEY);
	}
	
	private boolean isKeyAorDPressed() {
		return Gdx.input.isKeyPressed(A_KEY) || Gdx.input.isKeyPressed(D_KEY);
	}
	
	private void  moveShape(Rectangle areaShape) {
		if (isAnyKeyNotPressed) {
			if (Gdx.input.isKeyPressed(A_KEY)) {
				areaShape.x -= config.getSizePartShap();
			}
			if (Gdx.input.isKeyPressed(D_KEY)) {
				areaShape.x += config.getSizePartShap();
			}
		}
	}

}
