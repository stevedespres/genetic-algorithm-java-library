package console;

import Individual.Individual;
import Individual.Skill;

/**
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class MySkill implements Skill {
	
	// TAILLE DU TABLEAU A REVOIR
    static byte[] solution = new byte[64];
    
    public MySkill(byte[] newSolution) {
    	this.solution=newSolution;
    }
	  /* Public methods */
    // Set a candidate solution as a byte array
    public void setSolution(byte[] newSolution) {
        solution = newSolution;
    }
 
     // To make it easier we can use this method to set our candidate solution
    // with string of 0s and 1s
    public void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            // ONLY 1 AND 0 ALLOWED
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }
 
    // Compute skill by comparing it to our candidate solution
    public int getSkill(Individual individual) {
        int skill = 0;
        // Loop through our individuals genes and compare them to our candidates
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                skill++;
            }
        }
        return skill;
    }
 
    // Get optimum skill
    public int getMaxSkill() {
        int maxSkill = solution.length;
        return maxSkill;
    }

}
