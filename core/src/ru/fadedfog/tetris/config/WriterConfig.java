package ru.fadedfog.tetris.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriterConfig {
	private String pathFile;

	public WriterConfig(String pathFile) {
		this.pathFile = pathFile;
	}
	
	public void write(Config config) {
		File file = new File(pathFile);
		if (!file.exists()) {
			file.mkdir();
		}
		
		try (Writer writer = new FileWriter(file, false)) {
			writeToFile(config, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeToFile(Config config, Writer writer) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();
		gson.toJson(config, writer);
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
}
