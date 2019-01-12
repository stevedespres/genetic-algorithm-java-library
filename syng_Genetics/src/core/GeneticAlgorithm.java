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
import population.Population;
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

	/* Population and Invividuals */
	private IndividualCreator individualCreator;
	private Population population;

	/* Functions */
	private EvaluationFunction<Population, Void> evaluationFunction;
	private MutationFunction<Individual, Void> mutationFunction;
	private CrossOverFunction<Individual[], Individual> crossoverFunction;
	
	/* Results */ 
	private Results results;
	
	/* Modes */ 
	private static ParentsSelectionMode parentsSelectionMode = ParentsSelectionMode.Random;
	private static IndividualReplacementMode individualReplacementMode = IndividualReplacementMode.Default;
	private static StopLimitMode stopLimitMode = StopLimitMode.No;
	private static int stopLimitParameter = 0;
	
	/**
	 * Set Individuals Builder
	 */
	@Override
	public void setIndividualCreator(IndividualCreator creator) {
		this.individualCreator = creator;
	}

	/**
	 * Set Evaluation Function
	 */
	@Override
	public void setEvaluationFunction(EvaluationFunction<Population, Void> function) {
		this.evaluationFunction = function;
	}

	/**
	 * Set Mutation Function
	 */
	@Override
	public void setMutationFunction(MutationFunction<Individual, Void> function) {
		this.mutationFunction = function;
	}

	/**
	 * Set CrossOver Function
	 */
	@Override
	public void setCrossOverFunction(CrossOverFunction<Individual[], Individual> function) {
		this.crossoverFunction = function;
	}

	/**
	 * Set Population
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void setPopulation(int size) throws GeneticAlgorithmException {
		population = new Population(size);
	}

	/**
	 * Init Genetic Algorithm
	 */
	@Override
	public void init() {
		LOGGER.info("Initialize population");
		population.init(individualCreator);
	}

	/**
	 * Run Genetic ALgorithm 
	 * @throws GeneticAlgorithmException 
	 */
	@Override
	public void run() throws GeneticAlgorithmException {
		
		LOGGER.info("Run Genetic Algorithm");
		
		// Iteration number
		int generationCount = 0;
		
		// While skill of individual is not optimum or stop limit reached
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
		
		for (int i = 0; i < population.getSize(); i++) {
			/* Evaluation */
			LOGGER.info("EVALUATION");
			evaluationFunction.execute(population);
			/* Selection */
			LOGGER.info("SELECTION");
			Individual indiv1 = selectParent();
			Individual indiv2 = selectParent();
			/* Crossover */
			LOGGER.info("CROSSOVER");
			Individual[] tab = { indiv1, indiv2 };
			Individual newIndiv = crossoverFunction.execute(tab);
			/* Replacement */
			LOGGER.info("REPLACEMENT");
			newPopulation = replaceIndividual(newPopulation, newIndiv, i);
		}

		/* Mutation */ 
		for (int i = 0; i < newPopulation.getSize(); i++) {
			mutationFunction.execute(newPopulation.getIndividual(i));
		}
		
		/* Evaluate skills of individuals of the new population */
		evaluationFunction.execute(newPopulation);
		
		/* Evolve */
		population = newPopulation;
	}
	
	/**
	 * Select one parent
	 * @return
	 * @throws GeneticAlgorithmException 
	 */
	private Individual selectParent() throws GeneticAlgorithmException {
		
		/* Random parents selection mode */
		if(parentsSelectionMode == ParentsSelectionMode.Random) {
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
		else if(parentsSelectionMode == ParentsSelectionMode.Bests) {
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
	public Population replaceIndividual(Population pop, Individual ind, int i) {
		
		/* Default mode */ 
		if(individualReplacementMode == IndividualReplacementMode.Default) {
			pop.saveIndividual(i, ind);
			return pop;
		}
		/* Random mode */ 
		else if(individualReplacementMode == IndividualReplacementMode.Random) {
			int index = (int) Math.random() * population.getSize();
			pop.saveIndividual(index, ind);
			return pop;
		}
		/* Replace a less competent individual */
		else if(individualReplacementMode == IndividualReplacementMode.Best) {
			/* Evaluate skills of individuals of the population */
			evaluationFunction.execute(pop);
			for(int j=0; j>pop.getSize();j++) {
				if(ind.getSkill() > pop.getIndividual(i).getSkill()) {
					pop.saveIndividual(j, ind);
					return pop;
				}
			}
		}
		return pop;
	}
	
	/**
	 * Manage stop limit
	 * @return
	 * @throws GeneticAlgorithmException 
	 */
	public boolean stopLimit(int iteration, int s) throws GeneticAlgorithmException {
		/* No limit */
		if(stopLimitMode == StopLimitMode.No) {
			return false;
		} 
		/* Individual evolution limit */
		else if(stopLimitMode == StopLimitMode.IndividualEvolution) {
			if(iteration > stopLimitParameter) {
				// Check individual evolution
			}
		}
		/* Population Individual limit */
		else if(stopLimitMode == StopLimitMode.PopulationEvolution) {
			if(iteration > stopLimitParameter) {
				// Check population evolution
			}
		}
		/* Time limit */
		else if(stopLimitMode == StopLimitMode.Time) {
			if(s > stopLimitParameter) {
				return true;
			}
		}
		/* Iterations limit */
		else if(stopLimitMode == StopLimitMode.Iteration) {
			if(iteration > stopLimitParameter) {
				return true;
			}
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
	public void setParentsSelectionMode(ParentsSelectionMode mode) {
		parentsSelectionMode = mode;
	}

	/**
	 * Define Individual Replacement modes
	 */
	@Override
	public void setIndividualReplacementMode(IndividualReplacementMode mode) {
		individualReplacementMode = mode;
	}

	/**
	 * Define Stop mode limit
	 */
	@Override
	public void setStopMode(StopLimitMode mode, int parameter) {
		stopLimitMode = mode;
		stopLimitParameter = parameter;
	}
}
