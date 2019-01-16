package population;

/**
 * Individual Interface 
 * 
 * Must be implemented by the user to define the individual model
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public interface Individual {
	
	/* Get individual genes */
	public Object getGene();
	
	/* Get a specific gene */ 
	public Object getGene(final int index);
	
	/* Get individual genes length */
	public int getGeneLength();
	
	/* Set a value for a specific gene */
	public void setGene(final int index, final Object value);
	
	/* Get the individual skill */
	public int getSkill();
	
	/* Set an individual skill used to evaluate individual */
	public void setSkill(final int skill);
		
}
