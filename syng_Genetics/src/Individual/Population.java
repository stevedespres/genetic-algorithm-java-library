package Individual;

import java.util.ArrayList;
import java.util.List;

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
	 * Création de la population d'individus
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
    
    public int size() {
        return size;
    }

    public Individual getMoreCompetent() {
    	Individual moreCompetent = individuals.get(0);
    	for(int i=1; i<individuals.size(); i++) {
    		if(individuals.get(i).getSkill() > moreCompetent.getSkill()) {
    			moreCompetent = individuals.get(i);
    		}
    	}
    	return moreCompetent;
    }
    
    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals.add(index, indiv);
    }

}
