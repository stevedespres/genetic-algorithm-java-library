package functions;

import java.util.function.Function;

/**
 * Cross Over Function
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 *
 * @param <T>
 * @param <R>
 */
public class CrossOverFunction<T, R> {

	Function<T, R> function;
	/**
	 * Constructor
	 * @param function
	 */
	public CrossOverFunction(Function<T, R> function) {
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
