package de.marcel.game;

import de.marcel.game.enginge.GameEngine;
import de.marcel.game.enginge.joystick.JoystickListener;
import de.marcel.game.enginge.joystick.JoystickPad;
import de.marcel.game.enginge.view3d.Engine3D;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	GameEngine game;
	Engine3D e3d;
	JoystickPad jp1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.activity_main);
		
		game = new GameEngine(this);

		JoystickHandeln jh = new JoystickHandeln();
		GameEngine.addJoystickListener(jh);
		
		/*e3d = new Engine3D(this);
		
		e3d.path.moveTo(500, 550);
		e3d.path.lineTo(500, 500);
		e3d.flash();

		e3d.clear();
		e3d.path.moveTo(100, 100);
		e3d.path.lineTo(100, 600);
		e3d.path.lineTo(600, 600);
		e3d.path.lineTo(600, 100);
		e3d.path.lineTo(120, 100);
		e3d.flash();*/
	}
	
	
	
	public class JoystickHandeln implements JoystickListener{

		@Override
		public void onJoystickMove(JoystickPad joystick, double x, double y) {
			//Log.e("abc", "abc x: " + x + " y: " + y);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
