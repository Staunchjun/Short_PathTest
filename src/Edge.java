
/**
 * Created by ruan on 16-10-10.
 */
public class Edge {
    public float cost, utility;
    private Node s;
    private Node d;

    public Edge(Node s, Node d) {
        cost = (float) getDis(s, d);
        utility = (float) sumUtility(s, d);
    }

    public Node other() {
        return d;
    }

    public double getDis(Node p1, Node p2) {
        double dis = Math.sqrt(Math.abs(p1.x - p2.x) * Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) * Math.abs(p1.y - p2.y));
        return dis;
    }

    public double sumUtility(Node p1, Node p2) {
        double utility = p1.P + p2.P;
        return utility;
    }
}
