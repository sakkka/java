package li260.circuit;

import li260.geometrie.Vecteur;
import li260.terrain.LectureTerrain;

public class CircuitFactoryFromFile {
	
	public static Vecteur dirDepart = new Vecteur (0, 1.) ;
	public static Vecteur dirArrivee = new Vecteur (0.0, 1.0) ;
	//public static Vecteur vecteurDepart = new Vecteur (0.0, 1.0) ;
	
	
	public static Circuit build (String filename) {
		
		return new CircuitImpl(LectureTerrain.lecture(filename), dirDepart, dirArrivee, filename);
	}

}
