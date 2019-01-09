package api;

import java.util.function.Function;

import Individual.Individual;
import Individual.Population;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;
import testQL.Algorithm;
import testQL.Skill;

public class GeneticAPI implements IGeneticApi {
	

	private Individual individualBuilder;
	private Population population;
	
	private EvaluationFunction evaluationFunction;
	private MutationFunction mutationFunction;
	private CrossOverFunction crossoverFunction;
		
	@Override
	public void setIndividualImplementation(Individual builder) {
		this.individualBuilder=builder;
		
	}

	@Override
	public void setEvaluationFunction(EvaluationFunction function) {
		this.evaluationFunction=function;
		
	}

	@Override
	public void setMutationFunction(MutationFunction function) {
		this.mutationFunction=function;
		
	}

	@Override
	public void setCrossOverFunction(CrossOverFunction function) {
		this.crossoverFunction=function;
		
	}

	@Override
	public void setPopulation(int size) {
		
		population = new Population(size);
	}

	@Override
	public void setModes() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Initialisation de l'algorithme genetique avec les paramètres renseignes
	 */
	@Override
	public void init() {
		// Initialisation de la population
		population.init(individualBuilder);
		
	}

	@Override
	public void run() {
		
		int generationCount = 0;
		 while (population.getMoreCompetent().getSkill() <  ((byte[]) (evaluationFunction.execute(null))).length ) {
	            generationCount++;
	            generationCount++;
	            System.out.println("Generation: " + generationCount + " competence: " + population.getMoreCompetent().getSkill());
	            population = evolvePopulation();
		 }
		
	}
	
	/**
	 * Algorithme génétique
	 * @return
	 */
	private Population evolvePopulation() {
	
		Population newPopulation = new Population(population.size());
	
		// crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }
 
        // Mutate population
        for (int i = 0; i < newPopulation.size(); i++) {
        	
            mutate(newPopulation.getIndividual(i));
        }
	
		population = newPopulation;
		
	}
	
	// = FONCTION DEVAL
	// Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
    	int tournamentSize = 5;
        // Create a tournament population
        Population tournament = new Population(tournamentSize);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getMoreCompetent();
        return fittest;
    }

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
