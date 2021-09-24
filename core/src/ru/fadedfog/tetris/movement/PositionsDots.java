package ru.fadedfog.tetris.movement;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.TypeShape;

public class PositionsDots {
	private GameConfig config;
	private int sizeDot;
	
	public PositionsDots() {
		config = GameConfig.getInstance();
		sizeDot = config.getSizePartShap();
	}
	
	public int[] getYPositionsDots(TypeShape typeShape, int numberSide) {
		int[] yPositions = new int[4];
		yPositions[0] = 0;
		switch (typeShape) {
			case I:
				if (numberSide == 1) {
					for (int i = 1; i < yPositions.length; i++) {
						yPositions[i] = yPositions[i - 1] + sizeDot;
					}
				} else {
					for (int i = 0; i < yPositions.length; i++) {
						yPositions[i] = 0;
					}
				}
				break;
			case J:
				if (numberSide == 1) {
					setValuesInPositions(yPositions, 0, sizeDot, sizeDot * 2);
				} else if (numberSide == 2) {
					setValuesInPositions(yPositions, -sizeDot, 0, 0);
				} else if (numberSide == 3) {
					setValuesInPositions(yPositions, 0, -sizeDot, -sizeDot * 2);
				} else if (numberSide == 4) {
					setValuesInPositions(yPositions, sizeDot, 0, 0);
				}
				break;
			case L:
				if (numberSide == 1) {
					setValuesInPositions(yPositions, 0, sizeDot, sizeDot * 2);
				} else if (numberSide == 2) {
					setValuesInPositions(yPositions, sizeDot, 0, 0);
				} else if (numberSide == 3) {
					setValuesInPositions(yPositions, 0, -sizeDot, -sizeDot * 2);
				} else if (numberSide == 4) {
					setValuesInPositions(yPositions, -sizeDot, 0, 0);
				}
				break;
			case S:
				break;
			case Z:
				if (numberSide == 1) {
					setValuesInPositions(yPositions, 0, 24, 24);
				} else if (numberSide == 2) {
					setValuesInPositions(yPositions, 24, 0, -24);
				}
				break;
			case T:
				if (numberSide == 1) {
					setValuesInPositions(yPositions, sizeDot, sizeDot, sizeDot);
				} else if (numberSide == 2 || numberSide == 4) {
					setValuesInPositions(yPositions, sizeDot, 0, -sizeDot);
				} else if (numberSide == 3) {
					setValuesInPositions(yPositions, -sizeDot, -sizeDot, -sizeDot);
				}

				break;
		}
		return yPositions;
	}
	
	private void setValuesInPositions(int[] positions, int ... position) {
		for (int i = 1; i < positions.length; i++) {
			positions[i] = position[i - 1];
		}
	}

	public int[] getXPositionsDots(TypeShape typeShape, int numberSide) {
		int[] xPositions = new int[4];
		xPositions[0] = 0;
		switch (typeShape) {
			case I:
				if (numberSide == 1) {
					for (int i = 0; i < xPositions.length; i++) {
						xPositions[i] = 0;
					}
				} else {
					for (int i = 1; i < xPositions.length; i++) {
						xPositions[i] = xPositions[i - 1]  + sizeDot;
					}
				}
				break;
			case J:
				if (numberSide == 1) {
					setValuesInPositions(xPositions, -sizeDot, 0, 0);
				} else if (numberSide == 2) {
					setValuesInPositions(xPositions, 0, -sizeDot, -sizeDot * 2);
				} else if (numberSide == 3) {
					setValuesInPositions(xPositions, sizeDot, 0, 0);
				} else if (numberSide == 4) {
					setValuesInPositions(xPositions, 0, sizeDot, sizeDot * 2);
				}
				break;
			case L:
				if (numberSide == 1) {
					setValuesInPositions(xPositions, sizeDot, 0, 0);
				} else if (numberSide == 2) {
					setValuesInPositions(xPositions, 0, -sizeDot, -sizeDot * 2);
				} else if (numberSide == 3) {
					setValuesInPositions(xPositions, -sizeDot, 0, 0);
				} else if (numberSide == 4) {
					setValuesInPositions(xPositions, 0, sizeDot, sizeDot * 2);
				}
				break;
			case S:
				break;
			case Z:
				if (numberSide == 1) {
					setValuesInPositions(xPositions, 24, 0, -24);
				} else if (numberSide == 2) {
					setValuesInPositions(xPositions, 0, -24, -24);
				}
				break;
			case T:
				if (numberSide == 1 || numberSide == 3) {
					setValuesInPositions(xPositions, -sizeDot, 0, sizeDot);
				} else if (numberSide == 2) {
					setValuesInPositions(xPositions, -sizeDot, -sizeDot, -sizeDot);
				} else if (numberSide == 4) {
					setValuesInPositions(xPositions, sizeDot, sizeDot, sizeDot);
				}
				break;
		}
		return xPositions;
	}
	
}
