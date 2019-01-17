package functions;

import java.util.function.Function;

/**
 * Mutation Function
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 * @param <T>
 * @param <R>
 */
public class MutationFunction<T, R> {
	Function<T, R> function;
	/**
	 * Constructor 
	 * @param function
	 */
	public MutationFunction(Function<T, R> function) {
		this.function = function;
	}
	
	/**
	 * Run Mutation Functions
	 * @param param
	 * @return
	 */
	public R execute(T param) {
		return function.apply(param);
	}
}
