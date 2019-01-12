package modes;

/**
 * Individual replacement modes
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 * Default : replace individual at his index
 * Best : if a new individual is best evaluated than the member of population the least evaluated, it replaces it.
 * Random :  the news individuals replace randomly the members of the population
 */
public enum IndividualReplacementMode {
	Default,
	Best, 
	Random;
}
