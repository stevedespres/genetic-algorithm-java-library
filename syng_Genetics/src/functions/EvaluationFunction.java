package functions;

import java.util.function.Function;

/**
 * 
 * @author Youssef ZIYYAT, Nathan DUBERNARD, Steve DESPRES, Guillaume COURTIN 
 *
 * @param <T>
 * @param <R>
 */
public class EvaluationFunction<T, R> {
	Function<T, R> function;
	/**
	 * Constructor 
	 * @param function
	 */
	public EvaluationFunction(Function<T, R> function) {
		this.function = function;
	}
	
	/**
	 * Execution of evaluation function
	 * @param param
	 * @return
	 */
	public R execute(T param) {
		return function.apply(param);
	}
}
