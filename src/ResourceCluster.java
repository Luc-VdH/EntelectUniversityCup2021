public class ResourceCluster {
    int x = 0;
    int y = 0;
    int z = 0;
    String rName = "";
    int rAmount = 0;

    public ResourceCluster(int x, int y, int z, String nme, int r){
        this.x = x;
        this.y = y;
        this.z = z;
        rName = nme;
        rAmount = r;
    }

    public void takeResource(){
        rAmount = 0;
    }
}