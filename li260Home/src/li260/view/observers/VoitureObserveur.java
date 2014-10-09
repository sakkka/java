package li260.view.observers;

import java.awt.Color;
import java.awt.Graphics;

import li260.IHMSwing.ObserverSWING;
import li260.voiture.Voiture;

public class VoitureObserveur implements ObserverSWING {

	private Voiture v ;
	private Color color = Color.yellow;
	private Color colorDerap = Color.red;

	public VoitureObserveur (Voiture voiture) { 
		this.v = voiture ; 

	}

	private int getX() {
		return (int) v.getPosition().getX() ; }

	private int getY() {
		return (int) v.getPosition().getY() ; }

	public Color getColor () {
		if(v.getVitesse()<0.3) // vitesse faible -> cyan 
			return new Color(0, (int)(v.getVitesse()*255*2), (int) (v.getVitesse()*255*2));

		if(v.getVitesse()<0.4)
			return new Color((int)(v.getVitesse()*255*2), (int) (v.getVitesse()*255*2), 0);

		if(v.getVitesse()<0.5)
			return new Color((int)(v.getVitesse()*255*2), 0, (int) (v.getVitesse()*255*2));
		
		if(v.getDerapageExt()) return colorDerap ;
		return color ;
	}



	public void print(Graphics g){
		// Attention a l'inversion eventuelle des coordonnees
		g.setColor(color);
		g.fillRect((int) getY(), (int) getX(), 4, 4);
		g.setColor(Color.red);
		g.drawLine((int) getY(), 
					(int) getX(), 
					(int) (getY()+v.getDirection().getY()*10), 
					(int) (getX()+v.getDirection().getX()*10));

		g.setColor(Color.black);
		g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f) derap: ", v.getVitesse(), 
				v.getDirection().getY(), v.getDirection().getX()) + v.getDerapageExt(),	10, 10);


	}


}
