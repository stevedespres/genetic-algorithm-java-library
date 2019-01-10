package api;

import java.util.logging.Logger;

import Individual.Individual;
import Individual.Population;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;
import Individual.Skill;
import console.Main;

public class GeneticAPI implements IGeneticApi {

	private final static Logger logger = Logger.getLogger(GeneticAPI.class.getName());

	private Individual individualBuilder;
	private Population population;

	@SuppressWarnings("rawtypes")
	private EvaluationFunction evaluationFunction;

	@SuppressWarnings("rawtypes")
	private MutationFunction mutationFunction;

	@SuppressWarnings("rawtypes")
	private CrossOverFunction crossoverFunction;

	private Skill skill;

	@Override
	public void setIndividualImplementation(Individual builder) {
		this.individualBuilder = builder;
	}

	@Override
	public void setEvaluationFunction(EvaluationFunction function) {
		this.evaluationFunction = function;
	}

	@Override
	public void setMutationFunction(MutationFunction function) {
		this.mutationFunction = function;
	}

	@Override
	public void setCrossOverFunction(CrossOverFunction function) {
		this.crossoverFunction = function;
	}

	@Override
	public void setPopulation(int size) {
		population = new Population(size);
	}

	@Override
	public void setModes() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * Initialisation de l'algorithme genetique avec les paramètres renseignes
	 */
	@Override
	public void init() {
		// Initialisation de la population
		population.init(individualBuilder);
		logger.info("Population initializing...");
	}

	@Override
	public void run() {

		// Interation number
		int generationCount = 0;
		// the highest skill score
		int skillMoreCompetent = 0;
		// CONDITION D'ARRET N EST JAMAIS VRAIE 
		while (/*generationCount<=population.getSize()*/skillMoreCompetent <= skill.getMaxSkill()) {
			skillMoreCompetent = skill.getSkill(population.getMoreCompetent());
			population.getMoreCompetent().setSkill(skillMoreCompetent);
			generationCount++;
			// generationCount++;
			logger.info("Generation: " + generationCount + " competence: " + population.getMoreCompetent().getSkill());
			evolvePopulation();
		}
		logger.info("Solution found!");
		logger.info("Generation: " + generationCount);
		logger.info("Genes:");
		System.out.println(population.getMoreCompetent().getSkill());
	}

	/**
	 * Algorithme génétique
	 * 
	 * @return
	 */
	private void evolvePopulation() {
		Population newPopulation = new Population(population.getSize());
		// Croisement
		for (int i = 0; i < population.getSize(); i++) {
			Individual indiv1 = (Individual) evaluationFunction.execute(population);
			Individual indiv2 = (Individual) evaluationFunction.execute(population);

			Individual[] tab = { indiv1, indiv2 };
			Individual newIndiv = (Individual) crossoverFunction.execute(tab);
			newPopulation.saveIndividual(i, newIndiv);
		}

		// Mutation
		for (int i = 0; i < newPopulation.getSize(); i++) {

			mutationFunction.execute(newPopulation.getIndividual(i));
		}

		population = newPopulation;

	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
