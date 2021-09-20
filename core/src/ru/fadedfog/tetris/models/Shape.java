package ru.fadedfog.tetris.models;

import ru.fadedfog.tetris.movement.MovementShape;

public class Shape {
	private TypeShape typeShape;
	private Dot[] dots;
	private int idMainDot;
	private int numberSide;
	private MovementShape movement;
	
	public Shape() {
		movement = new MovementShape();
	}

	public void fall() {
		for (Dot dot: dots) {
			dot.fall();
		}
	}
	
	public void move() {
		for (Dot dot: dots) {
			dot.move();
		}
	}
	
	public void rotate() {
		movement.rotate(this);
		changeNumberSide();
	}
	
	private void changeNumberSide() {
		int countNumberSide = typeShape.getCountNumberSides();
		if (numberSide > countNumberSide) {
			numberSide = 1;
		}
	}
	
	public int getIdMainDot() {
		return idMainDot;
	}
	
	public void setIdMainDot(int idMainDot) {
		this.idMainDot = idMainDot;
	}
	
	public int getNumberSide() {
		return numberSide;
	}
	
	public void setNumberSide(int numberSide) {
		this.numberSide = numberSide;
	}
	
	public TypeShape getTypeShape() {
		return typeShape;
	}

	public void setTypeShape(TypeShape typeShape) {
		this.typeShape = typeShape;
	}

	public Dot[] getDots() {
		return dots;
	}

	public void setDots(Dot[] dots) {
		this.dots = dots;
	}

}
