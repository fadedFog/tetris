package ru.fadedfog.tetris.config;

public class WriterConfig {
	private String pathFile;

	public WriterConfig(String pathFile) {
		this.pathFile = pathFile;
	}
	
	public void write(GameConfig config) {
		
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
}
