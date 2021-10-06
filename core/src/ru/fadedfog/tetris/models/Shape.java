package ru.fadedfog.tetris.models;

import java.util.Arrays;
import java.util.Objects;

import ru.fadedfog.tetris.movement.MovementShape;

public class Shape implements Cloneable {
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
	
//	
//	public void rotateManual() {
//		movement.rotationManual(this);
//	}
//	
//	public void rotationManual() {
//		movement.rotationManual(this);
//	}
	
	public void changeNumberSide() {
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
		changeNumberSide();
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

	public MovementShape getMovement() {
		return movement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dots);
		result = prime * result + Objects.hash(idMainDot, movement, numberSide, typeShape);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		return Arrays.equals(dots, other.dots) && idMainDot == other.idMainDot
				&& Objects.equals(movement, other.movement) && numberSide == other.numberSide
				&& typeShape == other.typeShape;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape cloneShape = new Shape();
		cloneShape.setTypeShape(this.getTypeShape());
		
		cloneShape.setDots(clonningDots());
		
		cloneShape.setIdMainDot(this.getIdMainDot());
		cloneShape.setNumberSide(this.getNumberSide());
		return cloneShape;
	}
	
	private Dot[] clonningDots() {
		Dot[] cloneDots = new Dot[dots.length];
		for (int i = 0; i < dots.length; i++) {
			Dot dot = new Dot();
			dot.setX(dots[i].getX());
			dot.setY(dots[i].getY());
			cloneDots[i] = dot;
		}
		return cloneDots;
	}

	@Override
	public String toString() {
		return "Shape [typeShape=" + typeShape + ", dots=" + Arrays.toString(dots) + ", idMainDot=" + idMainDot
				+ ", numberSide=" + numberSide + ", movement=" + movement + "]";
	}
	
}
