# Algorithm Genetic

A genetic algorithm is a search heuristic that is inspired by Charles Darwin’s theory of natural evolution. This algorithm reflects the process of natural selection where the fittest individuals are selected for reproduction in order to produce offspring of the next generation.
More info : https://en.wikipedia.org/wiki/Genetic_algorithm

This Java library allows to execute a genetic algorithm with custom functions and personnalized individual model. 

# Developers
Students of ISTIA, Engineering school of the University of Angers, France. 
EI5 IHMRV
  - Guillaume Courtin
  - Youssef Ziyyat
  - Nathan Dubernard
  - Steve Despres

# Features

  - Custom Individual model
  - Custom Evaluation, CrossOver and Mutation functions
  - Manage population size
  - Manage parents selection mode : BESTS, RANDOM
  - Manage individual replacement mode : DEFAULT, BEST, RANDOM 
  - Manage stop limit mode : NO, TIME, ITERATION, POPULATION_EVOLUTION, INDIVIDUAL_EVOLUATION
  - Multithreading

# Architecture 
![alt text](https://lh5.googleusercontent.com/Q9hfSgS176Oke6B3dz3Tf9nhhgOTVEhAF1xQL-PwOgtiJcQx67imZgwrpAXv-hAt3aZejjehuTMfzdPSL8tx=w1920-h910)
# Installation
View an example of implementation in src/example/. 
To run this genetic algorithm, you have to set this parameters : 
  - Implement IndividualCreator : specify how to build an individual
  - Implement Individual : define the model of an individual

  - Set IndividualCreator : set your Individual creator
  - Set your Evaluation, Mutation and CrossOver functions : with lambda expressions
  - Set population size
  - (optionnal) Set parents selection mode
  - (optionnal) Set individual replacement mode
  - (optionnal) Set stop limit mode
  - (optionnal) Set stop limit parameter (number of iterations or time)

After setting up all of these parameters, you have to init the genetic algorithm then run it.
When the algorithm is finished, you can get the results. 