package ru.fadedfog.tetris.config;

public class ReaderConfig {
	private String pathFile;

	public ReaderConfig(String pathFile) {
		this.pathFile = pathFile;
	}
	
	public void read(GameConfig config) {
		
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	
}
