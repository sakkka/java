package li260.strategy;

import java.io.Serializable;

import li260.voiture.Commande;


public interface Strategy extends Serializable {
		
	
	public Commande getCommande();
}
