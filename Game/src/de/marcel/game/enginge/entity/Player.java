package de.marcel.game.enginge.entity;

import de.marcel.game.enginge.entity.bounding.Bounding;
import de.marcel.game.enginge.joystick.JoystickPad;
import android.content.Context;

public class Player extends Moveable {
	
	public PlayerMoveThread pmThread;
	public boolean running = true;
	
	public Player(int posX, int posY, int sizeX, int sizeY, int image,
			Context context, int speed, JoystickPad jp, Bounding bounding) {
		super(posX, posY, sizeX, sizeY, image, context, speed, bounding);
		
		pmThread = new PlayerMoveThread(this, jp);
		pmThread.start();
	}
}
