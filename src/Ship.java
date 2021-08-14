public class Ship {
    int x = 0;
    int y = 0;
    int z = 0;
    Resource [] resources = new Resource[100];
    int rCount = 0;
    public Ship(){

    }
    public void setPosition(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void addResource(Resource r){
        resources[rCount] = r;
        rCount++;
    }
    public void clearResources(){
        resources = new Resource[100];
    }
}