package process;

import exceptions.GeneticAlgorithmException;
import functions.CrossOverFunction;
import population.IIndividual;

/**
 * Thread to crossover individuals 
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class CrossOverThread implements Runnable {

	private CrossOverFunction<IIndividual[], IIndividual>  crossoverFunction;
	private IIndividual[] parents;
	private IIndividual child;
	
	/**
	 * Constructor
	 * @param coFunction
	 * @param p
	 * @throws GeneticAlgorithmException 
	 */
	public CrossOverThread(CrossOverFunction<IIndividual[], IIndividual> coFunction, IIndividual[] p) throws GeneticAlgorithmException{
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
	public IIndividual getChild() throws GeneticAlgorithmException {
		if(child != null) {
			return child;
		}else {
			throw new GeneticAlgorithmException("Error crossover : individual child is not created");
		}
	}
	
	

}

