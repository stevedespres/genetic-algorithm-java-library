package Individual;

import java.util.ArrayList;
import java.util.List;

import testQL.Individual;

public class Population {
	
	
	private final int size;
	private List<Individual> individuals;
	
	/**
	 * Constructeur
	 * @param size
	 * @param percentage
	 */
	public Population(int size) {
		this.size = size;
		this.individuals = new ArrayList<>();
	}
	
	/**
	 * Cr�ation de la population d'individus
	 * @param builder
	 */
	public void init(Individual builder) {
		for(int i=0; i< size ; i++) {
			individuals.add(builder.create());
		}
	}
	
	/** Getters **/
    public Individual getIndividual(int index) {
        return individuals.get(index);
    }
    
    public List<Individual> getIndividuals(){
    	return individuals;
    }
    
    /**
     * @return population size
     */
    public int getSize() {
        return size;
    }

    /*public Individual getMoreCompetent() {
    	Individual moreCompetent = individuals.get(0);
    	for(int i=1; i<individuals.size(); i++) {
    		if(individuals.get(i).getSkill() > moreCompetent.getSkill()) {
    			moreCompetent = individuals.get(i);
    		}
    	}
    	return moreCompetent;
    }*/
    public Individual getMoreCompetent() {
        Individual moreCompetent = individuals.get(0);
        // Loop through individuals to find more competent
        for (int i = 0; i < size; i++) {
            if (moreCompetent.getCompetence() <= getIndividual(i).getCompetence()) {
                moreCompetent = getIndividual(i);
            }
        }
        return moreCompetent;
    }
    
    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals.add(index, indiv);
    }

}
