package li260.voiture;

import li260.circuit.Circuit;
import li260.geometrie.Vecteur;

public class VoitureFactory {
	
	public VoitureFactory(double vmax, double braquage, double alpha_c,	double alpha_f, double beta_f, double alpha_derapage, double masse,	Vecteur position, Vecteur direction, double vitesse, double  vitesse_sortie_derapage) {
		
		super();
	}


	private static double vmax = 0.9;
	private static double alpha_c = 0.02;
	private static double braquage = 0.2;
	private static double alpha_f= 0.001;
	private static double beta_f= 0.002;
	private static double alpha_derapage = 0.01;
	private static double masse = 1;
	private static double vitesse_sortie_derapage = 0.6;
	private static double vitesse = 0.; 							// vitesse initiale = 0


	public static Voiture build (Circuit c) {
		
	return new VoitureImpl(vmax, braquage, alpha_c, alpha_f, beta_f, alpha_derapage, masse, c.getDepart(), c.getDirectionDepart(), vitesse, vitesse_sortie_derapage) ;}

}
