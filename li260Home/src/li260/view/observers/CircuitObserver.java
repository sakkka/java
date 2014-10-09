package li260.view.observers;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import li260.IHMSwing.ObserverSWING;

public class CircuitObserver implements ObserverSWING{
	
	private Image trackIm ;
	private ArrayList<ObserverSWING> listeSwing;
	
	public CircuitObserver (Image trackIm) {
		this.trackIm = trackIm ;
	}
	
	public void print(Graphics g) {	
		g.drawImage(trackIm, 0, 0, null) ;
	
	}


}

