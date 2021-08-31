package ru.fadedfog.tetris.models;

import java.util.Objects;

import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.movement.MovementShape;

public class Dot {
	private Rectangle rectangle;
	private MovementShape movement;
	private GameConfig config;
	private int onRow;
	
	public Dot() {
		config = GameConfig.getInstance();
		movement = new MovementShape();
		rectangle = new Rectangle();
		rectangle.y = config.getStartYDot();
		onRow = config.getRowsNumber();
		rectangle.x = config.getWidthWindow() / 2;
		rectangle.width = config.getSizePartShap();
		rectangle.height = config.getSizePartShap();
	}

	public void fall() {
		movement.fall(rectangle);
		updateOnRow();
	}
	
	public void updateOnRow() {
		int difference = (int) (config.getStartYDot() - getY());
		int numberOfSteps = difference / config.getSizePartShap();
		onRow = config.getRowsNumber() - numberOfSteps;
	}
	
	public void move() {
		movement.move(rectangle);
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

	public int getOnRow() {
		return onRow;
	}

	public void setOnRow(int onRow) {
		this.onRow = onRow;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movement, rectangle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dot other = (Dot) obj;
		return Objects.equals(movement, other.movement) && Objects.equals(rectangle, other.rectangle);
	}
	
}
