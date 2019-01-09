package console;

import Individual.Individual;

public class MyIndividual implements Individual{

	static final int geneLength = 64; // Taille du gene
	private final byte[] genes = new byte[geneLength]; // Creation des genes sous forme de tableau d'octets
	
    private int skill = 0;
    
	/**
	 * Fonction de création d'individus 
	 */
	public Individual create() {
	    for (int i = 0; i < geneLength; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
	    return this;
	}
	
	/**
	 * Recuperation des genes de l'individu
	 */
	public byte[] getGene() {
		return genes;
	}
	
    public byte getGene(int index) {
        return genes[index];
    }
    
    public int getGeneLength() {
    	return geneLength;
    }
    
    public void setGene(int index, byte value) {
        genes[index] = value;
    }
    
    public Integer getSkill() {
        if (skill == 0) {
             // Loop through our individuals genes and compare them to our candidates
             for (int i = 0; i < geneLength && i < geneLength; i++) {
 
                 if (getGene(i) == solution[i]) {
                	 skill++;
                 }
             }
        }
        return skill;
    }
 
}
