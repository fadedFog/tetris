package ru.fadedfog.tetris.models;

import ru.fadedfog.tetris.config.GameConfig;

public enum TypeShape {
	J {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX() - config.getSizePartShap();
			positionsDots[0][1] = mainDot.getY();
			positionsDots[1][0] = mainDot.getX();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[2][0] = mainDot.getX();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap() * 2;
			return positionsDots;
		}
	},
	I {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX();
			positionsDots[0][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[1][0] = mainDot.getX();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap() * 2;
			positionsDots[2][0] = mainDot.getX();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap() * 3;
			return positionsDots;
		}
	},
	O {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX() - config.getSizePartShap();
			positionsDots[0][1] = mainDot.getY();
			positionsDots[1][0] = mainDot.getX() - config.getSizePartShap();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[2][0] = mainDot.getX();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap();
			return positionsDots;
		}
	},
	L {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX() + config.getSizePartShap();
			positionsDots[0][1] = mainDot.getY();
			positionsDots[1][0] = mainDot.getX();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[2][0] = mainDot.getX();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap() * 2;
			return positionsDots;
		}
	},
	Z {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX() + config.getSizePartShap();
			positionsDots[0][1] = mainDot.getY();
			positionsDots[1][0] = mainDot.getX();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[2][0] = mainDot.getX() - config.getSizePartShap();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap();
			return positionsDots;
		}
	},
	T {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX();
			positionsDots[0][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[1][0] = mainDot.getX() - config.getSizePartShap();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[2][0] = mainDot.getX() + config.getSizePartShap();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap();
			return positionsDots;
		}
	},
	S {
		@Override
		public int[][] getDotsPositionsOfShape(Dot mainDot) {
			int[][] positionsDots = initArray();
			positionsDots[0][0] = mainDot.getX() - config.getSizePartShap();
			positionsDots[0][1] = mainDot.getY();
			positionsDots[1][0] = mainDot.getX();
			positionsDots[1][1] = mainDot.getY() + config.getSizePartShap();
			positionsDots[2][0] = mainDot.getX() + config.getSizePartShap();
			positionsDots[2][1] = mainDot.getY() + config.getSizePartShap();
			return positionsDots;
		}
	};
	
	public static final int ROWS_ARRAY_OF_DOTS = 4;
	public static final int SIZE_COORD_DOTS = 2; 
	private static GameConfig config = GameConfig.getInstance();
	
	public abstract int[][] getDotsPositionsOfShape(Dot mainDot);
	
	private static int[][] initArray() {
		return new int[ROWS_ARRAY_OF_DOTS][SIZE_COORD_DOTS];
	}
	
}
