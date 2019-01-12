package core;

import exceptions.GeneticAlgorithmException;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import modes.IndividualReplacementMode;
import modes.ParentsSelectionMode;
import modes.StopLimitMode;
import population.Individual;
import population.IndividualCreator;
import population.Population;
import results.Results;

/**
 * Genetic Algorithm Interface
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public interface IGeneticAlgorithm {
	
	/**
	 * Define Individual Creator
	 * @param builder
	 */
	public void setIndividualCreator(IndividualCreator creator);
	
	/**
	 * Define Evaluation Function
	 * @param function Evaluation function
	 */
	public void setEvaluationFunction(EvaluationFunction<Population, Void> function);
	
	/**
	 * Define Mutation function
	 * @param function Mutation function
	 * @return 
	 */
	public void setMutationFunction(MutationFunction<Individual, Void> function);
	
	/**
	 * Define CrossOver function
	 * @param function CrossOver function
	 */
	public void setCrossOverFunction(CrossOverFunction<Individual[], Individual> function);
	
	/**
	 * Define population
	 * @param size Number of individuals
	 * @throws GeneticAlgorithmException 
	 */
	public void setPopulation(int size) throws GeneticAlgorithmException;
		
	/**
	 * Define the mode of parents selection
	 */
	public void setParentsSelectionMode(ParentsSelectionMode mode);
	
	/**
	 * Define the mode of individual replacement
	 */
	public void setIndividualReplacementMode(IndividualReplacementMode mode);
	
	/**
	 * Define Stop mode
	 * @param mode 
	 * @param parameter time, n iterations, ...
	 */
	public void setStopMode(StopLimitMode mode, int parameter);
	
	/**
	 * Initialize Genetic Algorithm
	 */
	public void init();
	
	/**
	 * Run Genetic Algorithm
	 * @throws GeneticAlgorithmException 
	 */
	public void run() throws GeneticAlgorithmException;
	
	/**
	 * Get Results
	 * @return Results
	 */
	public Results getResult();

	
		
}
