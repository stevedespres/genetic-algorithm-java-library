package Individual;

/**
 * User class will be based partly on this abstract class 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public abstract class Individual {

	/* Function to create an individual */
	public abstract Individual create();
	
	/* Function to get an individual gene */
	public abstract Object getGene();
	
	/* Function to get a specific gene */ 
	public abstract byte getGene(int index);
	
	/* Function to get individual genes length */
	public abstract int getGeneLength();
	
	/* Function to set a value for a specific gene */
	public abstract void setGene(int index, Object value);
	
	/* Function to get an individual skill */
	public abstract int getSkill();
	
	/* Function to set individual skill */
	public abstract void setSkill(int skill);
	
	/* */
	public abstract int size();
	

}
