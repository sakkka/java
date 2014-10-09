package li260.algo;




public interface Radar {
	
	
	public double getDistanceInPixels(int index);		// pour l'observer
	public int getBestIndex(); 					// meilleur indice
	public double calcScore(double angles);
	public double[] getScores() ;
	public double getResultatRadar() ;
	public double returnBest();

	
}



