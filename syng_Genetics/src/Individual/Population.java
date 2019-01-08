package Individual;

import java.util.ArrayList;
import java.util.List;

public class Population {
	
	
	private final int size;
	private final float percentage;
	
	private List<IndividualBuilder> list;
	
	public Population(int size, float percentage) {
		this.size = size;
		this.percentage = percentage;
		
		this.list = new ArrayList<>();
	}
	
	public <T,R> void init(IndividualBuilder<T, R> builder) {
		for(int i=0; i< size ; i++) {
		//	builder.create(T);
		//	list.add();
		}
	}

}
