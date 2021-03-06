package ru.fadedfog.tetris.models;

import java.util.Objects;

import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.movement.MovementShape;

public class Dot {
	private Rectangle previousCoord;
	private Rectangle rectangle;
	private MovementShape movement;
	private GameConfig config;
	private int onRow;
	private TypeShape dotInTypeShape;
	
	public Dot() {
		config = GameConfig.getInstance();
		movement = new MovementShape();
		rectangle = new Rectangle();
		rectangle.y = config.getStartYDot();
		onRow = config.getRowsNumber();
		rectangle.x = config.getWidthWindow() / 2;
		rectangle.width = config.getSizePartShape();
		rectangle.height = config.getSizePartShape();
		previousCoord = new Rectangle();
		setPreCoord();
	}
	
	public void setPreCoord() {
		previousCoord.y = rectangle.getY();
		previousCoord.x = rectangle.getX();
	}

	public void fall() {
		movement.fall(rectangle);
	}
	
	public void updateOnRow() {
		int difference = (int) (config.getStartYDot() - getY());
		int numberOfSteps = difference / config.getSizePartShape();
		onRow = config.getRowsNumber() - numberOfSteps;
	}
	
	public void move() {
		movement.move(rectangle);
	}
	
	public Rectangle getPreviousCoord() {
		return previousCoord;
	}

	public void setPreviousCoord(Rectangle previousCoord) {
		this.previousCoord = previousCoord;
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

	public boolean equalsCoords(Dot dot) {
		return this.getRectangle().equals(dot.getRectangle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(config, dotInTypeShape, movement, onRow, previousCoord, rectangle);
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
		return Objects.equals(config, other.config) && dotInTypeShape == other.dotInTypeShape
				&& Objects.equals(movement, other.movement) && onRow == other.onRow
				&& Objects.equals(previousCoord, other.previousCoord) && Objects.equals(rectangle, other.rectangle);
	}

	@Override
	public String toString() {
		return "Dot [rectangle=" + rectangle + ", onRow=" + onRow
				+ "]";
	}

	public TypeShape getDotInTypeShape() {
		return dotInTypeShape;
	}

	public void setDotInTypeShape(TypeShape dotInTypeShape) {
		this.dotInTypeShape = dotInTypeShape;
	}
	
}
