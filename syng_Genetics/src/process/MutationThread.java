package process;

import functions.MutationFunction;
import population.Individual;

/**
 * Thread to mutate individual  
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class MutationThread implements Runnable {

	private MutationFunction<Individual, Void>  mutationFunction;
	private Individual individual;

	/**
	 * Constructor
	 * @param mutFunction
	 * @param ind
	 */
	public MutationThread(MutationFunction<Individual, Void> mutFunction, Individual ind){
		mutationFunction = mutFunction;
		individual = ind;
	}
	
	@Override
	public void run() {
		mutationFunction.execute(individual);	
	}

}
