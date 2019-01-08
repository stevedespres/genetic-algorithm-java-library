package Individual;

import java.util.function.Function;

public class IndividualBuilder<T, R> {

	Function<T, R> function;
	
	/**
	 * Constructeur 
	 * @param function
	 */
	public IndividualBuilder(Function<T, R> function) {
		this.function = function;
	}
	
	/**
	 * Creation d'un individu
	 * @param param
	 * @return
	 */
	public R create(T param) {
		return function.apply(param);
	}
}
