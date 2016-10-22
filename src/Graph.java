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

    public Graph(List<EdgeBean> edgeBeens, List<MapNode> mapNodes) {
        V = mapNodes.size() + 1;
        E = edgeBeens.size();
        nodes = new ArrayList<Node>(V + 1);


        nodes.add(0, new Node());
        for (int i = 1; i <= V; i++) {
            nodes.add(i, new Node());
        }


        for (MapNode mapNode : mapNodes) {
            int n = mapNode.getId();
            Node node = nodes.get(n);
            node.N = n;
            String corrdinate = mapNode.getSt_astext();
            String[] strings = corrdinate.split(" ");
            String[] X_array = strings[0].replace('(', 'A').split("A");
            String x = X_array[1];
            String[] Y_array = strings[1].replace(')', 'A').split("A");
            String Y = Y_array[0];
            node.x = new Double(x);
            node.y = new Double(Y);
        }

        for (EdgeBean beann : edgeBeens) {

            List<Integer> adjNode = beann.getAdjNode();
            int s = adjNode.get(0);
            int d = adjNode.get(1);

            nodes.get(s).addNeighbor(nodes.get(d));
            nodes.get(s).addEdge(nodes.get(d).N, new Edge(nodes.get(s), nodes.get(d), beann.getSumutility()));
            nodes.get(d).addNeighbor(nodes.get(s));
            nodes.get(d).addEdge(nodes.get(s).N, new Edge(nodes.get(d), nodes.get(s), beann.getSumutility()));

        }


    }

}
