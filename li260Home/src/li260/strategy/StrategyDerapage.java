package li260.strategy;

import li260.algo.Radar;
import li260.circuit.Circuit;
import li260.voiture.Commande;
import li260.voiture.Voiture;

public class StrategyDerapage implements Strategy {

	private static final long serialVersionUID = 6195504555796427788L;
	private Voiture fefe ;
	@SuppressWarnings("unused")
	private Circuit circ ;
	private Radar RC ;
	private Radar RD ;

	public StrategyDerapage (Voiture fefe, Circuit circ, Radar rad, Radar rad2) {

		this.fefe = fefe ;
		this.circ = circ;
		this.RD = rad ;
		this.RC = rad2 ;

	}


	public Commande getCommande () {

		Commande commande ;
		double angRad = RC.getResultatRadar();
		double angDij = RD.getResultatRadar();
		double scoreDij = RD.returnBest();
		double scoreRad = RC.returnBest();
		//System.out.println ("Strategy Rad+DIj   --  SCOREDij :"+scoreDij+" AngDIJ : "+angDij);
		
		
		
		//System.out.println ("    "+scoreRad);

		if(scoreRad==-2.0) {
			System.out.println ("M U R     "+angRad);
			return commande = new Commande (-1. , angleAdjust1(angDij));  } //je joue avec ca pour le 7_track


		if(scoreDij==-1.0) {
			System.out.println ("Strategy Rad+DIj : TOUT DROIT"+angDij);
			return commande = new Commande (1., angleAdjust1(angDij));  }

		if(angRad == angDij) {
			commande = new Commande(1.0 , angleAdjust1(angDij)) ;
			System.out.println ("choix: RAD");
		}
		else {
			commande = new Commande(1.0 , angleAdjust1((angRad+angDij)/2.000000));
			System.out.println ("choix: MOY"+angDij+" "+angRad);					
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
	
	public double angleAdjust1(double ang) {
		//double lim = fefe.getMaxTurnSansDerapage(); 	// temps qu'on roule AVEC derapages
		if (ang>1.) ang=1.;
		if (ang<-1.) ang=-1.;
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
} 
