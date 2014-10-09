package li260.mains.tutoAG;

import java.util.Random;

import li260.optimisation.Genome;
import li260.optimisation.factories.GenomeGenerator;

public class GenomeGeneratorImpl implements GenomeGenerator<Double>{
	private double[] xmin, xmax;
	private Random r;
	
	public GenomeGeneratorImpl(double[] xmin, double[] xmax) {
		super();
		this.xmin = xmin;
		this.xmax = xmax;		
		r = new Random();
	}
	
	public Genome<Double> build() {
		Genome<Double> g =  new Genome<Double>();  // c'est une arraylist
		g.add(r.nextDouble()*(xmax[0]-xmin[0]) + xmin[0]); // premier gene
		g.add(r.nextDouble()*(xmax[1]-xmin[1]) + xmin[1]); // deuxieme gene
		return g;
	}
}
