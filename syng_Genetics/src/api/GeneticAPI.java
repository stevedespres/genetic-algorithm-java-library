package api;

import Individual.Individual;
import Individual.Population;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;
import Individual.Skill;

public class GeneticAPI implements IGeneticApi {
	

	private Individual individualBuilder;
	private Population population;
	
	private EvaluationFunction evaluationFunction;
	private MutationFunction mutationFunction;
	private CrossOverFunction crossoverFunction;
	
	private Skill skill;
		
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
		
	}

	@Override
	public void run() {
		
		int generationCount = 0;
		int skillMoreCompetent = 0;
		
		do {
			
			skillMoreCompetent = skill.getSkill(population.getMoreCompetent());
			population.getMoreCompetent().setSkill(skillMoreCompetent);
		
	
			
			
	            generationCount++;
	            generationCount++;
	            System.out.println("Generation: " + generationCount + " competence: " + population.getMoreCompetent().getSkill());
	            evolvePopulation();
		
		 }while(skillMoreCompetent < skill.getMaxSkill() );
		
		   System.out.println("Solution found!");
	        System.out.println("Generation: " + generationCount);
	        System.out.println("Genes:");
	        System.out.println(population.getMoreCompetent());
	}
	
	/**
	 * Algorithme génétique
	 * @return
	 */
	private void evolvePopulation() {
	
		Population newPopulation = new Population(population.getSize());
	
		// Croisement
        for (int i = 0; i < population.getSize(); i++) {
            Individual indiv1 = (Individual) evaluationFunction.execute(population);
            Individual indiv2 = (Individual) evaluationFunction.execute(population);
            
            Individual[] tab = {indiv1, indiv2};
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
