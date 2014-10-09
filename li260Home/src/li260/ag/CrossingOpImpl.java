package li260.mains.tutoAG;

import li260.optimisation.Genome;
import li260.optimisation.operators.CrossingOperator;

public class CrossingOpImpl implements CrossingOperator<Double>{

	/**
	 * Generation d'un genome a partir de deux genomes
	 */
	public Genome<Double> cross(Genome<Double> g1, Genome<Double> g2) {
		Genome<Double> fils = new Genome<Double>();
		for(int i=0; i<g1.size(); i++){ // generer un nombre aleatoire entre g1 et g2 pour chaque gene
			Double dmin = Math.min(g1.get(i), g2.get(i));
			Double dmax = Math.min(g1.get(i), g2.get(i));
			fils.add(Math.random()*(dmax-dmin)+dmin);
		}
		return fils;
	}

}
