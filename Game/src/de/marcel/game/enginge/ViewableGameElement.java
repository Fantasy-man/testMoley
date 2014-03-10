package de.marcel.game.enginge;

import android.content.Context;

public class ViewableGameElement extends Viewable {
	
	public int posX;
	public int posY;
	public int sizeX;
	public int sizeY;
	
	public ViewableGameElement(int posX, int posY, int sizeX, int sizeY,
			int image, Context context) {
		super(	Rechnung.gameUnitToPixel(posX),
				Rechnung.gameUnitToPixel(posY),
				Rechnung.gameUnitToPixel(sizeX),
				Rechnung.gameUnitToPixel(sizeY), image, context);
		
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	@Override
	public void view() {
		graphicalPosX = Rechnung.gameUnitToPixel(posX);
		graphicalPosY = Rechnung.gameUnitToPixel(posY);
		graphicalWidth = Rechnung.gameUnitToPixel(sizeX);
		graphicalHeight = Rechnung.gameUnitToPixel(sizeY);
		
		super.view();
	}
	
}
