package ru.fadedfog.tetris.config;


public class GameConfig {
	private static GameConfig gameConfig;
	private final String PATH_CONFIG_FILE = "src/ru/fadedfog/tetris/resources/game_config.json";
	private ReaderConfig readerConnfig;
	private WriterConfig writerConfig;
	private Config config;

	private GameConfig() {
		readerConnfig = new ReaderConfig(PATH_CONFIG_FILE);
		writerConfig = new WriterConfig(PATH_CONFIG_FILE);
		config = new Config();
	}
	
	public static GameConfig getInstance() {
		if (gameConfig == null) {
			gameConfig = new GameConfig();
		}
		return gameConfig;
	}
	
	public void readConfig() {
		config = readerConnfig.read(this);
	}
	
	public void writeConfig() {
		writerConfig.write(config);
	}
	
	public float getStartYDot() {
		return config.getHeightWindow() - config.getSizePartShap();
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

	public String getTitleWindow() {
		return config.getTitleWindow();
	}

	public void setTitleWindow(String title) {
		config.setTitleWindow(title);
	}

	public int getWidthWindow() {
		return config.getWidthWindow();
	}

	public void setWidthWindow(int widthWindow) {
		config.setWidthWindow(widthWindow);
	}

	public int getHeightWindow() {
		return config.getHeightWindow();
	}

	public void setHeightWindow(int heightWindow) {
		config.setHeightWindow(heightWindow);
	}

	public int getyWindow() {
		return config.getyWindow();
	}

	public void setyWindow(int yWindow) {
		config.setyWindow(yWindow);
	}

	public boolean isResizableWindow() {
		return config.isResizableWindow();
	}

	public void setResizableWindow(boolean resizableWindow) {
		config.setResizableWindow(resizableWindow);
	}

	public int getSizePartShape() {
		return config.getSizePartShap();
	}

	public void setSizePartShap(int sizePartShap) {
		config.setSizePartShap(sizePartShap);
	}

	public int getColumnsNumber() {
		return config.getColumnsNumber();
	}

	public void setColumnsNumber(int columnsNumber) {
		config.setColumnsNumber(columnsNumber);
	}

	public int getRowsNumber() {
		return config.getRowsNumber();
	}

	public void setRowsNumber(int rowsNumber) {
		config.setRowsNumber(rowsNumber);
	}
	
	public int getxGameField() {
		return config.getxGameField();
	}

	public void setxGameField(int xGameField) {
		config.setxGameField(xGameField);
	}

	public int getyGameField() {
		return config.getyGameField();
	}

	public void setyGameField(int yGameField) {
		config.setyGameField(yGameField);
	}

	public int getWidthGameField() {
		return config.getWidthGameField();
	}

	public void setWidthGameField(int widthGameField) {
		config.setWidthGameField(widthGameField);
	}

	public int getHeightGameField() {
		return config.getHeightGameField();
	}

	public void setHeightGameField(int heightGameField) {
		config.setHeightWindow(heightGameField);
	}
	
}
