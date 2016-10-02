/**
 * Created by ruan on 9/12/16.
 */
public class Node {
    public double G = 0;//real cost
    public double H = 0;//"Direction" to end point
    public double F = 0;//G+H = F
    public double P = 0;//probbilty
    public double x; //coordinates x
    public double y;//coordinates y
    public Node parent;
    public int N;//number

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Node() {

    }

    public static double getDis(Node p1, Node p2) {
        double dis =Math.sqrt( Math.abs(p1.x - p2.x) * Math.abs(p1.x - p2.x)+Math.abs(p1.y - p2.y)*Math.abs(p1.y - p2.y) ) ;
        return dis;
    }
}
