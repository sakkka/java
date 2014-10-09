package li260.strategy;

import li260.algo.Radar;
import li260.circuit.Circuit;
import li260.voiture.Commande;
import li260.voiture.Voiture;

public class StrategyRadarPlusDijkstra implements Strategy {
	
	private static final long serialVersionUID = 6195504555796427788L;
	private Voiture fefe ;
	@SuppressWarnings("unused")
	private Circuit circ ;
	private Radar RC ;
	private Radar RD ;
	
	public StrategyRadarPlusDijkstra (Voiture fefe, Circuit circ, Radar rad, Radar rad2) {

		this.fefe = fefe ;
		this.circ = circ;
		this.RC = rad ;
		this.RD = rad2 ;

	}
	
	
	public Commande getCommande () {
		
		Commande commande ;
		
		double angRad = RC.getResultatRadar()/fefe.getBraquage();
		double angDij = RD.getResultatRadar()/fefe.getBraquage();

		if(angDij==-1) {
			System.out.println ("Strategy Rad+DIj : tout droit"+angDij);
			return commande = new Commande (0.5, 0);  }

		if(angRad == angDij) {
			commande = new Commande(1.0 , angleAdjust(angRad)) ;
			System.out.println ("choix: RAD");
		}
		else {
			commande = new Commande(1.0 , angleAdjust((angRad+angDij)/2.000000));
			System.out.println ("choix: MOY");					
		}	
		
		System.out.println (commande.getAccel()+"  "+commande.getTurn());
	return commande ;	
	}
	
	public double angleAdjust(double ang) {
		double lim = fefe.getMaxTurnSansDerapage(); 	// temps qu'on roule SANS derapages
		if (ang>lim) ang=lim;
		if (ang<-lim) ang=-lim;
	return ang;
	}
} 
