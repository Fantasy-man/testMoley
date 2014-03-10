package de.marcel.game.enginge.entity.bounding;
import java.util.*;

import de.marcel.game.enginge.entity.bounding.formen.Form;

public class Bounding {
	
	public ArrayList<Bounding> allBoundings = new ArrayList<Bounding>();
	public Form form;
	
	public Bounding(Form form) {
		allBoundings.add(this);
		this.form = form;
	}
	
}
