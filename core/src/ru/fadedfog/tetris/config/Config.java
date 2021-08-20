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
	
	public Config() {
		titleWindow = "T e t r i s";
		widthWindow = 336;
		heightWindow = 696;
		yWindow = 1;
		resizableWindow = false;
		sizePartShap = 24;
		columnsNumber = 10;
		rowsNumber = 22;
	}
	
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
}
