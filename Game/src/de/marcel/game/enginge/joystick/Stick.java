package de.marcel.game.enginge.joystick;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import de.marcel.game.R;
import de.marcel.game.enginge.GameEngine;
import de.marcel.game.enginge.Rechnung;
import de.marcel.game.enginge.Viewable;
import de.marcel.game.enginge.touchscreen.TouchscreenEventListener;

public class Stick extends Viewable implements TouchscreenEventListener {
	
	private int posRelativeX = 0;
	private int posRelativeY = 0;
	private int nullStellungX = 0;
	private int nullStellungY = 0;
	private int touchVerschiebungX = 0;
	private int touchVerschiebungY = 0;
	private boolean gedrueckt = false;
	public JoystickPad pad;
	
	public Stick(JoystickPad pad) {
		super(	pad.graphicalPosX,
				pad.graphicalPosY,
				Rechnung.anteil(pad.graphicalWidth, pad.getJoystickZuPadVerhaeltniss()), 
				Rechnung.anteil(pad.graphicalHeight, pad.getJoystickZuPadVerhaeltniss()),
				R.drawable.joystick_punkt,
				GameEngine.activity);
		
		this.pad = pad;
		
		nullStellungX = pad.graphicalPosX;
		nullStellungY = pad.graphicalPosY;
		
		view();
	}
	
	public void setRelativePosition(int x, int y) {
		Log.d("GameEngine", "Stick - setPosition(" + x + ", " + y + ")");
		
		// Stick Positionieren
		posRelativeX = x;
		posRelativeY = y;
		graphicalPosX = nullStellungX + posRelativeX;
		graphicalPosY = nullStellungY + posRelativeY;
		view();
		
		// Joysticklistener aufrufen
		pad.stickX = (double) x / (double) graphicalWidth;
		pad.stickY = (double) y / (double) graphicalHeight;
		GameEngine.callJoystickEvent(pad, (double) x / (double) graphicalWidth, (double) y / (double) graphicalHeight);
	}
	
	public void setToNullstellung() {
		setRelativePosition(0, 0);
	}

	@Override
	public void onTouchscreenEvent(View view, MotionEvent event) {
		int action = event.getActionMasked();
		int pointerIndex = event.getActionIndex();
		int touchX = (int) event.getX(pointerIndex);
		int touchY = (int) event.getY(pointerIndex);
		
		boolean weiter = false;
		
		Log.d("GameEngine", pad.id + "---" + action + "---index: " + event.getPointerId(pointerIndex) + " ----------------------------------");
		
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			for (int i = 0; i < event.getPointerCount() ; i++) {
				if (event.getPointerId(i) == pad.touchPointerID) {
					weiter = true;
					pointerIndex = i;
				}
			}
			
			if (weiter) {
				touchX = (int) event.getX(pointerIndex);
				touchY = (int) event.getY(pointerIndex);
				// SCHIEBEN
				Log.d("GameEngine", "Stick - schieben");
				
				// Nur innerhalb des Pads
				if (Rechnung.entfernungZwischen(touchX - touchVerschiebungX, touchY - touchVerschiebungY, nullStellungX, nullStellungY) > pad.graphicalWidth / 2 - graphicalWidth / 2) {
					// Ausserhalb des Pads!
					// -> Stick in Pad verschieben
					Log.d("GameEngine", pad.id + "  GESCHOBEN");
					double a, b, c, v;
					a = touchX - nullStellungX - touchVerschiebungX;
					b = touchY - nullStellungY - touchVerschiebungY;
					c = Math.sqrt(a * a + b * b);
					v = (pad.graphicalWidth / 2 - graphicalWidth / 2) / c;
					touchX = (int) (a * v);
					touchY = (int) (b * v);
					setRelativePosition(touchX, touchY);
				} else {
					setRelativePosition(touchX - touchVerschiebungX - nullStellungX, touchY - touchVerschiebungY - nullStellungY);
				}
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
		case MotionEvent.ACTION_DOWN:
			Log.d("GameEngine", "Stick - ACTION_DOWN index: " + pointerIndex + " x: " + touchX + " y: " + touchY);
			if (!gedrueckt) {
				Log.d("GameEngine", "Stick - !gedrueckt");
				// GEDRUECKT
				if (Rechnung.entfernungZwischen(touchX, touchY, nullStellungX, nullStellungY) < graphicalWidth / 2) {
					// Auf Stick gedrückt
					Log.d("GameEngine", pad.id + "  GEDRÜCKT");
					gedrueckt = true;
					pad.touchPointerID = event.getPointerId(pointerIndex);
					touchVerschiebungX = touchX - graphicalPosX;
					touchVerschiebungY = touchY - graphicalPosY;
				}
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_UP:
			if (gedrueckt && event.getPointerId(pointerIndex) == pad.touchPointerID) {
				Log.d("GameEngine", pad.id + "  LOSGELASSEN");
				// LOSGELASSEN
				gedrueckt = false;
				pad.touchPointerID = -20;
				touchVerschiebungX = 0;
				touchVerschiebungY = 0;
				setToNullstellung();
			}
			break;
		}
	}
	
	
	// GETTER UND SETTER

	public int getPosRelativeX() {
		return posRelativeX;
	}

	public int getPosRelativeY() {
		return posRelativeY;
	}
	
}
