package console;

import console.MyIndividual;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;

import java.util.logging.Logger;

import Individual.Individual;
import Individual.Population;
import Individual.Skill;
import api.GeneticAPI;

/**
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class Main {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(Main.class.getName());
		
		/* Initialisation de l'api */
		GeneticAPI genetic = new GeneticAPI();
		logger.info("New genetic API created");
		
		/* Initialisation des paramètres */
		Individual individu = new MyIndividual();
		logger.info("Create individual from \"MyIndividual\" -- user class ");
		genetic.setIndividualImplementation(individu); // Implementation d'un individu par l'utilisateur
		genetic.setPopulation(50); // Population de 50 individus, 50% de selection à chaque cycle
		
		/** Fonction d'évaluation **/ 
		EvaluationFunction<Population,Individual> evaluationFunction = new EvaluationFunction<>(				
				(pop) -> {	
					
					logger.info("Fonction d'evaluation");
					int tournamentSize = 5;
			        // Create a tournament population
			        Population tournament = new Population(tournamentSize);
			        int randomId=0;
			        // For each place in the tournament get a random individual
			        for (int i = 0; i < tournamentSize; i++) {
			            randomId = (int) (Math.random() * pop.getSize());
			            //--------- System.out.println(randomId);
			            tournament.saveIndividual(i, pop.getIndividual(randomId));
			        }
			        // Get the fittest
			        Individual fittest = tournament.getMoreCompetent();
			        return fittest;
							
				
	
		});
		genetic.setEvaluationFunction(evaluationFunction); // Fonction d'evaluation
		
		/** Fonction de mutation **/ 
		MutationFunction<MyIndividual,Void> mutationFunction = new MutationFunction<>(				
				individual -> {	
					double mutationRate = 0.0003;
					for (int i = 0; i < individual.getGeneLength(); i++) {
			            if (Math.random() <= mutationRate) {
			                // Create random gene
			                byte gene = (byte) Math.round(Math.random());
			                individual.setGene(i, gene);
			            }
			        }
				
					return null;
		});		
		genetic.setMutationFunction(mutationFunction);
		
		/** Fonction de croisement **/ 
		CrossOverFunction<Individual[], Individual> crossoverFunction = new CrossOverFunction<>(				
			
				(individuals) -> {	
					// pb ici !
					logger.info("Fonction de croisement");
					Individual individual1 = individuals[0];
					Individual individual2 = individuals[1];
					
					double uniformRate = 0.5;
					Individual newSol = new MyIndividual();
			        // Loop through genes
			        for (int i = 0; i < individual1.getGeneLength(); i++) {
			            // Crossover
			            if (Math.random() <= uniformRate) {
			                newSol.setGene(i, individual1.getGene(i));
			            } else {
			                newSol.setGene(i, individual2.getGene(i));
			            }
			        }
			        return newSol;
		});		
		genetic.setCrossOverFunction(crossoverFunction);

		/** Modelisation de la solution a atteindre **/ 
		String stringSolution = "1111000000000000000000000000001111100000000000000000000000001111";
		byte[] solution = new byte[stringSolution.length()];
		
		for (int i = 0; i < stringSolution.length(); i++) {
		     String character = stringSolution.substring(i, i + 1);
		        if (character.contains("0") || character.contains("1")) {
		        solution[i] = Byte.parseByte(character);
		    } else {
		        solution[i] = 0;
		    }
		}		
		Skill skill = new MySkill(solution);
		genetic.setSkill(skill);
		genetic.init();
		genetic.run();
	}
	
}
