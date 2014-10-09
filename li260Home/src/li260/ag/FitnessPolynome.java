package li260.mains.tutoAG;

import li260.optimisation.Genome;
import li260.optimisation.operators.FitnessOperator;

public class FitnessPolynome implements FitnessOperator<Double>{

	/**
	 * Plus cette fonction est grande, plus on est content
	 * On minimise ici la fonction f(x1,x2) =  f(x1,x2) = (x1+2)^2+(x2+3)^2
	 * Il faut donc retourner -f(x1,x2) => minimisation = maximisation de l'oppose
	 */
	public double fit(Genome<Double> g) {
		return -(g.get(0)+2)*(g.get(0)+2)-(g.get(1)+3)*(g.get(1)+3);
		
	}

}
