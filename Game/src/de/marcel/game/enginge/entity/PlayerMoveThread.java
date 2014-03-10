package de.marcel.game.enginge.entity;

import de.marcel.game.enginge.GameEngine;
import de.marcel.game.enginge.joystick.JoystickPad;

public class PlayerMoveThread extends Thread {
	
	public boolean running = true;
	public Player p;
	public long letzteFpsSekunde = 0;
	public int fpsCounter = 0;
	public JoystickPad jp;
	
	public PlayerMoveThread(Player p, JoystickPad jp) {
		this.jp = jp;
		this.p = p;
	}
	
	@Override
	public void run() {
		
		while (running) {
			
			// FPS anzeigen
			/*if (System.currentTimeMillis() > (letzteFpsSekunde + 2) * 1000) {
				
				Log.e("abc", "curr: " + System.currentTimeMillis() + " > " + (letzteFpsSekunde + 1) * 1000);
				Log.e("abc", "letzteFpsSekunde: " + letzteFpsSekunde);

				Log.e("abc", "counter: " + fpsCounter);
				GameEngine.activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						GameEngine.fps.setText("FPS: " + fpsCounter);
					}
				});

				Log.e("abc", "0");
				fpsCounter = 0;
				letzteFpsSekunde = (long) (System.currentTimeMillis() / 1000);
				Log.e("abc", "gesetzt nach: " + letzteFpsSekunde);
			}*/
			
			if (	jp.stickX > 0.001 || jp.stickX < -0.001
				||	jp.stickY > 0.001 || jp.stickY < -0.001) {
				
				GameEngine.activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						p.bewegenNach(jp.stickX, jp.stickY);
					}
				});
				fpsCounter++;
			}
			
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}
