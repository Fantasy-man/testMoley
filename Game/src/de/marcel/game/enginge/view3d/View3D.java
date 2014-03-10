package de.marcel.game.enginge.view3d;

import de.marcel.game.enginge.GameEngine;
import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class View3D{

	public ImageView iv = null;
	public RelativeLayout.LayoutParams lp = null;
	public Activity a;
	
	public View3D(Activity a) {
		this.a = a;
		
	}
	
	public void view(Bitmap bm) {
		if (iv == null || lp == null) {
			iv = new ImageView(a);
			lp = new RelativeLayout.LayoutParams(1920, 1080);
			GameEngine.mainLayout.addView(iv);
		}

		iv.setImageBitmap(bm);
		lp.setMargins(0, 0, 0, 0);
		iv.setLayoutParams(lp);
	}
	
	
	
}
