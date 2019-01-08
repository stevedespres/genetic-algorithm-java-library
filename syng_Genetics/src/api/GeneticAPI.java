package api;

import java.util.function.Function;

import Individual.IndividualBuilder;
import Individual.Population;
import functions.CrossOverFunction;
import functions.EvaluationFunction;
import functions.MutationFunction;
import results.Result;

public class GeneticAPI implements IGeneticApi {
	

	private IndividualBuilder individualBuilderFunction;
	private EvaluationFunction evaluationFunction;
	private MutationFunction mutationFunction;
	private CrossOverFunction crossoverFunction;
	
	private Population population;
	
	@Override
	public void setIndividualsBuilder(IndividualBuilder function) {
		this.individualBuilderFunction=function;
		
	}

	@Override
	public void setEvaluationFunction(EvaluationFunction function) {
		this.evaluationFunction=function;
		
	}

	@Override
	public void setMutationFunction(MutationFunction function) {
		this.mutationFunction=function;
		
	}

	@Override
	public void setCrossOverFunction(CrossOverFunction function) {
		this.crossoverFunction=function;
		
	}

	@Override
	public void setPopulation(int size, float percentageOfChildsGenerated) {
		
		
	}

	@Override
	public void setModes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
