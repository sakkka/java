package li260.algo;

import li260.circuit.Circuit;
import li260.geometrie.Vecteur;
import li260.terrain.Terrain;
import li260.terrain.TerrainTools;
import li260.voiture.Voiture;

public class RadarDijkstra implements Radar  {

	private Dijkstra dij ;
	protected double[] scores;
	private double distanceInPixels;
	protected int BestIndex;
	protected double[] thetas ;
	protected final double EPS = 0.1;
	protected Voiture fefe;
	protected Circuit track ;


	public RadarDijkstra (Circuit track, Voiture fefe, double[] thetas, Dijkstra dij ) {

		super();
		this.thetas = thetas;
		this.dij = dij ;
		this.scores = new double[thetas.length];
		this.fefe=fefe;
		this.track=track;
		//this.thetas=thetas;
		//this.scores = new double[thetas.length];
		

	}

	public double calcScore (double angle) {

		Vecteur pos = fefe.getPosition().clone() ;
		Vecteur dir = fefe.getDirection().clone() ;
		
		dir.autoRotation(angle);
		
		double min = Double.POSITIVE_INFINITY;
		
		double tmp = 0 ;
		int i =0;
		while( TerrainTools.isRunnable(track.getTerrain(pos))) {
			i++;
			pos.autoAdd(dir.mult(EPS));

			tmp = dij.getDist((int) pos.getX(), (int) pos.getY());
		
			if (tmp < min) {
				min = tmp ;
			}
			
			if((track.getTerrain(pos) == Terrain.EndLine) && 
					(track.getDirectionArrivee().produitScalaire(dir) >= 0) ) {
				tmp = -10000 + i;
				
				return tmp ;
			}
			
		}
		return min;
	}
	
	public double[] getScores () {
		
		for(int i = 0; i< thetas.length; i++) {
			scores[i] = calcScore(thetas[i]);
		}
		
	return scores ;}
	
	

	
	public int getBestIndex() {
		
		double min =  Double.POSITIVE_INFINITY;
		int indexMax = 0;
		
		for(int i = 0; i < scores.length; i++) {
	//		System.out.println ("Scores dij : "+scores[i]);
			if(scores[i] < min) {
				
				min = scores[i];
				indexMax = i;
			}
			
		}
		this.BestIndex = indexMax ;
	//	System.out.println ("indexMax : "+indexMax);
		getDistanceInPixels(BestIndex) ;
		
		return this.BestIndex;
	}
	
	public double getResultatRadar () {
		
		getScores() ;
		getBestIndex() ;
//		System.out.println ("radar dijkstra (best score): "+scores[BestIndex]);
//		System.out.println ("radar dijkstra (best angle): "+thetas[BestIndex]);
		
		return this.thetas[this.BestIndex] ;
	

	}

	@Override
	public double getDistanceInPixels(int index) {
		distanceInPixels = scores[index] * EPS;
		return distanceInPixels;
	}

	@Override
	public double returnBest() {
		return scores[BestIndex] ;
	}
	
}





