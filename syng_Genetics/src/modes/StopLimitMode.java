package modes;

/** 
 * Differents Modes to stop genetic algorithm
 * 
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 * 
 * Time : time limit in seconds
 * Iteration : stop after n iterations
 * PopulationEvolution : stop if population not evolute after n iterations
 * IndividualEvolution : stop if individual not evolut after n iterations
 * No : no stop limit
 * 
 */
public enum StopLimitMode { 
	TIME, 
	ITERATION, 
	POPULATION_EVOLUTION, 
	INDIVIDUAL_EVOLUTION,
	NO;
}

