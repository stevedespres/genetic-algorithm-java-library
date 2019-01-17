package stopCriteria;

import population.Population;

/**
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class EvolutionCriteria implements IStopCriteria {

	private Population population;
	private int maxIteration;
	private int currentIteration;

	@Override
	public boolean stopAlgorithm(Population pop) {

		if (this.population == null)
			this.population = pop;
		if (this.population.equals(pop)) {
			this.currentIteration++;
			if (this.currentIteration >= this.maxIteration)
				return true;
		} else {
			this.population = null;
			// reset iteration
			this.currentIteration = 0;
		}
		return false;
	}

}
