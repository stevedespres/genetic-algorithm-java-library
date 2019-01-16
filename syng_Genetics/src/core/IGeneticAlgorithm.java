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
	public void setIndividualCreator(final IndividualCreator creator);
	
	/**
	 * Define Evaluation Function
	 * @param function Evaluation function
	 */
	public void setEvaluationFunction(final EvaluationFunction<Individual, Void> function);
	
	/**
	 * Define Mutation function
	 * @param function Mutation function
	 * @return 
	 */
	public void setMutationFunction(final MutationFunction<Individual, Void> function);
	
	/**
	 * Define CrossOver function
	 * @param function CrossOver function
	 */
	public void setCrossOverFunction(final CrossOverFunction<Individual[], Individual> function);
	
	/**
	 * Define population
	 * @param size Number of individuals
	 * @throws GeneticAlgorithmException 
	 */
	public void setPopulation(final int size) throws GeneticAlgorithmException;
		
	/**
	 * Define the mode of parents selection
	 */
	public void setParentsSelectionMode(final ParentsSelectionMode mode);
	
	/**
	 * Define the mode of individual replacement
	 */
	public void setIndividualReplacementMode(final IndividualReplacementMode mode);
	
	/**
	 * Define Stop mode
	 * @param mode 
	 * @param parameter time, n iterations, ...
	 */
	public void setStopMode(final StopLimitMode mode,final int parameter);
	
	/**
	 * Initialize Genetic Algorithm
	 * @throws GeneticAlgorithmException 
	 */
	public void init() throws GeneticAlgorithmException;
	
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
