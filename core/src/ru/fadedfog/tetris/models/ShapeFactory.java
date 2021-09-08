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
		Dot[] dotsOfShape = null;
		
		switch(type) {
			case J:
				break;
			case I:
				break;
			case O:
				break;
			case L:
				break;
			case Z:
				break;
			case T:
				break;
			case S:
				break;
		}
		
		return  dotsOfShape;
	}

}
