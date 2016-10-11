
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruan on 9/12/16.
 */
public class Graph {

    private int V;
    private int E;
    private List<Node> nodes = null;

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }

    public Graph(In in) {
        V = in.readInt();
        nodes = new ArrayList<Node>(V);
        for (int i = 0; i < V; i++) {
            nodes.add(i, new Node());
        }
        E = in.readInt();
        for (int i = 0; i < E; i++) {
            int s = in.readInt();
            int d = in.readInt();
            nodes.get(s).addNeighbor(nodes.get(d));
//            nodes.get(s).adjEdge.add(new Edge(nodes.get(s),nodes.get(d)));

        }
        for (int i = 0; i < V; i++) {
            int n = in.readInt();
            Node node = nodes.get(n);
            node.N = n;
            node.x = in.readDouble();
            node.y = in.readDouble();
            node.P = in.readDouble();
        }
    }

}
