import java.util.*;
import java.io.*;
import java.lang.Math;

public class RunResourceCollect {
    public static Ship [] ships;
    public static void main(String[] args) {
        try{
            Scanner scFile = new Scanner(new File("src/galaxy2.txt"));
            //LINE ONE
            String [] lineOne = scFile.nextLine().split("\\|");
            int UR = Integer.parseInt(lineOne[0]);
            int numShips = Integer.parseInt(lineOne[1]);
            int shipCap = Integer.parseInt(lineOne[2]);
            int numLab = Integer.parseInt(lineOne[3]);
            int outpostThreshold = Integer.parseInt(lineOne[4]); //T - Outpost Processed Material Threshold (0 < T <= 100000)
            int NQ = Integer.parseInt(lineOne[5]); //NQ - Number of Quotas (0 < NQ <= 10)

            //LINE TWO
            String [] lineTwo = scFile.nextLine().split("\\|");
            int len = lineTwo.length;

            String [] resourceIDs = new String[len];
            String [] numberOfClusters = new String[len];
            String [] quota = new String[NQ];
            
            for(int i = 0; i < len; i++){
                resourceIDs[i] = lineTwo[i].split(",")[0];
                numberOfClusters[i] = lineTwo[i].split(",")[1];
            }

            for(int i = 0; i < NQ; i++){
                quota[i] = scFile.nextLine().split("\\|")[1]; //NOTE THAT THESE ARE PERCENTAGES
            }
            Resource [] res = new Resource[NQ];
            for(int i = 0; i < NQ; i++){
                res[i] = new Resource(Integer.parseInt(resourceIDs[i]), Integer.parseInt(numberOfClusters[i]), Integer.parseInt(quota[i]));
            }

            ships = new Ship[numShips];
            for (int i = 0; i < numShips; i++) {
                ships[i] = new Ship(shipCap);
            }

            int numClustsTot = 0;
            for (int i = 0; i < numberOfClusters.length; i++) {
                numClustsTot += Integer.parseInt(numberOfClusters[i]);
            }
            ResourceCluster [] clusters = new ResourceCluster[numClustsTot];
            int clustCount = 0;
            
            for(int i = 0; i < UR; i++){
                String resourceLine = scFile.nextLine();
                String [] r = resourceLine.split("\\|");
                int id = Integer.parseInt(r[0]);
                for (int j = 1; j < r.length; j++) {
                    String [] rArr = r[j].split(",");
                    clusters[clustCount] = new ResourceCluster(id, Integer.parseInt(rArr[1]), Integer.parseInt(rArr[2]), Integer.parseInt(rArr[3]), rArr[0], Integer.parseInt(rArr[4]));
                    clustCount++;
                }
            }

            int currentResourceVol = 0;
            resPoints(clusters);
            int [] quotaInts = calcQuotas(outpostThreshold, quota);

            Arrays.sort(clusters);
            int shipIndex = 0;

            for (int i = 0; i < clusters.length; i++) {
                if(currentResourceVol >= outpostThreshold){
                    break;
                }
                ResourceCluster current = clusters[i];
                if(quotaInts[current.rID-1] > 0){
                    
                    for(int k = 0; k < ships.length; k++){
                        int dist = distRel(ships[k].x, ships[k].y, ships[k].z, current.x,  current.y,  current.z);
                        ships[k].setDistance(dist);
                    }
                    Arrays.sort(ships);
                    for (int j = 0; j < ships.length; j++) {

                        if(ships[j].currentInHold > ships[j].capacity){
                            ships[j].setPosition(0, 0, 0);
                            ships[j].addToPath("0");
                            ships[j].clearResources();
                            ships[j].currentInHold = 0;
                            resPoints(clusters);
                            Arrays.sort(clusters);
                        }else{
                            ships[j].setPosition(current.x, current.y, current.z);
                            ships[j].addToPath(current.rName);
                            ships[j].addResource(current);
                            ships[j].currentInHold += current.rAmount;
                            currentResourceVol += current.rAmount;
                            quotaInts[current.rID-1] -= current.rAmount;
                            resPoints(clusters);
                            Arrays.sort(clusters);
                            break;
                        }
                        
                    }
                }
                
            }

            writeFile(ships);
        }catch (Exception e){
            System.out.println("file not found \n");
            e.printStackTrace();
        }
    }

    public static int dist(int x, int y, int z){
        return (int)Math.round(Math.sqrt(x*x + y*y + z*z));
    }

    public static int distRel(int x, int y, int z, int x1, int y1, int z1){
        return (int)Math.round(Math.sqrt(Math.pow(x1-x,2)+ Math.pow(y1-y,2) + Math.pow(z1-z,2)));
    }

    public static void resPoints(ResourceCluster [] c){

        for(int i = 0; i < c.length; i++){
            ResourceCluster ci = c[i];
            Ship close = ships[0];
            int max = 10000;
            int maxI = 0;
            int dist = 0;
            for(int j = 0; j < ships.length; j++){
                // System.out.println(ships[j].x + "\t" +ships[j].y+ "\t" + ships[j].z);
                dist = distRel(ships[j].x, ships[j].y, ships[j].z, ci.x,  ci.y,  ci.z);
                if(max > dist){
                    max = dist;
                    maxI = j;
                }
            }
            int pts = (int)((ci.PP + ci.PC - ci.PT*0.1)*ci.BM - dist*0.1);
            c[i].setPoints(pts);
        }
    }

    public static int[] calcQuotas(int outpostThreshold, String [] quotas){
        int [] qs = new int[10];
        for(int i = 0; i < 10; i++){
            qs[i] = 0;
        }
        for(int i = 0; i < quotas.length; i++){
            qs[i] = (int)(outpostThreshold*(Double.parseDouble(quotas[i])/100));
            System.out.println(quotas[i]);

        }
        return qs;
    }
    public static void writeFile(Ship [] ships) throws IOException{
        FileWriter w = new FileWriter("output.txt");
        for(int i = 0; i < ships.length; i++){
            w.write(ships[i].toString() + "\n");
        }
        w.close();
    }
}