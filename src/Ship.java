import java.util.*;

public class Ship {
    int x = 0;
    int y = 0;
    int z = 0;
    ArrayList<String> path = new ArrayList<String>();
    ResourceCluster [] resources = new ResourceCluster[100];
    int rCount = 0;
    int capacity = 0;
    int currentInHold = 0;
    public Ship(int cap){
        capacity = cap;
    }
    public void setPosition(int x, int y, int z, int capacity){
        this.x = x;
        this.y = y;
        this.z = z;
        this.capacity = capacity;
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
}