package ru.fadedfog.tetris.config;


public class ReaderConfig {
	private String pathFile;

	public ReaderConfig(String pathFile) {
		this.pathFile = pathFile;
	}
	
	public Config read(GameConfig gameConfig) {
		return null;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	
}
