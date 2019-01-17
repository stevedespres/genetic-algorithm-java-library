package example;

import population.IIndividual;
import population.IIndividualCreator;

/**
 * Concrete Individual Creator
 * Example of implementation of Individual Creator Interface
 * Must be defined by the user
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class MyIndividualCreator implements IIndividualCreator {

	static final int geneLength = 64;
	
	/**
	 * Create an Individual
	 */
	@Override
	public IIndividual CreateIndividual() {
		byte[] genes = new byte[geneLength];
		for (int i = 0; i < geneLength; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
		return new MyIndividual(genes);
	}
}
