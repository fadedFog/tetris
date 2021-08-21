package ru.fadedfog.tetris.models;

import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;

public class Dot {
	private Rectangle rectangle;
	
	public Dot() {
		GameConfig config = GameConfig.getInstance();
		rectangle = new Rectangle();
		rectangle.y = config.getHeightWindow();
		rectangle.x = config.getWidthWindow() / 2;
		rectangle.width = config.getSizePartShap();
		rectangle.height = config.getSizePartShap();
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void reduceY() {
		rectangle.y -= rectangle.height;
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
