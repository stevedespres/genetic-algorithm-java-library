package Individual;

public interface Skill {

	    /* Public methods */
	    // Set a candidate solution
	    public void setSolution(byte[] newSolution);
	 
	    // To make it easier we can use this method to set our candidate solution
	    // with string of 0s and 1s
	    public void setSolution(String newSolution);
	 
	    // Compute skill by comparing it to our candidate solution
	    public int getSkill(Individual individual);
	 
	    // Get optimum skill
	    public int getMaxSkill();
	
}
