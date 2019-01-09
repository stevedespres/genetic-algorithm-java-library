package Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Population class
 * Initializing population, creating individuals from user creating funtion
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class Population {
	
	private final static Logger logger = Logger.getLogger(Population.class.getName());
	private final int size;
	private List<Individual> individuals;
	
	/**
	 * Constructor
	 * @param size
	 * @param percentage
	 */
	public Population(int size) {
		this.size = size;
		logger.info("creation of a new population of "+this.size+" individual");
		this.individuals = new ArrayList<>();
	}
	
	/**
	 * Création de la population d'individus
	 * @param builder
	 */
	public void init(Individual builder) {
		logger.info("Individual init");
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
            if (moreCompetent.getSkill() <= getIndividual(i).getSkill()) {
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
