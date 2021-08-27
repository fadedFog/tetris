package ru.fadedfog.tetris.models;

import java.util.Objects;

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
		rectangle.y = config.getHeightWindow() - config.getSizePartShap();
		rectangle.x = config.getWidthWindow() / 2;
		rectangle.width = config.getSizePartShap();
		rectangle.height = config.getSizePartShap();
	}

	public void fall() {
		movement.fall(rectangle);
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
