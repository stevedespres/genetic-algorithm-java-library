package api;

import java.util.function.Function;

import Individual.IndividualBuilder;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;

public interface IGeneticApi {
	
	/**
	 * Définir la fonction pour construire les individus
	 * @param <T>
	 * @param function Fonction de construction d'individus
	 */
	public <T, R> void setIndividualsBuilder(IndividualBuilder<T, R> function);
	
	/**
	 * Définir la fonction d'évaluation des individus
	 * @param function Fonction d'évaluation
	 */
	public void <T, R> setEvaluationFunction(EvaluationFunction<T, R> function);
	
	/**
	 * Définir la fonction de mutation
	 * @param function Fonction de mutation
	 */
	public void <T, R> setMutationFunction(MutationFunction<T, R> function);
	
	/**
	 * Définir la fonction de croisement
	 * @param function Fonction de croisement
	 */
	public void <T, R> setCrossOverFunction(CrossOverFunction<T, R> function);
	
	/**
	 * Définir la population
	 * @param size Taille de la population
	 * @param percentageOfChildsGenerated Pourcentage d'individus générés à chaque cycle
	 */
	public void setPopulation(int size, float percentageOfChildsGenerated);
	
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
