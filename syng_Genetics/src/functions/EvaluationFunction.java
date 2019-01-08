package functions;

import java.util.function.Function;

public class EvaluationFunction<T, R> {
	Function<T, R> function;
	/**
	 * Constructeur 
	 * @param function
	 */
	public EvaluationFunction(Function<T, R> function) {
		this.function = function;
	}
	
	/**
	 * Execution de la fonction d'evaluation
	 * @param param
	 * @return
	 */
	public R execute(T param) {
		return function.apply(param);
	}
}
