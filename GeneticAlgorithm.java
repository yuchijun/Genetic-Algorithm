package genetictspp;

/**
 * 算法类
 */

import java.util.Random;

import genetictspp.SpeciesIndividual;
import genetictspp.SpeciesPopulation;
import genetictspp.TSPData;

public class GeneticAlgorithm{
    SpeciesIndividual run(SpeciesPopulation list){

        createPopulation(list);

        for(int i=0; i<TSPData.DEVELOP_NUM; ++i){
            
            select(list);
            crossover(list);
            mutate(list);

        }

       
        
        return getBest(list);
    }

    void createPopulation(SpeciesPopulation list){
        //种群中个体的数量
        int randomNum = (int)(TSPData.SPECIES_NUM);

        for(int i=0; i<randomNum; ++i){
            SpeciesIndividual species = new SpeciesIndividual();
            species.createBeginingGenes();
            list.add(species);
        }


    }

    //被选中的概率的计算
    void calRate(SpeciesPopulation list){
        SpeciesIndividual point = list.head.next;
        float totalFitness = 0.0f;
        list.speciesNum = 0;

        while(point != null){
            point.calFitness();
            list.speciesNum++;
            totalFitness += point.fitness;
            point = point.next;

        }

        point = list.head.next;

        while(point != null){
            point.rate = point.fitness/totalFitness;
            point = point.next;
        }

    }

    //进行轮盘赌
    void select(SpeciesPopulation list){
        calRate(list);
        SpeciesIndividual talentIndividual = null;
        SpeciesIndividual point = list.head.next;
        float talentDis = Float.MAX_VALUE;
        while(point != null){
            if(talentDis > point.distance){
                talentIndividual = point;
                talentDis = point.distance;
            }
            point = point.next;
        }

        SpeciesPopulation newpopulation = new SpeciesPopulation();

        int talentNum = (int)list.speciesNum/4 ;
        for(int i=0; i<talentNum; ++i){
            SpeciesIndividual newSpecies = talentIndividual.clone();
            newpopulation.add(newSpecies);
        }

        

        for(int i=0; i<list.speciesNum - talentNum; ++i){
            SpeciesIndividual oldPoint = list.head.next;
            float rand = (float)Math.random();
            while(oldPoint != null && oldPoint != talentIndividual){
                if(rand<=oldPoint.rate){
                    SpeciesIndividual newSpecies = oldPoint.clone();
                    newpopulation.add(newSpecies);
                    break;
                }else{
                    rand = rand - oldPoint.rate;
                }
                oldPoint = oldPoint.next;
            }

            if(oldPoint == null || oldPoint == talentIndividual)
				{
					//复制最后一个
					point=list.head;
					while(point.next != null)
						point=point.next;
					SpeciesIndividual newSpecies=point.clone();
					newpopulation.add(newSpecies);
				}


        }


        list.head = newpopulation.head;
    }

    int num(SpeciesPopulation list){
        return list.speciesNum;
    }

    void crossover(SpeciesPopulation list){

        float random = (float)Math.random();
        if(random>TSPData.pcl && random<TSPData.pch){
            Random rand = new Random();
            int find = rand.nextInt(list.speciesNum);
            SpeciesIndividual point = list.head.next;

            while(point != null && find != 0){
                point = point.next;
                find--;
            }
            if(point.next != null){
                int begin = rand.nextInt(TSPData.CITY_NUM);
                for(int i=begin; i<TSPData.CITY_NUM; ++i){
                    int fir,sec;
                    for(fir=0; !point.next.genes[fir].equals(point.genes[i]); ++fir);
                    for(sec=0; !point.genes[sec].equals(point.next.genes[i]);++sec);

                    String temp;
                    temp = point.genes[i];
                    point.genes[i] = point.next.genes[i];
                    point.next.genes[i] = temp;

                    point.genes[sec] = point.next.genes[i];
                    point.next.genes[fir] = point.genes[i];
                }

            }

        }
    }

    void mutate(SpeciesPopulation list){
        SpeciesIndividual point = list.head.next;
        while(point != null){
            float rate = (float)Math.random();
            if(rate<TSPData.pm){
                Random rand = new Random();
                int left = rand.nextInt(TSPData.CITY_NUM);
                int right = rand.nextInt(TSPData.CITY_NUM);
                if(left > right){
                    int temp;
                    temp = left;
                    left = right;
                    right = temp;
                }

                while(left <right){
                    String temp;
                    temp = point.genes[left];
                    point.genes[left] = point.genes[right];
                    point.genes[right] = temp;

                    left++;
                    right--;
                }
            }
            point = point.next;
        }
    }

    SpeciesIndividual getBest(SpeciesPopulation list){
        SpeciesIndividual point = list.head.next;
        SpeciesIndividual bestone = null;
        float minDis = Float.MAX_VALUE;

        while(point != null){
            if(minDis > point.distance){
                minDis = point.distance;
                bestone = point;
            }
            point = point.next;
        }

        return bestone;

    }


}