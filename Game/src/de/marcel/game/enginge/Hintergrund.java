package de.marcel.game.enginge;

import android.util.Log;

public class Hintergrund extends Viewable {
	
	public Hintergrund(int bild) {
		super(GameEngine.graphicalDisplayPixelsX / 2 , GameEngine.graphicalDisplayPixelsY / 2, GameEngine.graphicalDisplayPixelsX, GameEngine.graphicalDisplayPixelsY, bild, GameEngine.activity);
		
		view();
		Log.e("GameEngine", "X: " + graphicalPosX + " Y: " + graphicalPosY + " width: " + graphicalWidth + " height: " + graphicalHeight);
	}
	
	public void setImage(int image) {
		this.image = image;
		view();
	}

}
