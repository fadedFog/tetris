package ru.fadedfog.tetris.models;

public class ShapeFactory {
	
	public Shape createShape(TypeShape type) {
		Shape shape = new Shape();
		shape.setTypeShape(type);
		shape.setNumberSide(2);
		Dot[] dotsOfShape = getStateDotsOfShape(type);
		shape.setDots(dotsOfShape);
		setIdMainDot(shape);
		return shape;
	}
	
	private void setIdMainDot(Shape shape) {
		shape.setIdMainDot(0);
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

		Dot[] resultDots = new Dot[dotsOfShape.length];
		resultDots[0] = dotsOfShape[dotsOfShape.length - 1];
		for (int i = 1; i < resultDots.length; i++) {
			resultDots[i] = dotsOfShape[i - 1];
		}
		
		return  resultDots;
	}

}
