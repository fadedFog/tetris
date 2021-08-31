package ru.fadedfog.tetris.models;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;

public class GameField {
	private Rectangle areaRectangle;
	private GameConfig config;
	private List<Dot> dots;
	private boolean isShapeCollisionShape;
	
	public GameField() {
		config = GameConfig.getInstance();
		areaRectangle = new Rectangle(config.getxGameField(), config.getyGameField(), 
				config.getWidthGameField(), config.getHeightGameField());
		dots = new LinkedList<>();
	}

	public void createNewShape() {
		dots.add(new Dot());
		isShapeCollisionShape = false;
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

	public List<Dot> getDots() {
		return dots;
	}

	public void setDots(List<Dot> dots) {
		this.dots = dots;
	}

	public Dot getUsedDot() {
		return dots.get(dots.size() - 1);
	}

	public boolean isShapeCollisionShape() {
		return isShapeCollisionShape;
	}

	public void setShapeCollisionShape(boolean isShapeCollisionShape) {
		this.isShapeCollisionShape = isShapeCollisionShape;
	}
	
}
