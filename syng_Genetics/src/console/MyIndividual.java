package console;

import Individual.Individual;

/**
 * This class is a test example for the algorithm.
 * This one should be given by the user
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class MyIndividual extends Individual{

	static final int geneLength = 64; // Taille du gene
	private final byte[] genes = new byte[geneLength]; // Creation des genes sous forme de tableau d'octets
	
    private int skill = 0;
    
	/**
	 * Fonction de création d'individu
	 */
	public Individual create() {
	    for (int i = 0; i < geneLength; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
	    return this;
	}
	
	/**
	 * Récuperation des genes de l'individu
	 */
	public byte[] getGene() {
		return genes;
	}
	
    public byte getGene(int index) {
        return genes[index];
    }
    
    /**
     * POURQUOI ON A getGeneLength ET LA FONCTION size()? LES DEUX RENVOIENT LE MËME RESULTAT ! 
     */
    public int getGeneLength() {
    	return geneLength;
    }
    /* Public methods */
    public int size() {
        return genes.length;
    }
    
    public void setGene(int index, Object value) {
        genes[index] = (byte) value;
        skill = 0;
    }

    public void setSkill(int s) {
    	skill = s;
    }
    
    public int getSkill() {
    	return skill;
    }



}
