package li260.mains.tutoAG;

import java.util.Random;

import li260.geometry.Vecteur;
import li260.optimisation.operators.MutationOperator;

public class MutationOpImpl implements MutationOperator<Double> {

	private double rate;
	private static Random r = new Random();
	private double sigma;

	
	public MutationOpImpl(double rate,  double sigma) {
		super();
		this.rate = rate;
		this.sigma = sigma;
	}

	
	/**
	 * Avec une probabilite faible, appliquer une transformation sur le gene
	 */
	public Double mute(Double g) {
		if(r.nextDouble()<rate)
			return g+r.nextGaussian()*sigma;
		return g;
	}

}
