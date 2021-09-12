package ru.fadedfog.tetris.models;

public class ShapeFactory {
	
	public Shape createShape(TypeShape type) {
		Shape shape = new Shape();
		shape.setTypeShape(type);
		Dot[] dotsOfShape = getStateDotsOfShape(type);
		shape.setDots(dotsOfShape);
		return shape;
	}
	
	private Dot[] getStateDotsOfShape(TypeShape type) {
		Dot mainDot = new Dot();

		int[][] dotsPositionsOfShape = type.getDotsPositionsOfShape(mainDot);
		Dot[] dotsOfShape = new Dot[dotsPositionsOfShape.length];
		dotsOfShape[0] = mainDot;
		for (int i = 0; i < dotsPositionsOfShape.length; i++) {
			Dot dot = new Dot();
			dot.setX(dotsPositionsOfShape[i][0]);
			dot.setY(dotsPositionsOfShape[i][1]);
			dotsOfShape[i] = dot;
		}
		dotsOfShape[dotsOfShape.length - 1] = mainDot;

		return  dotsOfShape;
	}

}
