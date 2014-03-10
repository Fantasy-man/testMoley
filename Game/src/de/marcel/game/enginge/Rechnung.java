package de.marcel.game.enginge;

public class Rechnung {
	
	public static int anteil(int grundwert, int anteil_in_prozent) {
		return (int) ((double) grundwert * (double) anteil_in_prozent / (double) 100);
	}
	
	public static int entfernungZwischen(int p1X, int p1Y, int p2X, int p2Y) {
		return (int) Math.sqrt(Math.pow(p2X - p1X, 2) + Math.pow(p2Y - p1Y, 2));
	}
	
	public static int gameUnitToPixel(int a) {
		return (int) ((double) a * GameEngine.groesse);
	}
	
	public static float degreeceFromXAndY(int x, int y) {
		if (x == 0 && y == 0) {
			return 0F;
		}
		if (x >= 0) {
			return (float) Math.toDegrees(Math.atan((double) y / (double) x)) + 90;
		} else {
			return (float) Math.toDegrees(Math.atan((double) y / (double) x)) + 270;
		}
	}
	
	public static Punkt getPointOnLineWhereXIs(int fromX, int fromY, int toX, int toY, int sollX) {
		int x = fromX - toX;
		int y = fromY - toY;
		int b = fromX - sollX;
		
		if (x == 0) {
			return new Punkt(toX, toY);
		}
		if (y == 0) {
			return new Punkt(sollX, toY);
		}
		
		return new Punkt(sollX, fromY + (int) (b/((double)x / (double)y)));
	}
	
	public static Punkt getPointOnLineWhereYIs(int fromX, int fromY, int toX, int toY, int sollY) {
		int x = fromX - toX;
		int y = fromY - toY;
		int b = fromY - sollY;
		
		if (x == 0) {
			return new Punkt(toX, sollY);
		}
		if (y == 0) {
			return new Punkt(toX, toY);
		}
		
		return new Punkt(fromX + (int) (b/((double)y / (double)x)), sollY);
	}
	
}
