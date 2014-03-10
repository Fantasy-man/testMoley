package de.marcel.game.enginge;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Viewable {
	
	public int graphicalPosX;
	public int graphicalPosY;
	public int graphicalWidth;
	public int graphicalHeight;
	public int graphicalRotationX = 0;
	public int graphicalRotationY = 1;
	public ImageView iv = null;
	public RelativeLayout.LayoutParams lp = null;
	public int image;
	public Context context;
	
	public Viewable(int posX, int posY, int width, int height, int image, Context context) {
		this.graphicalPosX = posX;
		this.graphicalPosY = posY;
		this.graphicalWidth = width;
		this.graphicalHeight = height;
		this.image = image;
		this.context = context;
	}
	
	public void view() {
		Log.i("GameEngine", "Viewable - view() - posX: " + getAbsoluteImagePositionX() + " Y: " + getAbsoluteImagePositionY() + 
				" width: " + graphicalWidth + " height: " + graphicalHeight);
		if (iv == null || lp == null) {
			iv = new ImageView(context);
			iv.setImageResource(image);
			lp = new RelativeLayout.LayoutParams(graphicalWidth, graphicalHeight);
			GameEngine.mainLayout.addView(iv);
		}
		if (lp.width != graphicalWidth || lp.height != lp.height) {
			lp = new RelativeLayout.LayoutParams(graphicalWidth, graphicalHeight);
		}
		lp.setMargins(getAbsoluteImagePositionX(), getAbsoluteImagePositionY(), 0, 0);
		iv.setRotation(Rechnung.degreeceFromXAndY(graphicalRotationX, graphicalRotationY));
		
		if (Rechnung.degreeceFromXAndY(graphicalRotationX, graphicalRotationY) != 0.0) {
			Log.e("abc", "rotation: " + Rechnung.degreeceFromXAndY(graphicalRotationX, graphicalRotationY));
		}
		
		iv.setLayoutParams(lp);
	}
	
	public int getAbsoluteImagePositionX() {
		return graphicalPosX - graphicalWidth / 2;
	}
	
	public int getAbsoluteImagePositionY() {
		return graphicalPosY - graphicalHeight / 2;
	}
	
}
