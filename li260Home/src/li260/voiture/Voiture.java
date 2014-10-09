package li260.voiture;
import li260.geometrie.Vecteur;


public interface Voiture {
	
	// pour le pilotage 
	
	public void drive (Commande c) throws VoitureException ;
	public double getMaxTurnSansDerapage () ;
	
	// pour l'observation	
	
	public double getVitesse() ;
	public Vecteur getPosition() ;
	public Vecteur getDirection () ;
	public boolean getDerapageExt () ;
	public double getBraquage() ;
	public double getVMax();
	public void driveSansDerapage(Commande comm);

}
