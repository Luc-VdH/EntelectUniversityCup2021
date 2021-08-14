import java.util.*;

public class Ship {
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public ArrayList<String> path = new ArrayList<String>();
    public ResourceCluster [] resources = new ResourceCluster[100];
    public int rCount = 0;
    public int capacity = 0;
    public int currentInHold = 0;
    public Ship(int cap){
        capacity = cap;
    }
    public void setPosition(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        
    }
    public void addResource(ResourceCluster r, int amount){
        currentInHold += amount;
        resources[rCount] = r;
        rCount++;
    }
    public void clearResources(){
        resources = new ResourceCluster[100];
    }
    public void addToPath(String s){
        path.add(s);
    }
    public String toString(){
        if(path.get(path.size()-1).equals("0")){
            return String.join(",", path) + "0";
        }
        return String.join(",", path);
    }
}