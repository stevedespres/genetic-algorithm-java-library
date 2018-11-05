
public class Main {

	public static Integer function(Double d) {
		return d.intValue();
	}
	
	public static void main(String[] args) {
		
		Wrapper<Double,Integer> w1 = new Wrapper<>(
				
				d -> {		
					return d.intValue();
	
		});
		System.out.println(w1.execute(3.14));
		
		
		
		Wrapper<Double,Integer> w2 = new Wrapper<>(Main::function);
		System.out.println(w2.execute(3.14));

	}

}
