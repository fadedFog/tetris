package ru.fadedfog.tetris.movement;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.TypeShape;

public class PositionsDots {
	private GameConfig config;
	
	public PositionsDots() {
		config = GameConfig.getInstance();
	}
	
	public int[] getYPositionsDots(TypeShape typeShape, int numberSide) {
		int[] yPositions = new int[4];
		switch (typeShape) {
			case I:
				if (numberSide == 1) {
					yPositions[0] = 0;
					for (int i = 1; i < yPositions.length; i++) {
						yPositions[i] = yPositions[i - 1] + config.getSizePartShap();
					}
				} else {
					for (int i = 0; i < yPositions.length; i++) {
						yPositions[i] = 0;
					}
				}
				break;
			case J:
				break;
			case L:
				break;
			case S:
				break;
			case Z:
				break;
			case T:
				break;
		}
		return yPositions;
	}

	public int[] getXPositionsDots(TypeShape typeShape, int numberSide) {
		int[] xPositions = new int[4];
		switch (typeShape) {
			case I:
				if (numberSide == 1) {
					for (int i = 0; i < xPositions.length; i++) {
						xPositions[i] = 0;
					}
				} else {
					for (int i = 1; i < xPositions.length; i++) {
						xPositions[i] = xPositions[i - 1]  + config.getSizePartShap();
					}
				}
				break;
			case J:
				break;
			case L:
				break;
			case S:
				break;
			case Z:
				break;
			case T:
				break;
		}
		return xPositions;
	}
	
}
