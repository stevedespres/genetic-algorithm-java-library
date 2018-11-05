import java.util.function.Function;

public class Wrapper<T,R> {

	Function<T, R> function;
	public Wrapper(Function<T, R> function) {
		this.function = function;
	}
	
	public R execute(T param) {
		return function.apply(param);
	}
	
}
