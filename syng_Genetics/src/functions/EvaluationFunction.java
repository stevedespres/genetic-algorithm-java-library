package functions;

import java.util.function.Function;

/**
 * Evaluation Function
 * 
 * @authors Ahmed Youssouf ZIYYAT, Nathan DUBERNARD, Steve DESPRES, Guillaume COURTIN 
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
	 * Run Evaluation Function
	 * @param param
	 * @return
	 */
	public R execute(T param) {
		return function.apply(param);
	}
}
