package api;

import Individual.Individual;
import Individual.Skill;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;

public interface IGeneticApi {
	
	/**
	 * Définir la fonction pour construire les individus
	 * @param builder
	 */
	public void setIndividualImplementation(Individual builder);
	
	/**
	 * Définir la fonction d'évaluation des individus
	 * @param function Fonction d'évaluation
	 */
	public <T, R> void setEvaluationFunction(EvaluationFunction<T, R> function);
	
	/**
	 * Définir la fonction de mutation
	 * @param function Fonction de mutation
	 * @return 
	 */
	public <T, R> void setMutationFunction(MutationFunction<T, R> function);
	
	/**
	 * Définir la fonction de croisement
	 * @param function Fonction de croisement
	 */
	public <T, R> void setCrossOverFunction(CrossOverFunction<T, R> function);
	
	/**
	 * Définir la population
	 * @param size Taille de la population
	 * @param percentageOfChildsGenerated Pourcentage d'individus générés à chaque cycle
	 */
	public void setPopulation(int size);
	
	public void setSkill(Skill skill);
	
	/**
	 * Définir les modes : stope, selection, nouveau individus
	 */
	public void setModes();
	
	/**
	 * Initialiser l'algorithme
	 */
	public void init();
	
	/**
	 * Lancer l'algorithme
	 */
	public void run();
	
	/**
	 * Obtenir les resultats de l'algorithme
	 * @return Resultats
	 */
	public Result getResult();
		
}
