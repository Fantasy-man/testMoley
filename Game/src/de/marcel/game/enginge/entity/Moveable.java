package de.marcel.game.enginge.entity;

import de.marcel.game.enginge.Punkt;
import de.marcel.game.enginge.entity.bounding.Boundable;
import de.marcel.game.enginge.entity.bounding.Bounding;
import android.content.Context;
import android.util.Log;

public class Moveable extends Boundable {
	
	public int speed = 10;
	
	public Moveable(int posX, int posY, int sizeX, int sizeY, int image,
			Context context, int speed, Bounding bounding) {
		super(posX, posY, sizeX, sizeY, image, context, bounding);
		
		this.speed = speed;
		
		view();
	}
	
	public void bewegenNach(double x, double y) {
		
		if (x > 1.0000001 || y > 1.0000001) {
			Log.e("GameEngine", "Moveable - Die Parameter für bewegenNach(" + x + "," + y + ") sind größer als 1.0000001!! -> Abgebrochen (return false;)");
			return;
		}
		
		// Neue position berechnen
		int nachPosX = posX + (int)(x * (double) speed);
		int nachPosY = posY + (int)(y * (double) speed);
		
		Punkt geheNach = getValidPositionToGoTo(posX, posY, nachPosX, nachPosY);
		nachPosX = geheNach.x;
		nachPosY = geheNach.y;
		
		posX = nachPosX;
		posY = nachPosY;
		graphicalRotationX = (int) (x * 1000);
		graphicalRotationY = (int) (y * 1000);
		
		view();
		
		return;
	}
	
}
