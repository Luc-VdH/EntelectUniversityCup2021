public class ResourceCluster {
    public int rID = 0;
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public String rName = "";
    public int rAmount = 0;
    public int points = 0;
    public ResourceCluster(int id, int x, int y, int z, String nme, int r){
        rID = id;
        this.x = x;
        this.y = y;
        this.z = z;
        rName = nme;
        rAmount = r;
    }

    public void takeResource(){
        rAmount = 0;
    }

    public void setPoints(int points){
        this.points = points;
    }
}