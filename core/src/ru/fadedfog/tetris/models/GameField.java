package ru.fadedfog.tetris.models;

import com.badlogic.gdx.math.Rectangle;

public class GameField {
	private Rectangle areaRectangle;
	
	public GameField() {
		areaRectangle = new Rectangle(48, 168, 240, 504);
	}

	public Rectangle getAreaRectangle() {
		return areaRectangle;
	}

	public void setAreaRectangle(Rectangle areaRectangle) {
		this.areaRectangle = areaRectangle;
	}

	public int getX() {
		return (int) areaRectangle.getX();
	}
	
	public void setX(int x) {
		areaRectangle.x = x;
	}
	
	public int getY() {
		return (int) areaRectangle.getY();
	}
	
	public void setY(int y) {
		areaRectangle.y = y;
	}
	
	public int getWidth() {
		return (int) areaRectangle.getWidth();
	}
	
	public void setWidth(int width) {
		areaRectangle.width = width;
	}
	
	public int getHeight() {
		return (int) areaRectangle.getHeight();
	}
	
	public void setHeight(int height) {
		areaRectangle.height = height;
	}
	
}
