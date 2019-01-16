package core;

import java.util.logging.Logger;

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
 * Implementation of Genetic Algorithm Interface
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */

public class GeneticAlgorithm implements IGeneticAlgorithm {

	/* Processor */
	private GeneticAlgorithmProcessor processor;
	
	/* Logger */
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithm.class.getName());

	/* Population and Individuals */
	private IndividualCreator individualCreator;
	private int populationSize;

	/* Functions */
	private EvaluationFunction<Individual, Void> evaluationFunction;
	private MutationFunction<Individual, Void> mutationFunction;
	private CrossOverFunction<Individual[], Individual> crossoverFunction;
		
	/* Modes */ 
	private static ParentsSelectionMode parentsSelectionMode = ParentsSelectionMode.RANDOM;
	private static IndividualReplacementMode individualReplacementMode = IndividualReplacementMode.DEFAULT;
	private static StopLimitMode stopLimitMode = StopLimitMode.NO;
	private static int stopLimitParameter = 0;
	
	private static boolean init = false;
	
	/**
	 * Set Individuals Builder
	 */
	@Override
	public void setIndividualCreator(final IndividualCreator creator) {
		this.individualCreator = creator;
	}

	/**
	 * Set Evaluation Function
	 */
	@Override
	public void setEvaluationFunction(final EvaluationFunction<Individual, Void> function) {
		this.evaluationFunction = function;
	}

	/**
	 * Set Mutation Function
	 */
	@Override
	public void setMutationFunction(final MutationFunction<Individual, Void> function) {
		this.mutationFunction = function;
	}

	/**
	 * Set CrossOver Function
	 */
	@Override
	public void setCrossOverFunction(final CrossOverFunction<Individual[], Individual> function) {
		this.crossoverFunction = function;
	}

	/**
	 * Set Population
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void setPopulation(final int size) throws GeneticAlgorithmException {
		populationSize = size;
	}
	
	/**
	 * Define Parents selection modes
	 */
	@Override
	public void setParentsSelectionMode(final ParentsSelectionMode mode) {
		parentsSelectionMode = mode;
	}

	/**
	 * Define Individual Replacement modes
	 */
	@Override
	public void setIndividualReplacementMode(final IndividualReplacementMode mode) {
		individualReplacementMode = mode;
	}

	/**
	 * Define Stop mode limit
	 */
	@Override
	public void setStopMode(final StopLimitMode mode, final int parameter) {
		stopLimitMode = mode;
		stopLimitParameter = parameter;
	}

	/**
	 * Initialize Genetic Algorithm
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void init() throws GeneticAlgorithmException {
		LOGGER.info("Initialize population");
		/* Check if all parameters are defined */
		checkParameters();
		
		processor = new GeneticAlgorithmProcessor(
				individualCreator,
				populationSize,
				evaluationFunction,
				mutationFunction, 
				crossoverFunction, 
				parentsSelectionMode,
				individualReplacementMode, 
				stopLimitMode,
				stopLimitParameter
				);
		init=true;
	}

	/**
	 * Run Genetic ALgorithm 
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void run() throws GeneticAlgorithmException {
		/* Check if processor is initialized */
		if(checkInitialization()) {
			processor.run();
		}
	}
	
	/**
	 * Get Results
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public Results getResult() throws GeneticAlgorithmException {
		return processor.getResult();
	}

	/**
	 * Check if all parameters are defined 
	 * @return
	 * @throws GeneticAlgorithmException
	 */
	private boolean checkParameters() throws GeneticAlgorithmException {
		StringBuilder error = new StringBuilder();
		boolean success = true;
		
		if(individualCreator == null) {
			error.append("IndividualCreator is not implemented\n");
			success=false;
		}
		if(populationSize == 0) {
			error.append("Population size is not defined\n");
			success=false;
		}
		if(evaluationFunction == null) {
			error.append("EvaluationFunction is not implemented\n");
			success=false;
		}
		if(mutationFunction == null) {
			error.append("MutationFunction is not implemented\n");
			success=false;
		}
		if(crossoverFunction == null) {
			error.append("CrossoverFunction is not implemented\n");
			success=false;
		}
		if(!success) {
			throw new GeneticAlgorithmException(error.toString());
		}
		return success;
	}
	
	/**
	 * Check if processor is initialized 
	 * @return
	 * @throws GeneticAlgorithmException
	 */
	private boolean checkInitialization() throws GeneticAlgorithmException {
		if(!init) {
			throw new GeneticAlgorithmException("Genetic Algorithm need to be initializing");
		}
		return init;
	}
}
