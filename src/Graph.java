
import java.util.HashMap;

/**
 * Created by ruan on 9/12/16.
 */
public class Graph {

    private java.util.Map<Integer,Node> nodeMap = null;

    public java.util.Map<Integer, Node> getNodeMap() {
        return nodeMap;
    }

    public int getV() {
        return V;
    }

    private int V;
    private int E;
    public Bag<Integer>[] adj;

   public  Graph(In in)
   {
       V = in.readInt();
       E = in.readInt();
       adj = (Bag<Integer>[]) new Bag[V];
       nodeMap = new HashMap<Integer,Node>();
       for (int v = 0; v < V; v++) {
           adj[v] = new Bag<Integer>();
       }
       for (int i = 0; i <E ; i++) {
           int s = in.readInt();
           int d = in.readInt();
           addEdge(s,d);
       }
       for (int i = 0; i <V ; i++) {
           Node node = new Node();
           node.N = in.readInt();
           node.x =in.readInt();
           node.y =in.readInt();
           node.P =in.readDouble();
           nodeMap.put(node.N,node);
       }
   }
    public Iterable<Integer> return_adj(int v) {
        return adj[v];
    }

    private void addEdge(int s, int d) {
        adj[s].add(d);
    }

}
