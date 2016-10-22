
/**
 * Created by ruan on 16-10-10.
 */
class Edge {
    public float cost, utility;
    private Node s;
    private Node d;

    public Edge(Node s, Node d, float utility) {
        cost = (float) getDis(s, d);
        this.utility = utility;
    }

    public Node other() {
        return d;
    }

    public double getDis(Node p1, Node p2) {
        double dis = Math.sqrt(Math.abs(p1.x - p2.x) * Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) * Math.abs(p1.y - p2.y));
        return dis;
    }

}
