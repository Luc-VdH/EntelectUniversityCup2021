import Math;

public class RunResourceCollect {
    public static void main(String[] args) {
        try{
            Scanner scFile = new Scanner(new File(""));
        }catch (Exception e){
            System.out.println("file not found");
        }
    }

    public int dist(int x, int y, int z){
        return Math.round(Math.sqrt(x*x + y*y + z*z));
    }
}