package process;

import functions.EvaluationFunction;
import population.Individual;

/**
 * Thread to evaluate individual  
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class EvaluationThread implements Runnable {

	private EvaluationFunction<Individual, Void>  evaluationFunction;
	private Individual individual;

	/**
	 * Constructor
	 * @param evalFunction
	 * @param ind
	 */
	public EvaluationThread(EvaluationFunction<Individual, Void> evalFunction, Individual ind){
		evaluationFunction = evalFunction;
		individual = ind;
	}
	
	@Override
	public void run() {
		evaluationFunction.execute(individual);
		
	}

}
