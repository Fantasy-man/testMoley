package de.marcel.game.enginge.touchscreen;

import android.view.MotionEvent;
import android.view.View;

public interface TouchscreenEventListener {
	
	public void onTouchscreenEvent(View view, MotionEvent event);
	
}
