package modes;

/** 
 * Differents Modes to stop genetic algorithm
 * 
 * @author Youssef ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 * 
 * Time : time limit in seconds
 * Iteration : stop after n iterations
 * PopulationEvolution : stop if population not evolute after n iterations
 * IndividualEvolution : stop if individual not evolut after n iterations
 * No : no stop limit
 * 
 */
public enum StopLimitMode { 
	Time, 
	Iteration, 
	PopulationEvolution, 
	IndividualEvolution,
	No;
}

