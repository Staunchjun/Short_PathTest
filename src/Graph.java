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
//            node.P = in.readDouble();
        }
    }

    public Graph(List<EdgeBean> EdgeBean, List<MapNode> NodesBean) {
        V = NodesBean.size();// add node 0
        E = EdgeBean.size();

        this.nodes = new ArrayList<Node>(V);
        for (int j = 0; j < 1; j++) {
            this.nodes.add(0, new Node());//add node 0 in case the error
        }
        //init nodes
        for (int i = 1; i <= V; i++) {
            this.nodes.add(i, new Node());
        }
        //read Node data
        for (MapNode mapNode : NodesBean) {
            int n = mapNode.getId();
            Node node = this.nodes.get(n);
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
        //read Edge data
        for (EdgeBean beann : EdgeBean) {

            List<Integer> adjNode = beann.getAdj();

            int s = adjNode.get(0);//from
            int d = adjNode.get(1);//to

            this.nodes.get(s).addNeighbor(this.nodes.get(d));
            this.nodes.get(s).addEdge(this.nodes.get(d).N, new Edge(this.nodes.get(s), this.nodes.get(d), beann.getUtility(), beann.getId()));
            this.nodes.get(d).addNeighbor(this.nodes.get(s));
            this.nodes.get(d).addEdge(this.nodes.get(s).N, new Edge(this.nodes.get(d), this.nodes.get(s), beann.getUtility(), beann.getId()));

        }


    }

}
