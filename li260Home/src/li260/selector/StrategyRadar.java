package li260.selector;


import li260.algo.Radar;
import li260.algo.RadarClassique;
import li260.circuit.Circuit;
import li260.strategy.Strategy;
import li260.voiture.Commande;
import li260.voiture.Voiture;

public class StrategyRadar implements Strategy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6195504555796427788L;
	private Voiture fefe ;
	private Circuit circ ;
	private double[] thetas ;
	
	public StrategyRadar(Voiture fefe, Circuit circ, double[] thetas) {
		
		this.fefe = fefe ;
		this.circ = circ;
		this.thetas = thetas ;
		
	}
	
	public Commande getCommande () {
		
		Radar RC = new RadarClassique (fefe, circ, thetas);
		RC.getScores();
		RC.getBestIndex();
		
		//ResultatRadar ResR = RC.getResultatRadar();
		
		double ang=RC.getResultatRadar()/fefe.getBraquage();
		
		double lim = fefe.getMaxTurnSansDerapage();
		
		if (ang>lim)ang=lim;
		if (ang<-lim)ang=-lim;
		Commande commande = new Commande(1.0 , ang) ;
		
	//  Commande commande = new Commande(0.5 , Math.min( Math.abs(ResR.getAngle()), fefe.getMaxTurnSansDerapage() )) ;
		
	//	System.out.println ("SR commande (best distance) :   "+ResR.getDistancePixel() );
	//	System.out.println ("SR commande turn : "+ commande.getTurn()+ " accel : "+ commande.getAccel());
		
		return commande ;
		
		
	}

	  
			          


}
