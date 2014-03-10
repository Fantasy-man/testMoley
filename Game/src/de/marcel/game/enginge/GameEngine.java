package de.marcel.game.enginge;

import java.util.ArrayList;

import de.marcel.game.R;
import de.marcel.game.enginge.joystick.JoystickListener;
import de.marcel.game.enginge.joystick.JoystickPad;
import de.marcel.game.enginge.touchscreen.TouchscreenEventListener;
import de.marcel.game.enginge.entity.*;
import de.marcel.game.enginge.entity.bounding.Bounding;
import de.marcel.game.enginge.entity.bounding.formen.Kreis;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameEngine {
	
	public static Activity activity = null;
	public static RelativeLayout mainLayout = null;
	public static int graphicalDisplayPixelsX;
	public static int graphicalDisplayPixelsY;
	public static double groesse = 1; // Umrechnungsfaktor (Pixel = Spieleinheit * groesse)
	public static Player player;
	public static Player player2;
	public static Player player3;
	public static JoystickPad steuerPad;
	public static JoystickPad steuerPad2;
	public static TextView fps;
	public static Hintergrund hintergrund;
	
	public static ArrayList<JoystickPad> joystickPads = new ArrayList<JoystickPad>();
	public static ArrayList<TouchscreenEventListener> touchscreenElements = new ArrayList<TouchscreenEventListener>();
	public static ArrayList<JoystickListener> joystickElements = new ArrayList<JoystickListener>();
	
	public GameEngine(Activity activity) {
		GameEngine.activity = activity;
		mainLayout = (RelativeLayout) activity.findViewById(R.id.mainLayout);
		
		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		
		graphicalDisplayPixelsX = metrics.widthPixels;
		graphicalDisplayPixelsY = metrics.heightPixels;
		
		hintergrund = new Hintergrund(R.drawable.hintergrund);
		
		fps = (TextView) activity.findViewById(R.id.nullpunkt);
		fps.setText("FPS: -");
		
		steuerPad =  addJoystickPad(13, 75, 40, 1);
		steuerPad2 =  addJoystickPad(87, 75, 40, 2); 
		player = new Player(200, 200, 100, 100, R.drawable.player, activity, 20, steuerPad , new Bounding(new Kreis(50)));
		player = new Player(400, 200, 100, 100, R.drawable.player, activity, 20, steuerPad2 , new Bounding(new Kreis(50)));
		
		mainLayout.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				callTouchscreenEvent(view, event);
				return true;
			}
		});
	}
	
	public static void callTouchscreenEvent(View view, MotionEvent event) {
		for (TouchscreenEventListener tel : touchscreenElements) {
			tel.onTouchscreenEvent(view, event);
		}
	}
	
	public static void callJoystickEvent(JoystickPad joystick, double x, double y) {
		
		if (System.currentTimeMillis() > (player.pmThread.letzteFpsSekunde + 2) * 1000) {
			
			GameEngine.fps.setText("FPS: " + player.pmThread.fpsCounter);
			
			player.pmThread.fpsCounter = 0;
			player.pmThread.letzteFpsSekunde = (long) (System.currentTimeMillis() / 1000);
		}
		
		//Log.e("joystick", "CALL listener x: " + x + " y: " + y);
		for (JoystickListener jl : joystickElements) {
			jl.onJoystickMove(joystick, x, y);
		}
	}
	
	
	
	public static void handleListeners(Object o) {
		for (Class<?> c : o.getClass().getInterfaces()) {
			if (c == TouchscreenEventListener.class) {
				touchscreenElements.add((TouchscreenEventListener) o);
			}
			if (c == JoystickListener.class) {
				joystickElements.add((JoystickListener) o);
			}
		}
	}
	
	// ADD
	public static void addJoystickListener(JoystickListener jl) {
		joystickElements.add(jl);
	}
	
	public static JoystickPad addJoystickPad(int anteilX, int anteilY, int anzeilSize, int id) {
		JoystickPad jp = new JoystickPad(anteilX, anteilY, anzeilSize, id);
		joystickPads.add(jp);
		handleListeners(jp);
		return jp;
	}
	
	public static int getGameSizeX() {
		return (int) (graphicalDisplayPixelsX / groesse);
	}
	
	public static int getGameSizeY() {
		return (int) (graphicalDisplayPixelsY / groesse);
	}
	
}
