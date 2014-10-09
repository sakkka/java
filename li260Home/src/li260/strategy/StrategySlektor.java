package li260.strategy;

import li260.algo.Radar;
import li260.circuit.Circuit;
import li260.voiture.Commande;
import li260.voiture.Voiture;

public class StrategySlektor implements Strategy {
	
	private Voiture fefe ;
	@SuppressWarnings("unused")
	private Circuit circ ;
	private Radar RC ;
	private Radar RD ;

	public StrategySlektor (Voiture fefe, Circuit circ, Radar radDij, Radar rad) {

		this.fefe = fefe ;
		this.circ = circ;
		this.RD = radDij ;
		this.RC = rad ;

	}


	public Commande getCommande() {
		
		Strategy stra = analyse() ;
		
		return stra.getCommande();
	}


	private Strategy analyse() {
		// TODO Auto-generated method stub
		return null;
	}

}
