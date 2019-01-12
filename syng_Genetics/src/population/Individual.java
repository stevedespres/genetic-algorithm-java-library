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
	public abstract Object getGene(final int index);
	
	/* Get individual genes length */
	public abstract int getGeneLength();
	
	/* Set a value for a specific gene */
	public abstract void setGene(final int index, final Object value);
	
	/* Get the individual skill */
	public abstract int getSkill();
	
	/* Set an individual skill used to evaluate individual */
	public abstract void setSkill(final int skill);
		
}
