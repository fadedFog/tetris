package ru.fadedfog.tetris.config;

public class GameConfig {
	private static GameConfig config;
	private final String PATH_CONFIG_FILE = "resources/game_config.json";
	private ReaderConfig readerConnfig;
	private WriterConfig writerConfig;
	private String titleWindow;
	private int widthWindow;
	private int heightWindow;
	private int yWindow;
	private boolean resizableWindow;
	private int sizePartShap;
	private int columnsNumber;
	private int rowsNumber;

	private GameConfig() {
		readerConnfig = new ReaderConfig(PATH_CONFIG_FILE);
		writerConfig = new WriterConfig(PATH_CONFIG_FILE);
		titleWindow = "T e t r i s";
		widthWindow = 336;
		heightWindow = 696;
		yWindow = 1;
		resizableWindow = false;
		sizePartShap = 24;
		columnsNumber = 10;
		rowsNumber = 22;
	}
	
	public static GameConfig getInstance() {
		if (config == null) {
			config = new GameConfig();
		}
		return config;
	}
	
	public void readConfig() {
		readerConnfig.read(this);
	}
	
	public void writeConfig() {
		writerConfig.write(this);
	}
	
	public String getPATH_CONFIG_FILE() {
		return PATH_CONFIG_FILE;
	}

	public WriterConfig getWriterConfig() {
		return writerConfig;
	}

	public void setWriterConfig(WriterConfig writerConfig) {
		this.writerConfig = writerConfig;
	}

	public ReaderConfig getReaderConnfig() {
		return readerConnfig;
	}

	public void setReaderConnfig(ReaderConfig readerConnfig) {
		this.readerConnfig = readerConnfig;
	}

	public static GameConfig getConfig() {
		return config;
	}

	public static void setConfig(GameConfig config) {
		GameConfig.config = config;
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
