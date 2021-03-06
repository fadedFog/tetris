package ru.fadedfog.tetris.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.tetris.config.GameConfig;
import ru.fadedfog.tetris.models.Dot;
import ru.fadedfog.tetris.models.Shape;

public class MovementShape {
	private final float VOLUME_SOUND = 0.2f;
	private final int A_KEY = Input.Keys.A;
	private final int D_KEY = Input.Keys.D;
	private final int ENTER_KEY = Input.Keys.ENTER;
	private final int SPACE = Input.Keys.SPACE;
	private final int ANY_KEY = Input.Keys.ANY_KEY;
	private GameConfig config;
	private PositionsDots positionsDots;
	private boolean isAnyKeyNotPressed;
	private long lastTime;
	private final long TIME_FALL = 20;
	private Sound soundOfRotate;
	
	public MovementShape() {
		config = GameConfig.getInstance();
		positionsDots = new PositionsDots();
		lastTime = System.currentTimeMillis();
		soundOfRotate = Gdx.audio.newSound(Gdx.files.internal("sound_rotate.wav"));		
	}
	
	public void rotate(Shape shape) {
		if (isKeyPressed(ENTER_KEY)) {
			rotateShape(shape);
		}
		
		isAnyKeyNotPressed = !Gdx.input.isKeyPressed(ANY_KEY);
	}
	
	private void rotateShape(Shape shape) {
		if (isAnyKeyNotPressed) {
			int[] xPositions = positionsDots.getXPositionsDots(shape.getTypeShape(), 
					shape.getNumberSide());
			int[] yPositions = positionsDots.getYPositionsDots(shape.getTypeShape(), 
					shape.getNumberSide());
			int idMainDot = shape.getIdMainDot();
			Dot mainDot = shape.getDots()[idMainDot];
			Dot[] dotsShape = shape.getDots(); 
			
			for (int i = 0; i < dotsShape.length; i++) {
				dotsShape[i].setX(mainDot.getX() + xPositions[i]);
				dotsShape[i].setY(mainDot.getY() + yPositions[i]);
			}
			
			shape.setNumberSide(shape.getNumberSide() + 1); 
			soundOfRotate.play(VOLUME_SOUND);
		}
	}
	
	public void move(Rectangle areaShape) {
		if (isKeyPressed(A_KEY) || isKeyPressed(D_KEY)) {
			moveShapeLeftRight(areaShape);
		}
		moveDown(areaShape);
		isAnyKeyNotPressed = !Gdx.input.isKeyPressed(ANY_KEY);
	}
	
	private boolean isKeyPressed(int keyCode) {
		return Gdx.input.isKeyPressed(keyCode);
	}
	
	private void  moveShapeLeftRight(Rectangle areaShape) {
		if (isAnyKeyNotPressed) {
			if (Gdx.input.isKeyPressed(A_KEY)) {
				areaShape.x -= config.getSizePartShape();
			}
			if (Gdx.input.isKeyPressed(D_KEY)) {
				areaShape.x += config.getSizePartShape();
			}
		}
	}
	
	private void moveDown(Rectangle areaShape) {
		if (Gdx.input.isKeyPressed(SPACE)) {
			if (System.currentTimeMillis() - lastTime >= TIME_FALL) {
				lastTime = System.currentTimeMillis();
				fall(areaShape);
			}
		}
	}
	
	public void fall(Rectangle areaShape) {
		areaShape.y -= areaShape.height;
	}

	public boolean isAnyKeyNotPressed() {
		return isAnyKeyNotPressed;
	}

	@Override
	public String toString() {
		return "MovementShape [A_KEY=" + A_KEY + ", D_KEY=" + D_KEY + ", ENTER_KEY=" + ENTER_KEY + ", SPACE=" + SPACE
				+ ", ANY_KEY=" + ANY_KEY + ", config=" + config + "]";
	}
	
	

}
