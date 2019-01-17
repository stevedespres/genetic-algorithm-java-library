package stopCriteria;

import population.Population;
/**
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public interface IStopCriteria {
	
	boolean stopAlgorithm(Population pop);

}
