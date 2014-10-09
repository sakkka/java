package li260.selector;

import li260.algo.Radar;
import li260.circuit.Circuit;
import li260.strategy.Strategy;
import li260.voiture.Commande;
import li260.voiture.Voiture;

public class StrategyRadarDij implements Strategy {



	private static final long serialVersionUID = 6195504555796427788L;
	private Voiture fefe ;
	@SuppressWarnings("unused")
	private Circuit circ ;
	private Radar rad ;

	public StrategyRadarDij (Voiture fefe, Circuit circ, Radar rad) {

		this.fefe = fefe ;
		this.circ = circ;
		this.rad = rad ;

	}

	public Commande getCommande () {

		double ang=rad.getResultatRadar()/fefe.getBraquage();
		double lim = fefe.getMaxTurnSansDerapage();
		
		if (ang>lim) ang=lim;
		if (ang<-lim) ang=-lim;

		Commande commande = new Commande(1.0 , ang) ;

		return commande ;


	}

}
