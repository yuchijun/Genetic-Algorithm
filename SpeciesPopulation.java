package genetictspp;

/**
 * 种群类
 */

import genetictspp.SpeciesIndividual;
import genetictspp.TSPData;

public class SpeciesPopulation{
    SpeciesIndividual head;
    int speciesNum;

    SpeciesPopulation(){
        head = new SpeciesIndividual();
        speciesNum = TSPData.SPECIES_NUM;

    }

    void add(SpeciesIndividual species){
        SpeciesIndividual point = head;

        while(point.next != null){
            point = point.next;
        }
        point.next = species;
        

    }

    void traverse()
	{
		SpeciesIndividual point=head.next;//游标
		while(point != null)//寻找表尾结点
		{
			for(int i=0;i<TSPData.CITY_NUM;i++)
				System.out.print(point.genes[i]+" ");
            System.out.println(point.distance);
            System.out.println(point.fitness);
			point=point.next;
		}
		System.out.println("_______________________");
	}
}