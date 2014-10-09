package li260.circuit;

import java.util.ArrayList;

import li260.geometrie.Vecteur;
import li260.terrain.Terrain;
import li260.terrain.TerrainTools;

public class CircuitImpl implements Circuit {

	private Terrain[][] track ;
	private Vecteur depart ;
	private Vecteur dirDepart, dirArrivee ;
	private ArrayList<Vecteur> listeArriv ;
	private String name;


	public CircuitImpl(Terrain[][] track, Vecteur dirDepart, Vecteur dirArrivee, String name) {

		//super();
		this.track = track;
		this.dirDepart = dirDepart;
		this.dirArrivee = dirArrivee;
		this.depart = getDepart() ;

		ArrayList<Vecteur> listeArriv2  = new ArrayList<Vecteur> () ;

		for (int i =0; i<TerrainTools.getTerrainHeight(track); i++) {

			for (int j =0; j<TerrainTools.getTerrainWidth(track); j++) {

				if(track[i][j] == Terrain.EndLine) {
					listeArriv2.add(new Vecteur (i, j));
					//System.out.println ("Arrivee ++");
				}
			}
		}
		this.listeArriv = listeArriv2;
		this.name=name.substring(name.lastIndexOf("/")+1, name.lastIndexOf("."));


	}

	public Terrain getTerrain (int i, int j) {		
		return track[i][j];
	}

	public Terrain getTerrain (Vecteur v) {
		return track[(int) v.getX()][(int) v.getY()];
	}

	public Vecteur getPointDepart () {
		return depart ;
	}

	public Vecteur getDirectionDepart() {
		return dirDepart ;
	}

	public Vecteur getDirectionArrivee() {
		return dirArrivee ;
	}

	public int getWidth() {
		return TerrainTools.getTerrainWidth(track);
	}

	public int getHeight () {
		return TerrainTools.getTerrainHeight(track);
	}

	public ArrayList<Vecteur> getArrivees () {

		return listeArriv ;
	}

	public Vecteur getDepart () {

		for (int i =0; i<TerrainTools.getTerrainHeight(track); i++) {

			for (int j =0; j<TerrainTools.getTerrainWidth(track); j++) {

				if(track[i][j] == Terrain.StartPoint) {

					depart = new Vecteur (i, j);
				}
			}
		}


		return depart ;
	}

	@Override
	public double getDist(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {

		return this.name;
	}



}
