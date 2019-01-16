package process;

import exceptions.GeneticAlgorithmException;
import functions.CrossOverFunction;
import population.Individual;

/**
 * Thread to crossover individuals 
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class CrossOverThread implements Runnable {

	private CrossOverFunction<Individual[], Individual>  crossoverFunction;
	private Individual[] parents;
	private Individual child;
	
	/**
	 * Constructor
	 * @param coFunction
	 * @param p
	 * @throws GeneticAlgorithmException 
	 */
	public CrossOverThread(CrossOverFunction<Individual[], Individual> coFunction, Individual[] p) throws GeneticAlgorithmException{
		crossoverFunction = coFunction;
		if(p.length == 2) {
			parents = p;
		}else {
			throw new GeneticAlgorithmException("Error crossover : individuals parents are not defined");
		}
	}
	
	@Override
	public void run() {
			child = crossoverFunction.execute(parents);	
	}
	
	/**
	 * Get child od the crossover
	 * @return
	 * @throws GeneticAlgorithmException 
	 */
	public Individual getChild() throws GeneticAlgorithmException {
		if(child != null) {
			return child;
		}else {
			throw new GeneticAlgorithmException("Error crossover : individual child is not created");
		}
	}
	
	

}

