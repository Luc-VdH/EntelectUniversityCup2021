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

            String resourceID = lineTwo[0];


            for(int i = 0; i < NQ; i++){

            }

            for(int i = 0; i < UR; i++){

            }
            while(scFile.hasNext()){
                String [] line = scFile.nextLine("|");


            }
        }catch (Exception e){
            System.out.println("file not found");
        }
    }

    public int dist(int x, int y, int z){
        return (int)Math.round(Math.sqrt(x*x + y*y + z*z));
    }
}