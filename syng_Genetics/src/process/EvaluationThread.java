package process;

import functions.EvaluationFunction;
import population.IIndividual;

/**
 * Thread to evaluate individual  
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class EvaluationThread implements Runnable {

	private EvaluationFunction<IIndividual, Void>  evaluationFunction;
	private IIndividual individual;

	/**
	 * Constructor
	 * @param evalFunction
	 * @param ind
	 */
	public EvaluationThread(EvaluationFunction<IIndividual, Void> evalFunction, IIndividual ind){
		evaluationFunction = evalFunction;
		individual = ind;
	}
	
	@Override
	public void run() {
		evaluationFunction.execute(individual);
		
	}

}
