package li260.mains.tutoAG;

import li260.geometry.Vecteur;
import li260.optimisation.Genome;
import li260.optimisation.algo.GeneticAlgorithm;
import li260.optimisation.factories.GenomeGenerator;
import li260.optimisation.operators.CrossingOperator;
import li260.optimisation.operators.FitnessOperator;
import li260.optimisation.operators.MutationOperator;


public class MainAG {
	public static void main(String[] args){
		// Exemple d'usage des algorithmes génétiques pour l'optimisation d'une fonction:
		// f(x1,x2) = (x1+2)^2+(x2+3)^2
		
		// pourcentage de mutation
		double rateMute = 0.15;
		double sigMute  = 1;
		
		// taille des populations et nb de générations
		int populationSize = 500;
		int nGeneration = 300;
		
		double[] xmin = {-5., -5};
		double[] xmax = {5., 5};
		
		// instanciation
		MutationOperator<Double> muteOp  = new MutationOpImpl(rateMute, sigMute);
		CrossingOperator<Double> crossOp = new CrossingOpImpl();
		FitnessOperator<Double>  fitOp = new FitnessPolynome();
		GenomeGenerator<Double> genGOp = new GenomeGeneratorImpl(xmin, xmax);
		
		
		GeneticAlgorithm<Double> ga = new GeneticAlgorithm<Double>(populationSize, genGOp, muteOp, crossOp, fitOp);
		Genome<Double> liste = ga.optimize(nGeneration);
		
		System.out.println(liste);
		
	}
}
