package exceptions;

/**
 * Genetic Algorithm Exception
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 */
public class GeneticAlgorithmException extends Exception {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public GeneticAlgorithmException(final String message) {
		super(message);
	}
	
	public GeneticAlgorithmException(final Exception ex) {
		super(ex);
	}
	
	public GeneticAlgorithmException(final String message, final Exception ex) {
		super(message, ex);
	}
}
