package ru.fadedfog.tetris.config;

public class Config {
	private String titleWindow;
	private int widthWindow;
	private int heightWindow;
	private int yWindow;
	private boolean resizableWindow;
	private int sizePartShap;
	private int columnsNumber;
	private int rowsNumber;
	private int xGameField;
	private int yGameField;
	private int widthGameField;
	private int heightGameField;
	
	public String getTitleWindow() {
		return titleWindow;
	}

	public void setTitleWindow(String title) {
		this.titleWindow = title;
	}

	public int getWidthWindow() {
		return widthWindow;
	}

	public void setWidthWindow(int widthWindow) {
		this.widthWindow = widthWindow;
	}

	public int getHeightWindow() {
		return heightWindow;
	}

	public void setHeightWindow(int heightWindow) {
		this.heightWindow = heightWindow;
	}

	public int getyWindow() {
		return yWindow;
	}

	public void setyWindow(int yWindow) {
		this.yWindow = yWindow;
	}

	public boolean isResizableWindow() {
		return resizableWindow;
	}

	public void setResizableWindow(boolean resizableWindow) {
		this.resizableWindow = resizableWindow;
	}

	public int getSizePartShap() {
		return sizePartShap;
	}

	public void setSizePartShap(int sizePartShap) {
		this.sizePartShap = sizePartShap;
	}

	public int getColumnsNumber() {
		return columnsNumber;
	}

	public void setColumnsNumber(int columnsNumber) {
		this.columnsNumber = columnsNumber;
	}

	public int getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public int getxGameField() {
		return xGameField;
	}

	public void setxGameField(int xGameField) {
		this.xGameField = xGameField;
	}

	public int getyGameField() {
		return yGameField;
	}

	public void setyGameField(int yGameField) {
		this.yGameField = yGameField;
	}

	public int getWidthGameField() {
		return widthGameField;
	}

	public void setWidthGameField(int widthGameField) {
		this.widthGameField = widthGameField;
	}

	public int getHeightGameField() {
		return heightGameField;
	}

	public void setHeightGameField(int heightGameField) {
		this.heightGameField = heightGameField;
	}

	public int getxFieldFututreShape() {
		return xGameField - 4 - 32;
	}

	public int getyFieldFututreShape() {
		return yGameField + heightGameField - 24;
	}

	public int getxScore() {
		return xGameField - 36;
	}

	public int getyScore() {
		return yGameField - 24;
	}
	
}
