package li260.circuit;

import java.util.ArrayList;

import li260.geometrie.Vecteur;
import li260.terrain.Terrain;


public interface Circuit {
	public Terrain getTerrain (int i, int j) ;
	public Terrain getTerrain (Vecteur v) ;
	
	public Vecteur getDepart() ;
	public Vecteur getPointDepart() ;
	public Vecteur getDirectionDepart() ;
	public Vecteur getDirectionArrivee();
	public int getWidth();
	public int getHeight () ;
	public ArrayList<Vecteur> getArrivees() ;
	
	public double getDist(int i, int j);
	public String getName() ;
	// dijkstra (Cours 3 ou 4)
	

}
