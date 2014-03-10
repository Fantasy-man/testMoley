package de.marcel.game.enginge.view3d;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

public class Engine3D {
	
	public Activity a;
	public View3D v3d;
	public Bitmap bm;
	public Paint paint;
	public Path path;
	public Canvas canvas;
	public Bitmap originalBm;
	
	public Engine3D(Activity a) {
		this.a = a;
		v3d = new View3D(a);
		
		originalBm = Bitmap.createBitmap(1920, 1080, Config.ARGB_8888);
		bm = originalBm.copy(Config.ARGB_8888, true);
		
		paint = new Paint();
		path = new Path();
		canvas = new Canvas(bm);
		
		paint.setAntiAlias(true);
		paint.setStrokeWidth(8);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setARGB(255, 0, 0, 0);
		
		canvas.drawBitmap(bm, 0, 0, paint);
		//canvas.drawPath(path, paint);
		
		
		//v3d.view(bm);
		Log.e("3D", "Gezeichnet");
	}
	
	public void draw(int fromX, int fromY, int toX, int toY) {
		path = new Path();

		path.moveTo(fromX, fromY);
		path.lineTo(toX, toY);
		
	}
	
	public void clear() {
		//bm = Bitmap.createBitmap(1920, 1080, Config.ARGB_8888);

		bm = originalBm.copy(Config.ARGB_8888, true);
		
		//v3d.view(bm);
		canvas = new Canvas(bm);
		path.reset();
		
		//canvas.drawBitmap(bm, 0, 0, paint);
		//flash();
	}
	
	public void flash() {
		canvas.drawPath(path, paint);
		v3d.view(bm);
		path = new Path();
	}
	
	
}
