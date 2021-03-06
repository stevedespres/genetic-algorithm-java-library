package results;

import population.IIndividual;

/**
 * Results of Genetic Algorithm
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class Results {

	private boolean solutionFound;
	private int nbGenerations = 0;
	private IIndividual mostCompetent;
	
	/**
	 * Constructor
	 * @param solFound If solution found
	 * @param nbGen Number of generation
	 * @param bestIndividual Most Competent Individual
	 */
	public Results(final boolean solFound,final int nbGen,final IIndividual bestIndividual){
		solutionFound = solFound;
		nbGenerations = nbGen;
		mostCompetent = bestIndividual;
	}
	

	/**
	 * Get number of generations
	 * @return number of generations
	 */
	public int getNbGenerations() {
		return nbGenerations;
	}
	
	/**
	 * Check if solution is found
	 * @return true if solution found
	 */
	public boolean solutionFound() {
		return solutionFound;
	}
	
	/**
	 * Get Most Competent Individual
	 * @return Most competent Individual
	 */
	public IIndividual getMostCompetent() {
		return mostCompetent;
	}
	
	/**
	 * Result to string
	 */
	@Override
	public String toString() {
		StringBuilder results = new StringBuilder();
		if(!solutionFound) {
			results.append("Solution not found\n");
		}else {
			results.append("Solution found\n");
			
		}
		results.append("Most competent individual : " + mostCompetent.toString() + "\n");
		results.append("Number of generations : " + nbGenerations + "\n");
		return results.toString();
	}
}
