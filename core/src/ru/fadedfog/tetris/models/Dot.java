package ru.fadedfog.tetris.models;

import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.movement.MovementShape;

public class Dot {
	private Rectangle rectangle;
	private MovementShape movement;
	
	public Dot() {
		GameConfig config = GameConfig.getInstance();
		movement = new MovementShape();
		rectangle = new Rectangle();
		rectangle.y = config.getHeightWindow();
		rectangle.x = config.getWidthWindow() / 2;
		rectangle.width = config.getSizePartShap();
		rectangle.height = config.getSizePartShap();
	}

	public void fall() {
		movement.fall(rectangle);
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public int getX() {
		return (int) rectangle.getX();
	}
	
	public void setX(int x) {
		rectangle.x = x;
	}
	
	public int getY() {
		return (int) rectangle.getY();
	}
	
	public void setY(int y) {
		rectangle.y = y;
	}
	
}
