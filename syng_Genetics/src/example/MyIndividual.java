package example;

import population.IIndividual;

/**
 * This class is a use case example for modelize an individual
 * This one should be implemented by the user
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class MyIndividual implements IIndividual{

	/* Parameters */
	static int geneLength;
	private byte[] genes;
    private int skill = 0;
    
    /**
     * Constructor
     * @param g genes
     */
    public MyIndividual(final byte[] g) {
    	genes = g;
    	geneLength = genes.length;
    }
    
	/**
	 * Get Genes
	 */
	public byte[] getGene() {
		return genes;
	}
	
	/**
	 * Get gene by index
	 */
    public Object getGene(final int index) {
        return genes[index];
    }
    
	/**
	 * Get Gene length
	 */
    public int getGeneLength() {
    	return geneLength;
    }
       
    /**
     * Set Gene at index
     */
    public void setGene(final int index,final Object value) {
        genes[index] = (byte) value;
    }
    
    /**
     * Get Skill
     */
    public int getSkill() {
    	return skill;
    }
    
    /**
     * Set Skill
     */
    public void setSkill(final int s) {
    	skill = s;
    }

    /**
     * Individual to String 
     */
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < geneLength; i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
