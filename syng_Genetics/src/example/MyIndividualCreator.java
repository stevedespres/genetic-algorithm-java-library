package example;

import population.Individual;
import population.IndividualCreator;

/**
 * Concrete Individual Creator
 * Example of implementation of Individual Creator Interface
 * Must be defined by the user
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class MyIndividualCreator implements IndividualCreator {

	static final int geneLength = 64;
	
	/**
	 * Create an Individual
	 */
	@Override
	public Individual CreateIndividual() {
		byte[] genes = new byte[geneLength];
		for (int i = 0; i < geneLength; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
		return new MyIndividual(genes);
	}
}
