package api;

import Individual.Individual;
import Individual.Skill;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;

public interface IGeneticApi {
	
	/**
	 * D�finir la fonction pour construire les individus
	 * @param builder
	 */
	public void setIndividualImplementation(Individual builder);
	
	/**
	 * D�finir la fonction d'�valuation des individus
	 * @param function Fonction d'�valuation
	 */
	public <T, R> void setEvaluationFunction(EvaluationFunction<T, R> function);
	
	/**
	 * D�finir la fonction de mutation
	 * @param function Fonction de mutation
	 * @return 
	 */
	public <T, R> void setMutationFunction(MutationFunction<T, R> function);
	
	/**
	 * D�finir la fonction de croisement
	 * @param function Fonction de croisement
	 */
	public <T, R> void setCrossOverFunction(CrossOverFunction<T, R> function);
	
	/**
	 * D�finir la population
	 * @param size Taille de la population
	 * @param percentageOfChildsGenerated Pourcentage d'individus g�n�r�s � chaque cycle
	 */
	public void setPopulation(int size);
	
	public void setSkill(Skill skill);
	
	/**
	 * D�finir les modes : stope, selection, nouveau individus
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
