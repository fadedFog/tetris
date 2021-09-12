package ru.fadedfog.tetris.models;

public class Shape {
	private TypeShape typeShape;
	private Dot[] dots;

	public void fall() {
		for (Dot dot: dots) {
			dot.fall();
		}
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
