package de.marcel.game.enginge.joystick;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import de.marcel.game.enginge.GameEngine;
import de.marcel.game.enginge.Rechnung;
import de.marcel.game.enginge.Viewable;
import de.marcel.game.enginge.touchscreen.TouchscreenEventListener;
import de.marcel.game.R;

public class JoystickPad extends Viewable implements TouchscreenEventListener {
	
	private int joystickZuPadVerhaeltniss = 50; // in %
	public Stick stick;
	public int touchPointerID = -20;
	public int id;
	public double stickX = 0;
	public double stickY = 0;
	
	
	
	public JoystickPad(int anteilX, int anteilY, int anzeilSize, int id) {		// in %
		super(	Rechnung.anteil(GameEngine.graphicalDisplayPixelsX, anteilX), 
				Rechnung.anteil(GameEngine.graphicalDisplayPixelsY, anteilY), 
				Rechnung.anteil(GameEngine.graphicalDisplayPixelsY, anzeilSize), 
				Rechnung.anteil(GameEngine.graphicalDisplayPixelsY, anzeilSize), 
				R.drawable.joystick_hintergrund, 
				GameEngine.activity);
		this.id = id;
		stick = new Stick(this);
		view();
		Log.i("GameEngine", "Rechnung.anteil(GameEngine.displayPixelsX (" + GameEngine.graphicalDisplayPixelsX + ") , anteilX (" + anteilX + ") ) = " + Rechnung.anteil(GameEngine.graphicalDisplayPixelsX, anteilX));
	}
	
	public JoystickPad(int posX, int posY, int width, int height, int id) {
		super(posX, posY, width, height, R.drawable.joystick_hintergrund, GameEngine.activity);
		stick = new Stick(this);
		view();
	}

	@Override
	public void onTouchscreenEvent(View view, MotionEvent event) {
		stick.onTouchscreenEvent(view, event);
	}
	
	
	// GETTER UND SETTER
	
	public int getJoystickZuPadVerhaeltniss() {
		return joystickZuPadVerhaeltniss;
	}

	public void setJoystickZuPadVerhaeltniss(int joystickZuPadVerhaeltniss) {
		this.joystickZuPadVerhaeltniss = joystickZuPadVerhaeltniss;
		view();
	}
	
}
