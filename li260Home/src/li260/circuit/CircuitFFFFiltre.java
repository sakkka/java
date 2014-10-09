package li260.circuit;

import li260.filtre.Filtre;
import li260.geometrie.Vecteur;
import li260.terrain.LectureTerrain;
import li260.terrain.Terrain;

public class CircuitFFFFiltre {
	
	public static Vecteur dirDepart = new Vecteur (0. , 1.) ;
	public static Vecteur dirArrivee = new Vecteur (0.0, 1.0) ;
	//public static Vecteur vecteurDepart = new Vecteur (0.0, 1.0) ;
	
	
	public static Circuit buildWithFilter (String filename, Filtre filtre) {
		Terrain[][] tab = LectureTerrain.lecture(filename);
		filtre.filtrer(tab);
		
		return new CircuitImpl(tab, dirDepart, dirArrivee);
	}

}
