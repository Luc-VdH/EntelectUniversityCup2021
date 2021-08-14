public class ResourceCluster implements Comparable<ResourceCluster> {
    public int rID = 0;
    public int x = 0;
    public int y = 0;
    public int z = 0;
    public String rName = "";
    public int rAmount = 0;
    public int points = 0;

    public int PC = 0;
    public int PP = 0;
    public int PT = 0;
    public double BM = 0;

    

    public ResourceCluster(int id, int x, int y, int z, String nme, int r){
        rID = id;
        switch (id) {
            case 1:
                PC = 1;
                PP = 2;
                PT = 2;
                BM = 1.5;
                break;
            case 2:
                PC = 1;
                PP = 2;
                PT = 2;
                BM = 2;
                break;
            case 3:
                PC = 1;
                PP = 2;
                PT = 3;
                BM = 2.5;
                break;
            case 4:
                PC = 2;
                PP = 4;
                PT = 3;
                BM = 4;
                break;
            case 5:
                PC = 2;
                PP = 4;
                PT = 3;
                BM = 3;
                break;
            case 6:
                PC = 2;
                PP = 4;
                PT = 4;
                BM = 4;
                break;
            case 7:
                PC = 3;
                PP = 6;
                PT = 4;
                BM = 4.5;
                break;
            case 8:
                PC = 3;
                PP = 6;
                PT = 5;
                BM = 4.5;
                break;
            case 9:
                PC = 4;
                PP = 8;
                PT = 6;
                BM = 6;
                break;
            case 10:
                PC = 5;
                PP = 10;
                PT = 8;
                BM = 5;
                break;
            default:
                System.out.println("WTF switch not working ID is wrong");
                break;

        }
        this.x = x;
        this.y = y;
        this.z = z;
        rName = nme;
        rAmount = r;
    }

    public void takeResource() {
        rAmount = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(ResourceCluster o) {
        return Integer.compare(points, o.points);
    }
}