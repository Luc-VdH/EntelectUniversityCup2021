import java.util.*;
import java.io.*;
import java.lang.Math;

public class RunResourceCollect {
    public static void main(String[] args) {
        try{
            Scanner scFile = new Scanner(new File(""));
            //LINE ONE
            String [] lineOne = scFile.nextLine().split(".");
            int UR = Integer.parseInt(lineOne[0]);
            int numShips = Integer.parseInt(lineOne[1]);
            int shipCap = Integer.parseInt(lineOne[2]);
            int numLab = Integer.parseInt(lineOne[3]);
            int outpostThreshold = Integer.parseInt(lineOne[4]); //T - Outpost Processed Material Threshold (0 < T <= 100000)
            int NQ = Integer.parseInt(lineOne[5]); //NQ - Number of Quotas (0 < NQ <= 10)

            //LINE TWO
            String [] lineTwo = scFile.nextLine().split("|");
            int len = lineTwo.length;

            String [] resourceIDs = new String[len];
            String [] numberOfClusters = new String[len];
            String [] quota = new String[NQ];
            
            for(int i = 0; i < len; i++){
                resourceIDs[i] = lineTwo[i].split(",")[0];
                numberOfClusters[i] = lineTwo[i].split(",")[1];
            }

            for(int i = 0; i < NQ; i++){
                quota[i] = scFile.nextLine().split("|")[1]; //NOTE THAT THESE ARE PERCENTAGES
            }
            Resource [] res = new Resource[NQ];
            for(int i = 0; i < NQ; i++){
                res[i] = new Resource(Integer.parseInt(resourceIDs[i]), Integer.parseInt(numberOfClusters[i]), Integer.parseInt(quota[i]));
            }

            int numClustsTot = 0;
            for (int i = 0; i < numberOfClusters.length; i++) {
                numClustsTot += Integer.parseInt(numberOfClusters[i]);
            }
            ResourceCluster [] clusters = new ResourceCluster[numClustsTot];
            int clustCount = 0;
            
            for(int i = 0; i < UR; i++){
                String resourceLine = scFile.nextLine();
                String [] rArr = resourceLine.split("|");
                for (int j = 1; j < rArr.length; j++) {
                    clusters[clustCount] = new ResourceCluster(Integer.parseInt(rArr[1]), Integer.parseInt(rArr[2]), Integer.parseInt(rArr[3]), rArr[0], Integer.parseInt(rArr[4]));
                }
            }

            int currentResourceVol = 0;



        }catch (Exception e){
            System.out.println("file not found");
        }
    }

    public int dist(int x, int y, int z){
        return (int)Math.round(Math.sqrt(x*x + y*y + z*z));
    }

    public void resPoints(){
        
    }

    public int[] calcQuotas(int outpostThreshold, String [] quotas){
        int [] qs = new int[quotas.length];
        for(int i = 0; i < quotas.length; i++){
            qs[i] = outpostThreshold*(Integer.parseInt(quotas[i])/100);
        }
        return qs;
    }
}