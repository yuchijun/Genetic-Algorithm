package genetictspp;
/**
 * 数据类
 */

public class TSPData{
    static int CITY_NUM;
    static final int SPECIES_NUM = 100;
    static final int DEVELOP_NUM =1000;
    static final float pcl = 0.60f , pch = 0.95f;
    static final float pm = 0.4f;
    static final float[][] disMap;

    static{
        int[][] CityPos = {
            {0,0},{12,32},{5,25},{8,45},{33,17},
            {25,7},{15,15},{15,25},{25,15},{41,12}
        };

        CITY_NUM = CityPos.length;
        disMap = new float[CITY_NUM][CITY_NUM];

        for(int i=0; i<CITY_NUM; ++i){
            for(int j=i; j<CITY_NUM; ++j){
                float dis = (float)Math.sqrt(Math.pow((CityPos[i][0]-CityPos[j][0]), 2) + Math.pow((CityPos[i][1]-CityPos[j][1]),2));
                disMap[i][j] = dis;
                disMap[j][i] = disMap[i][j];

                
            }
        }

    }

    


}