
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruan on 9/12/16.
 */
public class Graph_arraylist {

    private List<Node> nodes = null;

    public List<Node> getNodes() {
        return nodes;
    }

    public int getV() {
        return V;
    }

    private int V;
    private int E;
    public Bag<Integer>[] adj;

    public Graph_arraylist(In in) {
        V = in.readInt();
        E = in.readInt();
        adj = (Bag<Integer>[]) new Bag[V];
        nodes = new ArrayList<Node>();
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        for (int i = 0; i < E; i++) {
            int s = in.readInt();
            int d = in.readInt();
            addEdge(s, d);
        }
        for (int i = 0; i < V; i++) {
            Node node = new Node();
            node.N = in.readInt();
            node.x = in.readDouble();
            node.y = in.readDouble();
            node.P = in.readDouble();
            nodes.add(node);
        }
    }

    public Iterable<Integer> return_adj(int v) {
        return adj[v];
    }

    private void addEdge(int s, int d) {
        adj[s].add(d);
    }

}
