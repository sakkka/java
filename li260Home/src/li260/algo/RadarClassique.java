package li260.algo;

import li260.circuit.Circuit;
import li260.geometrie.Vecteur;
import li260.terrain.TerrainTools;
import li260.voiture.Voiture;

public class RadarClassique implements Radar {
	
	protected double[] scores;
	@SuppressWarnings("unused")
	private double distanceInPixels;
	protected int BestIndex;
	protected double[] thetas ;
	protected final double EPS = 0.1;
	protected Voiture fefe;
	protected Circuit track ;
	
	public RadarClassique (Voiture fefe, Circuit track, double[] thetas) {
		
		this.fefe=fefe;
		this.track=track;
		this.thetas=thetas;
		this.scores = new double[thetas.length];
		//this.BestIndex = 3;
	}
	
	
	


	public double calcScore(double angle) {
		
		Vecteur pos = fefe.getPosition() ;
		Vecteur dir = fefe.getDirection() ;
		
		Vecteur pos2 = pos ;
		Vecteur dir2 = dir ;
		
		double score = 0 ;
		
		dir2=dir2.rotation(angle);	
		while(TerrainTools.isRunnable(track.getTerrain(pos2))) {
			
			score ++ ;
			pos2 = pos2.add((dir2.mult(EPS)))  ; 	
			

			
		}	
		

		
	return score;
	}

	public double[] getScores () {
		
		for(int i = 0; i< thetas.length; i++) {
			scores[i] = calcScore(thetas[i]);
		}
		
	return scores ;}
	
	
	public double getDistanceInPixels(int index) {		
		
		return distanceInPixels = scores[index] * EPS;
	}

	
	public int getBestIndex() {

		double max = 0;
		int indexMax = 0;
		
		for(int i = 0; i < scores.length; i++) {
			
			if(scores[i] > max) {
				
				max = scores[i];
				indexMax = i;
			}
			
		}
		BestIndex = indexMax ;
		
		getDistanceInPixels(BestIndex) ;
		

		
		return BestIndex ;	
	}

	public double returnBest () {
		if( getDistanceInPixels(0) < 20.) {
			System.out.println ("MUR ||||||||||||||||||||||||||||||||") ; //pour frener devant les nurs
			return -2 ;
		}
		return scores[BestIndex] ;
	}

	public double getThetaIndex(int index) {
		return thetas[index];
	}

	
	public double getResultatRadar () {
		
		getScores() ;
		getBestIndex() ;
//		System.out.println ("radar classique (best score): "+scores[BestIndex]);
//		System.out.println ("radar classique (best angle): "+thetas[BestIndex]);
		
		return this.thetas[this.BestIndex] ;
	}
	}


