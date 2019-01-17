package example;

import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import modes.IndividualReplacementMode;
import modes.ParentsSelectionMode;
import modes.StopLimitMode;
import population.IIndividual;
import population.IIndividualCreator;

import java.util.logging.Logger;

import core.GeneticAlgorithm;
import exceptions.GeneticAlgorithmException;

/**
 * Main class : Use case of the Genetic Algorithm
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class Main {

	public static void main(String[] args) {
		
		/* Logger */
		Logger LOGGER = Logger.getLogger(Main.class.getName());
		try {
			/* Initialize Genetic ALgorithm Interface */
			LOGGER.info("New genetic API created");
			GeneticAlgorithm genetic = new GeneticAlgorithm();
	
			/* initialize Individual Creator */
			IIndividualCreator creator = new MyIndividualCreator();
			LOGGER.info("Initialize Individuals Creator");
			genetic.setIndividualCreator(creator);
			
			/* Set Population */
		
			genetic.setPopulation(50);
			
			/** Example of Evaluation function **/ 
			EvaluationFunction<IIndividual,Void> evaluationFunction = new EvaluationFunction<>(				
					(ind) -> {							
						 /** Initialize an optimum solution **/ 
						 byte[] solution;
						 String character;
						 
						 String stringSolution = "1111000000000000000000000000001111100000000000000000000000001111";
						 /*String solution to byte[] */
						 solution = new byte[stringSolution.length()];
						 for (int i = 0; i < stringSolution.length(); i++) {
						    character = stringSolution.substring(i, i + 1);
						    if (character.contains("0") || character.contains("1")) {
						        solution[i] = Byte.parseByte(character);
						    } else {
						        solution[i] = 0;
					 	    }
					     }
						
						/** Calculate the skill of individual compared by the optimum solution **/		
						 int skill = 0;	
				
					 	/* Loop through our individuals genes and compare them to the solution */
				        for (int i = 0; i < ind.getGeneLength() && i < solution.length; i++) {
				            if ((byte) ind.getGene(i) == solution[i]) {
				                skill++;
				            }
				        }       
				        ind.setSkill(skill);
						 
						return null;
			});
			/* Set Evaluation Function */
			genetic.setEvaluationFunction(evaluationFunction);
			
			/** Exemple of Mutation Function **/ 
			MutationFunction<IIndividual,Void> mutationFunction = new MutationFunction<>(				
					(ind) -> {	
						double mutationRate = 0.0003;
						for (int i = 0; i < ind.getGeneLength(); i++) {
				            if (Math.random() <= mutationRate) {
				                /* Create random gene */
				                byte gene = (byte) Math.round(Math.random());
				                ind.setGene(i, gene);
				            }
				        }
						return null;
			});		
			/* Set Mutation Function */
			genetic.setMutationFunction(mutationFunction);
			
			/** Example of CrossOver Function **/ 
			CrossOverFunction<IIndividual[], IIndividual> crossoverFunction = new CrossOverFunction<>(				
					(individuals) -> {	
						double uniformRate = 0.5;
						/* Select 2 individuals */
						IIndividual individual1 = individuals[0];
						IIndividual individual2 = individuals[1];
						/* Create new individuals */
											
						IIndividual newInd = creator.CreateIndividual();
				        /* Loop through genes */
				        for (int i = 0; i < individual1.getGeneLength(); i++) {
				            /* Crossover */
				            if (Math.random() <= uniformRate) {
				            	newInd.setGene(i, individual1.getGene(i));
				            } else {
				            	newInd.setGene(i, individual2.getGene(i));
				            }
				        }
				        return newInd;
			});		
			/* Set CrossOver Function */
			genetic.setCrossOverFunction(crossoverFunction);
			
			/* Set Modes */
			genetic.setParentsSelectionMode(ParentsSelectionMode.RANDOM);
			genetic.setIndividualReplacementMode(IndividualReplacementMode.DEFAULT);
			genetic.setStopMode(StopLimitMode.NO, 0);
			//genetic.setStopMode(StopLimitMode.TIME, 1);
			
			/* Initialize Genetic Algorithm */ 
			genetic.init();
			long startTime = System.currentTimeMillis();
			genetic.run();
			long endTime = System.currentTimeMillis();
			/* Get results */ 
			LOGGER.info(genetic.getResult().toString());
			LOGGER.info("Time of execution : "+String.valueOf(((double)(endTime-startTime))/1000 +" Seconds"));
			
		} catch (GeneticAlgorithmException e) {
			LOGGER.info(e.getMessage());
		}
		
		
	}
	
}
