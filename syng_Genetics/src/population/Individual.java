package population;

/**
 * Individual Interface 
 * 
 * Must be implemented by the user to define the individual model
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public abstract class Individual {
	
	/* Get individual genes */
	public abstract Object getGene();
	
	/* Get a specific gene */ 
	public abstract Object getGene(int index);
	
	/* Get individual genes length */
	public abstract int getGeneLength();
	
	/* Set a value for a specific gene */
	public abstract void setGene(int index, Object value);
	
	/* Get the individual skill */
	public abstract int getSkill();
	
	/* Set an individual skill used to evaluate individual */
	public abstract void setSkill(int skill);
		
}
