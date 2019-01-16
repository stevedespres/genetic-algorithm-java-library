package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import population.Population;
import process.CrossOverThread;
import process.EvaluationThread;
import process.MutationThread;
import results.Results;

/**
 * Implementation of Genetic Algorithm Interface
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */

public class GeneticAlgorithm implements IGeneticAlgorithm {

	/* Logger */
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithm.class.getName());

	/* Population and Individuals */
	private IndividualCreator individualCreator;
	private Population population;

	/* Functions */
	private EvaluationFunction<Individual, Void> evaluationFunction;
	private MutationFunction<Individual, Void> mutationFunction;
	private CrossOverFunction<Individual[], Individual> crossoverFunction;
	
	/* Results */ 
	private Results results;
	
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
		population = new Population(size);
	}

	/**
	 * Initialize Genetic Algorithm
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void init() throws GeneticAlgorithmException {
		LOGGER.info("Initialize population");
		checkImplementation();
		population.init(individualCreator);
		init=true;
	}

	/**
	 * Run Genetic ALgorithm 
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void run() throws GeneticAlgorithmException {
		
		LOGGER.info("Run Genetic Algorithm");
		checkInitialization();
		/* Iteration number */
		int generationCount = 0;
		
		/* While skill of individual is not optimum or stop limit reached */
		while (population.getMoreCompetent().getSkill() < population.getMoreCompetent().getGeneLength() || stopLimit(generationCount, 0) ) {
			generationCount++;
			LOGGER.info("Generation: " + generationCount + " competence: " + population.getMoreCompetent().getSkill());
			evolvePopulation();
		}
		/* Get the results */
		results = new Results(true, generationCount, population.getMoreCompetent() );
	}

	/**
	 * Evolve population
	 * @throws GeneticAlgorithmException 
	 */
	private void evolvePopulation() throws GeneticAlgorithmException {
		
		/* Create new population */
		Population newPopulation = new Population(population.getSize());
		/* CrossOver */
		crossover(newPopulation);
		/* Mutation of individuals */ 
		mutate(newPopulation);
		/* Evaluate skills of individuals of the new population */
		evaluate(newPopulation);
		/* Evolve */
		population = newPopulation;
	}
	
	/**
	 * Evaluate a population
	 * @param pop
	 */
	private void evaluate(final Population pop) {
		LOGGER.info("EVALUATION");
		/* Service to execute in parallel */ 
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		/* For each individual */
		for(Individual ind : pop.getIndividuals()) {
			/* Evaluate in a thread */
			exec.execute(new EvaluationThread(evaluationFunction, ind));
		}
		exec.shutdown();
		while(!exec.isTerminated());
	}
	
	/**
	 * Select parents and crossover 
	 * @return
	 * @throws GeneticAlgorithmException
	 */
	private void crossover(Population pop) throws GeneticAlgorithmException {
		
		/* List of threads */
		List<CrossOverThread> list = new ArrayList<>();
		/* Service to execute in parallel */ 
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		/* For each individual */
		for (int i = 0; i < pop.getSize(); i++) {	
			
			/* Select parents and crossover */
			LOGGER.info("SELECTION");
			Individual indiv1 = selectParent();
			Individual indiv2 = selectParent();

			/* Crossover */
			LOGGER.info("CROSSOVER");
			Individual[] parents = { indiv1, indiv2 };

			/* CrossOver in a thread */
			CrossOverThread crossOverThread = new CrossOverThread(crossoverFunction, parents);
			exec.execute(crossOverThread);		
			list.add(crossOverThread);		
		}
		exec.shutdown();
		while(!exec.isTerminated());
		/* Update individuals in population */
		replacement(pop, list);
	}
	
	/**
	 * Replace individuals after Crossover
	 * @param pop
	 * @param list
	 * @throws GeneticAlgorithmException
	 */
	private void replacement(Population pop, List<CrossOverThread> list) throws GeneticAlgorithmException {
		int pos = 0;
		for (CrossOverThread coThread : list)
		{
			/* Get child */
			Individual child = coThread.getChild();
			/* Replacement */
			replaceIndividual(pop, child, pos);
			pos++;
		}
	}
	
	/**
	 * Mutate the individuals
	 * @param pop
	 */
	private void mutate(final Population pop) {
		LOGGER.info("MUTATION");
		/* Service to execute in parallel */ 
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		/* For each individual */
		for (Individual ind : pop.getIndividuals()) {
			/* Evaluate in a thread */
			exec.execute(new MutationThread(mutationFunction, ind));
		}
		exec.shutdown();
		while(!exec.isTerminated());
	}
	
	/**
	 * Select one parent
	 * @return
	 * @throws GeneticAlgorithmException 
	 */
	private Individual selectParent() throws GeneticAlgorithmException {
		
		/* Random parents selection mode */
		if(parentsSelectionMode == ParentsSelectionMode.RANDOM) {
			/** Selection (fitness) **/
		    int randomId=0;
		    int tournamentSize = 5;
	        /* Create a tournament population */
			Population tournament = new Population(tournamentSize);
	        /* For each place in the tournament get a random individual */
	        for (int i = 0; i < tournamentSize; i++) {
	            randomId = (int) (Math.random() * population.getSize());
	            tournament.saveIndividual(i, population.getIndividual(randomId));
	        }
	        /* Get the fittest */
	        Individual fittest = tournament.getMoreCompetent();
	        return fittest;	
		}
		
		/* Bests parents selection mode */ 
		else if(parentsSelectionMode == ParentsSelectionMode.BESTS) {
			return population.getMoreCompetent();
		}
		else {
			throw new GeneticAlgorithmException("No Parents Selection mode is defined");
		}
		
	}
	
	/**
	 * Replace Individual in a Population
	 * @param pop
	 * @param ind
	 */
	private void replaceIndividual(final Population pop,final Individual ind, int i) {
		LOGGER.info("REPLACEMENT");
		/* Default mode */ 
		if(individualReplacementMode == IndividualReplacementMode.DEFAULT) {
			pop.saveIndividual(i, ind);
		}
		/* Random mode */ 
		else if(individualReplacementMode == IndividualReplacementMode.RANDOM) {
			int index = (int) Math.random() * population.getSize();
			pop.saveIndividual(index, ind);
		}
		/* Replace a less competent individual */
		else if(individualReplacementMode == IndividualReplacementMode.BEST) {
			/* Evaluate skills of individuals of the population */
			evaluate(pop);
			for(int j=0; j>pop.getSize();j++) {
				if(ind.getSkill() > pop.getIndividual(i).getSkill()) {
					pop.saveIndividual(j, ind);
				}
			}
		}
	}
	
	/**
	 * Manage stop limit
	 * @return
	 * @throws GeneticAlgorithmException 
	 */
	public boolean stopLimit(final int iteration,final int s) throws GeneticAlgorithmException {
		/* No limit */
		if(stopLimitMode == StopLimitMode.NO) {
			return false;
		} 
		/* Individual evolution limit */
		else if(stopLimitMode == StopLimitMode.INDIVIDUAL_EVOLUTION) {
			if(iteration > stopLimitParameter) {
				// Check individual evolution
			}
		}
		/* Population Individual limit */
		else if(stopLimitMode == StopLimitMode.POPULATION_EVOLUTION) {
			if(iteration > stopLimitParameter) {
				// Check population evolution
			}
		}
		/* Time limit */
		else if(stopLimitMode == StopLimitMode.TIME) {
			if(s > stopLimitParameter) {
				return true;
			}
		}
		/* Iterations limit */
		else if(stopLimitMode == StopLimitMode.ITERATION && iteration > stopLimitParameter) {
				return true;
		}
		return false;
	}

	/**
	 * Get Results
	 */
	@Override
	public Results getResult() {
		return results;
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
	
	private void checkImplementation() throws GeneticAlgorithmException {
		StringBuilder error = new StringBuilder();
		boolean success = true;
		
		if(individualCreator == null) {
			error.append("IndividualCreator is not implemented\n");
			success=false;
		}
		if(population == null) {
			error.append("Population is not implemented\n");
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
	}
	
	private void checkInitialization() throws GeneticAlgorithmException {
		if(!init) {
			throw new GeneticAlgorithmException("Genetic Algorithm need to be initializing");
		}
	}
}
