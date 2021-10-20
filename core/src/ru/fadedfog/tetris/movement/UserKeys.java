package ru.fadedfog.tetris.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class UserKeys {
	private final int ESC_KEY = Input.Keys.ESCAPE;
	
	public boolean isEscPressed() {
		return Gdx.input.isKeyJustPressed(ESC_KEY);
	}
	
}
