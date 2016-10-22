
/**
 * Created by ruan on 16-10-10.
 */
class Edge {
    public float cost, utility;
    private Node s;
    private Node d;

    public Edge(Node s, Node d, float utility) {
        cost = (float) Util.getDis(s, d);
        this.utility = utility;
    }

    public Node other() {
        return d;
    }


}
