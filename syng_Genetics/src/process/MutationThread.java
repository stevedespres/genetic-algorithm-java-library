package process;

import functions.MutationFunction;
import population.IIndividual;

/**
 * Thread to mutate individual  
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class MutationThread implements Runnable {

	private MutationFunction<IIndividual, Void>  mutationFunction;
	private IIndividual individual;

	/**
	 * Constructor
	 * @param mutFunction
	 * @param ind
	 */
	public MutationThread(MutationFunction<IIndividual, Void> mutFunction, IIndividual ind){
		mutationFunction = mutFunction;
		individual = ind;
	}
	
	@Override
	public void run() {
		mutationFunction.execute(individual);	
	}

}
