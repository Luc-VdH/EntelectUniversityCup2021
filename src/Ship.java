import java.util.*;

public class Ship implements Comparable<Ship> {
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public ArrayList<String> path = new ArrayList<String>();
    public ResourceCluster [] resources = new ResourceCluster[1000000];
    public int rCount = 0;
    public int capacity = 0;
    public int currentInHold = 0;
    public int distance = 0;
    public Ship(int cap){
        capacity = cap;
    }
    public void setPosition(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        
    }
    public void addResource(ResourceCluster r){
        currentInHold += r.rAmount;
        resources[rCount] = r;
        rCount++;
    }
    public void clearResources(){
        resources = new ResourceCluster[1000000];
        rCount = 0;
    }
    public void addToPath(String s){
        path.add(s);
    }
    public String toString(){
        if(path.size() != 0){
            if(!path.get(path.size()-1).equals("0")){
                return String.join(",", path) + ",0";
            }
            return String.join(",", path);
        }
        return "";
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    @Override
    public int compareTo(Ship o) {
        return Integer.compare(distance, o.distance);
    }
    
}