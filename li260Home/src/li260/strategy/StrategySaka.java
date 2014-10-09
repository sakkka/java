package li260.strategy;

import li260.algo.Radar;
import li260.circuit.Circuit;
import li260.voiture.Commande;
import li260.voiture.Voiture;

public class StrategySaka implements Strategy {

	private static final long serialVersionUID = 6195504555796427788L;
	private Voiture fefe ;
	@SuppressWarnings("unused")
	private Circuit circ ;
	private Radar RC ;
	private Radar RD ;

	public StrategySaka (Voiture fefe, Circuit circ, Radar rad, Radar rad2) {

		this.fefe = fefe ;
		this.circ = circ;
		this.RD = rad ;
		this.RC = rad2 ;

	}


	public Commande getCommande () {

		Commande commande ;
		double angRad = RC.getResultatRadar()/fefe.getBraquage();
		double angDij = RD.getResultatRadar()/fefe.getBraquage();
		double scoreDij = RD.returnBest();
		double scoreRad = RC.returnBest();
		//System.out.println ("Strategy Rad+DIj   --  SCOREDij :"+scoreDij+" AngDIJ : "+angDij);



		//System.out.println ("    "+scoreRad);

		if(scoreRad==-2.0) {
			System.out.println ("M U R"+angRad);
			return commande = new Commande (-1. , angleAdjust(angDij));  } //je joue avec ca pour le 7_track


		if(scoreDij < 0) {
			System.out.println ("Strategy : arrivee devant"+angDij);
			return commande = new Commande (1., angleAdjust(angDij));  }

		if(angRad == angDij) {
			commande = new Commande(1.0 , angleAdjust(angDij)) ;
			System.out.println ("choix: RAD");
		}
		else {
		
			commande = new Commande(1.0 , angleAdjust((angRad+angDij)/2.000000));
			System.out.println ("choix: MOY"+angDij+" "+angRad);					
		}	
		
		if(scoreRad>1500){

		System.out.println (commande.getAccel()+"  "+commande.getTurn()+"sans gestion vitesse");
		return commande ;	
		}
		else {
			System.out.println (commande.getAccel()+"  "+commande.getTurn());
			return gestionVitesse(commande) ;
		}
	}



	public double angleAdjust(double ang) {
		double lim = fefe.getMaxTurnSansDerapage(); 	// temps qu'on roule SANS derapages
		if (ang>lim) ang=lim;
		if (ang<-lim) ang=-lim;
		return ang;
	}

	public Commande gestionVitesse (Commande cmd) {
		if(fefe.getVitesse() > 0.8) {
			return new Commande (-0.5, cmd.getTurn()) ;
		}
		else if (fefe.getVitesse() > 0.6 ) {
			return new Commande (0., cmd.getTurn());
		}
		else {
			return cmd ;
		}
	}
	
	public Commande gestionVitesseFort (Commande cmd) {
		if(fefe.getVitesse() > 0.8) {
			return new Commande (-0.8, cmd.getTurn()) ;
		}
		else if (fefe.getVitesse() > 0.6 ) {
			return new Commande (-0.2, cmd.getTurn());
		}
		else {
			return cmd ;
		}
	}
} 
