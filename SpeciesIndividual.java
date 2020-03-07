package genetictspp;

/**
 * 个体类
 */

import java.util.Random;

import genetictspp.TSPData;


public class SpeciesIndividual{
    String[] genes;
    float fitness;
    float distance;
    SpeciesIndividual next;
    float rate;

    SpeciesIndividual(){
        this.genes = new String[TSPData.CITY_NUM];
        this.distance = 0.0f;
        this.fitness = 0.0f;
        this.next = null;
        rate = 0.0f;
    }
        
    void createBeginingGenes(){
        for(int i=0; i<genes.length; ++i){
            genes[i] = Integer.toString(i+1);
        }

        //生成随机的城市序列
        Random rand = new Random();
        for(int j=0; j<genes.length; ++j){
            int num = j + rand.nextInt(genes.length - j);

            String temp;
            temp = genes[j];
            genes[j] = genes[num];
            genes[num] = temp;
        }

    }

    //适应度为路径长度的倒数
    void calFitness()
	{
		float totalDis=0.0f;
		//计算总路径
		for(int i = 0;i < TSPData.CITY_NUM;i++)
		{
			int curCity=Integer.parseInt(this.genes[i])-1; //parseInt解析一个字符串，返回一个整数
			int nextCity=Integer.parseInt(this.genes[(i+1) % TSPData.CITY_NUM])-1;

			totalDis += TSPData.disMap[curCity][nextCity];
		}
		
		this.distance=totalDis;
		this.fitness=1.0f/totalDis; //路径越短，倒数越大，适应度越大
	}
        

    public SpeciesIndividual clone(){
        SpeciesIndividual species = new SpeciesIndividual();
        for(int i=0; i<this.genes.length; ++i){
            species.genes[i] = this.genes[i];
        }
        species.distance = this.distance;
        species.fitness = this.fitness;

        return species;

    }

    void printRoute(){
        System.out.print("最短路");
        for(int i = 0; i<this.genes.length; ++i){
            System.out.print(genes[i] + "->");
        }
        System.out.print(genes[0] + "\n");
        System.out.print("最短路长" + this.distance);
    }
}