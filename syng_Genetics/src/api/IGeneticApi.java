package api;

import java.util.function.Function;

import results.Result;

public interface IGeneticApi {
	
	/**
	 * Définir la fonction pour construire les individus
	 * @param function Fonction de construction d'individus
	 */
	public <T, R> void setIndividualsBuilder(Function<T, R> function);
	
	/**
	 * Définir la fonction d'évaluation des individus
	 * @param function Fonction d'évaluation
	 */
	public <T, R> void setEvaluationFunction(Function<T, R> function);
	
	/**
	 * Définir la fonction de mutation
	 * @param function Fonction de mutation
	 */
	public <T, R> void setMutationFunction(Function<T, R> function);
	
	/**
	 * Définir la fonction de croisement
	 * @param function Fonction de croisement
	 */
	public <T, R> void setCrossOverFunction(Function<T, R> function);
	
	/**
	 * Définir la population
	 * @param size Taille de la population
	 * @param percentageOfChildsGenerated Pourcentage d'individus générés à chaque cycle
	 */
	public <T, R> void setPopulation(int size, float percentageOfChildsGenerated);
	
	/**
	 * Définir les modes : stope, selection, nouveau individus
	 */
	public <T, R> void setModes();
	
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
