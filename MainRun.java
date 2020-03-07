package genetictspp;
/**
 * 主程序类
 */

import genetictspp.GeneticAlgorithm;
import genetictspp.SpeciesIndividual;
import genetictspp.SpeciesPopulation;

public class MainRun{
    public static void main(String[] args) {
        GeneticAlgorithm GA = new GeneticAlgorithm();
        SpeciesPopulation speciesPopulation = new SpeciesPopulation();
        SpeciesIndividual bestone =  GA.run(speciesPopulation);
        bestone.printRoute();
        
    } 
    


}