package li260.algo;

public class ResultatRadar  {
	
	private double angle ;
	private double scores ;
	private double distancePixel ;
	
	
	public ResultatRadar(double angle, double scores, double distancePixel) {
		super();
		this.angle = angle;
		this.scores = scores;
		this.distancePixel = distancePixel;
	}


	public double getAngle() {
		return angle;
	}


	public double getScores() {
		return scores;
	}


	public double getDistancePixel() {
		return distancePixel;
	}
 

}