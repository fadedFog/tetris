package ru.fadedfog.tetris.movement;

import com.badlogic.gdx.math.Rectangle;

public class MovementShape {
	
	public void fall(Rectangle areaShape) {
		areaShape.y -= areaShape.height;
	}

}
