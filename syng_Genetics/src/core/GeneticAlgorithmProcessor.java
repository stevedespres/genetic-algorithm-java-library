package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.GeneticAlgorithmException;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import modes.IndividualReplacementMode;
import modes.ParentsSelectionMode;
import modes.StopLimitMode;
import population.IIndividual;
import population.IIndividualCreator;
import population.Population;
import process.CrossOverThread;
import process.EvaluationThread;
import process.MutationThread;
import results.Results;
import stopCriteria.TimeCriteria;

/**
 * Genetic Algorithm Processor
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class GeneticAlgorithmProcessor {

	/* Logger */
	private final static Logger LOGGER = Logger.getLogger(GeneticAlgorithmProcessor.class.getName());

	/* Population */
	private Population population;

	/* Functions */
	private EvaluationFunction<IIndividual, Void> evaluationFunction;
	private MutationFunction<IIndividual, Void> mutationFunction;
	private CrossOverFunction<IIndividual[], IIndividual> crossoverFunction;

	/* Results */
	private Results results;

	/* Modes */
	private static ParentsSelectionMode parentsSelectionMode = ParentsSelectionMode.RANDOM;
	private static IndividualReplacementMode individualReplacementMode = IndividualReplacementMode.DEFAULT;
	private static StopLimitMode stopLimitMode = StopLimitMode.NO;
	private static int stopLimitParameter = 0;

	/**
	 * Constructor
	 * 
	 * @param creator
	 *            Individual creator
	 * @param popSize
	 *            Population size
	 * @param evalFct
	 *            Evaluation Function
	 * @param mutFct
	 *            Mutation Function
	 * @param coFct
	 *            CrossOver Function
	 * @param psMode
	 *            Parents selection Mode
	 * @param irMode
	 *            Individual Replacement Mode
	 * @param slMode
	 *            Stop Limit Mode
	 * @param stopParameter
	 *            Stop Parameter
	 * @throws GeneticAlgorithmException
	 *             Exception
	 */
	public GeneticAlgorithmProcessor(IIndividualCreator creator, int popSize,
			EvaluationFunction<IIndividual, Void> evalFct, MutationFunction<IIndividual, Void> mutFct,
			CrossOverFunction<IIndividual[], IIndividual> coFct, ParentsSelectionMode psMode,
			IndividualReplacementMode irMode, StopLimitMode slMode, int stopParameter)
			throws GeneticAlgorithmException {
		/* Initialize parameters */
		population = new Population(popSize);
		evaluationFunction = evalFct;
		mutationFunction = mutFct;
		crossoverFunction = coFct;
		parentsSelectionMode = psMode;
		individualReplacementMode = irMode;
		stopLimitParameter = stopParameter;
		/* Initialize Population */
		population.init(creator);
	}

	/**
	 * Run genetic algorithm
	 * 
	 * @throws GeneticAlgorithmException
	 */
	public void run() throws GeneticAlgorithmException {
		LOGGER.info("Run Genetic Algorithm");
		/* Iteration number */
		int generationCount = 0;

		/* While skill of individual is not optimum or stop limit reached */
		while (population.getMoreCompetent().getSkill() < population.getMoreCompetent().getGeneLength()
				|| stopLimit(generationCount, 0)) {
			generationCount++;
			LOGGER.info("Generation: " + generationCount + " competence: " + population.getMoreCompetent().getSkill());
			evolvePopulation();

		}
		/* Get the results */
		results = new Results(true, generationCount, population.getMoreCompetent());
	}

	/**
	 * Evolve population
	 * 
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
	 * 
	 * @param pop
	 */
	private void evaluate(final Population pop) {
		LOGGER.info("EVALUATION");
		/* Service to execute in parallel */
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		/* For each individual */
		for (IIndividual ind : pop.getIndividuals()) {
			/* Evaluate in a thread */
			exec.execute(new EvaluationThread(evaluationFunction, ind));
		}
		exec.shutdown();
		while (!exec.isTerminated())
			;
	}

	/**
	 * Select parents and crossover
	 * 
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
			IIndividual indiv1 = selectParent();
			IIndividual indiv2 = selectParent();

			/* Crossover */
			LOGGER.info("CROSSOVER");
			IIndividual[] parents = { indiv1, indiv2 };

			/* CrossOver in a thread */
			CrossOverThread crossOverThread = new CrossOverThread(crossoverFunction, parents);
			exec.execute(crossOverThread);
			list.add(crossOverThread);
		}
		exec.shutdown();
		while (!exec.isTerminated())
			;
		/* Update individuals in population */
		replacement(pop, list);
	}

	/**
	 * Replace individuals after Crossover
	 * 
	 * @param pop
	 * @param list
	 * @throws GeneticAlgorithmException
	 */
	private void replacement(Population pop, List<CrossOverThread> list) throws GeneticAlgorithmException {
		int pos = 0;
		for (CrossOverThread coThread : list) {
			/* Get child */
			IIndividual child = coThread.getChild();
			/* Replacement */
			replaceIndividual(pop, child, pos);
			pos++;
		}
	}

	/**
	 * Mutate the individuals
	 * 
	 * @param pop
	 */
	private void mutate(final Population pop) {
		LOGGER.info("MUTATION");
		/* Service to execute in parallel */
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		/* For each individual */
		for (IIndividual ind : pop.getIndividuals()) {
			/* Evaluate in a thread */
			exec.execute(new MutationThread(mutationFunction, ind));
		}
		exec.shutdown();
		while (!exec.isTerminated())
			;
	}

	/**
	 * Select one parent
	 * 
	 * @return
	 * @throws GeneticAlgorithmException
	 */
	private IIndividual selectParent() throws GeneticAlgorithmException {

		/* Random parents selection mode */
		if (parentsSelectionMode == ParentsSelectionMode.RANDOM) {
			/** Selection (fitness) **/
			int randomId = 0;
			int tournamentSize = 5;
			/* Create a tournament population */
			Population tournament = new Population(tournamentSize);
			/* For each place in the tournament get a random individual */
			for (int i = 0; i < tournamentSize; i++) {
				randomId = (int) (Math.random() * population.getSize());
				tournament.saveIndividual(i, population.getIndividual(randomId));
			}
			/* Get the fittest */
			IIndividual fittest = tournament.getMoreCompetent();
			return fittest;
		}

		/* Bests parents selection mode */
		else if (parentsSelectionMode == ParentsSelectionMode.BESTS) {
			return population.getMoreCompetent();
		} else {
			throw new GeneticAlgorithmException("No Parents Selection mode is defined");
		}

	}

	/**
	 * Replace Individual in a Population
	 * 
	 * @param pop
	 * @param ind
	 */
	private void replaceIndividual(final Population pop, final IIndividual ind, int i) {
		LOGGER.info("REPLACEMENT");
		/* Default mode */
		if (individualReplacementMode == IndividualReplacementMode.DEFAULT) {
			pop.saveIndividual(i, ind);
		}
		/* Random mode */
		else if (individualReplacementMode == IndividualReplacementMode.RANDOM) {
			int index = (int) Math.random() * population.getSize();
			pop.saveIndividual(index, ind);
		}
		/* Replace a less competent individual */
		else if (individualReplacementMode == IndividualReplacementMode.BEST) {
			/* Evaluate skills of individuals of the population */
			evaluate(pop);
			for (int j = 0; j > pop.getSize(); j++) {
				if (ind.getSkill() > pop.getIndividual(i).getSkill())
					pop.saveIndividual(j, ind);
			}
		}
	}

	/**
	 * Manage stop limit
	 * 
	 * @return
	 * @throws GeneticAlgorithmException
	 */
	private boolean stopLimit(final int iteration, final int s) throws GeneticAlgorithmException {
		/* No limit */
		if (stopLimitMode == StopLimitMode.NO) {
			return false;
		}
		/* Individual evolution limit */
		else if (stopLimitMode == StopLimitMode.INDIVIDUAL_EVOLUTION) {
			if (iteration > stopLimitParameter) {
				// Check individual evolution
			}
		}
		/* Population Individual limit */
		else if (stopLimitMode == StopLimitMode.POPULATION_EVOLUTION) {
			if (iteration > stopLimitParameter) {
				// Check population evolution
			}
		}
		/* Time limit */
		else if (stopLimitMode == StopLimitMode.TIME) {
			/*
			 * if(s > stopLimitParameter) { return true; }
			 */
			TimeCriteria tm = new TimeCriteria(stopLimitParameter);
			tm.startCompteur();
			tm.run();
			if (!tm.stopAlgorithm(null))
				return true;
		}
		/* Iterations limit */
		else if (stopLimitMode == StopLimitMode.ITERATION && iteration > stopLimitParameter) {
			return true;
		}
		return false;
	}

	/**
	 * Get results of genetic algorithme
	 * 
	 * @return
	 * @throws GeneticAlgorithmException
	 */
	public Results getResult() throws GeneticAlgorithmException {
		if (results != null) {
			return results;
		} else {
			throw new GeneticAlgorithmException("Error : results are not available");
		}
	}
}
