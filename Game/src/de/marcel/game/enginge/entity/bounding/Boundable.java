package de.marcel.game.enginge.entity.bounding;

import android.content.Context;
import de.marcel.game.enginge.*;
import de.marcel.game.enginge.entity.bounding.formen.Kreis;

public class Boundable extends ViewableGameElement {
	
	Bounding bounding;
	
	public Boundable(int posX, int posY, int sizeX, int sizeY, int image,
			Context context, Bounding bounding) {
		super(posX, posY, sizeX, sizeY, image, context);
		
		this.bounding = bounding;
	}
	
	public Punkt getValidPositionToGoTo(int fromX, int fromY, int toX, int toY){
		Punkt p = holdOnScreenWithPosition(toX, toY);
		toX = p.x;
		toY = p.y;
		
		return new Punkt(toX, toY);
	}

	
	public Punkt holdOnScreenWithPosition(int x, int y) {
		
		if (bounding.form instanceof Kreis) {
			// KREIS
			Kreis k = (Kreis) bounding.form;
			
			// Linker Rand
			if (x - k.radius < 0) {
				x = k.radius;
			}
			
			// Rechter Rand
			if (x + k.radius > GameEngine.getGameSizeX()) {
				x = GameEngine.getGameSizeX() - k.radius;
			}
			
			// Oberer Rand
			if (y - k.radius < 0) {
				y = k.radius;
			}
			
			// Unterer Rand
			if (y + k.radius > GameEngine.getGameSizeY()) {
				y = GameEngine.getGameSizeY() - k.radius;
			}
		}
		
		return new Punkt(x, y);
	}
	
	
} 
