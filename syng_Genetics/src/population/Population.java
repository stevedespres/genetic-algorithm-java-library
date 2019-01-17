package population;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import exceptions.GeneticAlgorithmException;

/**
 * Population
 * Initializing population, creating individuals from Individual Interface implemented by user
 * 
 * @authors Nathan DUBERNARD, Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, 
 */
public class Population {
	
	/* Parameters */ 
	private final static Logger LOGGER = Logger.getLogger(Population.class.getName());
	private final int size;
	private List<IIndividual> individuals;
	
	/**
	 * Constructor
	 * @param size
	 * @param percentage
	 * @throws GeneticAlgorithmException 
	 */
	public Population(final int size) throws GeneticAlgorithmException {
		if(size > 0) {
			this.size = size;
			this.individuals = new ArrayList<>();
		}else {
			throw new GeneticAlgorithmException("Population size need to be > 0");
		}
	}
	
	/**
	 * Creation of population
	 * @param builder
	 */
	public void init(final IIndividualCreator creator) {
		for(int i=0; i< size ; i++) {
			individuals.add(creator.CreateIndividual());
		}
	}
	
	/**
	 * Get an individual by index
	 * @param index
	 * @return Individual
	 */
    public IIndividual getIndividual(final int index) {
        return individuals.get(index);
    }
    
    /**
     * Get list of individuals
     * @return List of individuals
     */
    public List<IIndividual> getIndividuals(){
    	return individuals;
    }
    
    /**
     * @return Population size
     */
    public int getSize() {
        return size;
    }

	/**
	 * Get the more competent individual (by skill)
	 * @return Individual
	 */
    public IIndividual getMoreCompetent() {
    	if(size>0)
    	{
    		IIndividual moreCompetent = individuals.get(0);
            // Loop through individuals to find more competent
            for (int i = 0; i < size; i++) {
                if (moreCompetent.getSkill() <= individuals.get(i).getSkill()) {
                    moreCompetent = individuals.get(i);
                }
            }
            return moreCompetent;
    	}
    	else {
    		LOGGER.info("list not initialized yet !! ");
    		return null;
    	}
    }
    
    /**
     * Save an individual in the list of individuals
     * @param index
     * @param indiv
     */
    public void saveIndividual(final int index,final IIndividual indiv) {
        individuals.add(index, indiv);
    }
}
