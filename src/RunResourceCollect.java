import java.util.*;
import java.io.*;
import java.lang.Math;

public class RunResourceCollect {
    public static void main(String[] args) {
        try{
            Scanner scFile = new Scanner(new File("src/galaxy1.txt"));
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

            Ship [] ships = new Ship[numShips];
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
                    
                    for (int j = 0; j < ships.length; j++) {
                        if(ships[j].currentInHold > ships[j].capacity){
                            ships[j].setPosition(0, 0, 0);
                            ships[j].addToPath("0");
                            ships[j].clearResources();
                            ships[j].currentInHold = 0;
                        }else{
                            ships[j].setPosition(current.x, current.y, current.z);
                            ships[j].addToPath(current.rName);
                            ships[j].addResource(current);
                            ships[j].currentInHold += current.rAmount;
                            currentResourceVol += current.rAmount;
                            quotaInts[current.rID-1] -= current.rAmount;
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

    public static void resPoints(ResourceCluster [] c){

        for(int i = 0; i < c.length; i++){
            ResourceCluster ci = c[i];
            int dist = (int)(dist(c[i].x, c[i].y, c[i].z)*0.1);
            int pts = (int)((ci.PP + ci.PC - ci.PT*0.1)*ci.BM - dist);
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