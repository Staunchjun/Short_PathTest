import java.util.*;

/**
 * Created by ruan on 9/12/16.
 */
public class A_star1 {
    double L = 0;
    private static long Strat;
    private static long End;
    private boolean isFirstPath = true;
    private List<ArrayList<Node>> allPaths;
    //    private ArrayList<Node> bestPath;
    private Path bestPath;
    //    private float cost , utility;
    private Graph graph;
    private double f[], g[], h[];
//    private Node parent[];


    public A_star1(Graph graph) {
        allPaths = new ArrayList<ArrayList<Node>>();
        this.graph = graph;
        f = new double[graph.getE()];
        g = new double[graph.getE()];
        h = new double[graph.getE()];
//        parent = new Node[graph.getE()];
    }

    private void showPath(Path bestPath) {

        ArrayList stack = bestPath.stack;
        if (stack == null) {
            System.out.print("impossible to reach");
        } else {
            Iterator iterator = stack.iterator();
            while (iterator.hasNext()) {
                Node temp = (Node) iterator.next();
                System.out.print(temp.N + " <-");

            }
            System.out.println();
            System.out.println("cost:" + bestPath.G);
            System.out.println("utility:" + bestPath.U);

        }
    }

    private void savePath(Node node) {
        ArrayList<Node> path = new ArrayList();

        float tempCost = (float) g[node.N];
        float tempUtility = 0;
        while (node != null) {
            tempUtility = (float) (tempUtility + node.P);
            path.add(node);
            node = node.parent;
        }
        Path tempPath = new Path(tempCost, tempUtility, path);
        allPaths.add(path);
        if (isFirstPath) {
            bestPath = tempPath;
            L = bestPath.G * 10025;
        } else {
            if (bestPath != null) {
                if (bestPath.G + L > tempPath.G && bestPath.U < tempPath.U) {
                    bestPath = tempPath;
                }
            } else {
                System.out.println("occur some problem！");
            }
        }
    }

    public void runA_star(Graph graph, Node s, Node d) {
        PointComparator pointComparator = new PointComparator();
        PriorityQueue priorityQueue = new PriorityQueue(graph.getV(), pointComparator);
        PriorityQueue closeQueue = new PriorityQueue(graph.getV(), pointComparator);

        g[s.N] = 0;
        h[s.N] = (float) getDis(s, d);
        f[s.N] = g[s.N] + h[s.N];

        s.parent = null;
        priorityQueue.offer(s);
        while (!priorityQueue.isEmpty()) {
            Node n = (Node) priorityQueue.poll();
            closeQueue.add(n);
            for (Node nn : n.getNeighbors()) {
                double temp_G = g[n.N] + getDis(n, nn);
                double temp_H = getDis(nn, d);
                double temp_F = temp_G + temp_H;

                if (nn.N == d.N) {
                    nn.F = temp_F;
                    g[nn.N] = temp_G;
                    h[nn.N] = temp_H;
                    f[nn.N] = temp_F;

                    d.parent = n;
                    Node node = d;
                    savePath(node);
                    isFirstPath = false;

                    break;
                }
                if (priorityQueue.contains(nn)) {
                    Node old_point = getNodeFromPq(priorityQueue, nn);
                    if (temp_F < old_point.F) {
                        updatePq(priorityQueue, n, nn, temp_G, temp_H, temp_F, old_point);
                    } else {
                        continue;
                    }
                } else if (closeQueue.contains(nn)) {
                    Node old_point = getNodeFromPq(closeQueue, nn);
                    if (temp_F < old_point.F) {
                        updatePq(closeQueue, n, nn, temp_G, temp_H, temp_F, old_point);
                    } else {
                        continue;
                    }
                } else {
                    insertPq(priorityQueue, n, nn, temp_G, temp_H, temp_F);
                }
            }

        }

        if (d.parent == null) {
            allPaths.add(null);
        }
        showPath(bestPath);
        showPath2(allPaths);
    }

    private void insertPq(PriorityQueue priorityQueue, Node n, Node next_n, double temp_G, double temp_H, double temp_F) {
        next_n.F = temp_F;

        g[next_n.N] = temp_G;
        h[next_n.N] = temp_H;
        f[next_n.N] = temp_F;

        priorityQueue.offer(next_n);
        next_n.parent = n;
    }

    private void updatePq(PriorityQueue closeQueue, Node n, Node next_n, double temp_G, double temp_H, double temp_F, Node old_point) {
        closeQueue.remove(old_point);
        insertPq(closeQueue, n, next_n, temp_G, temp_H, temp_F);
    }

    private Node getNodeFromPq(PriorityQueue closeQueue, Node next_n) {
        Iterator iterator = closeQueue.iterator();
        Node old_point = null;
        while (iterator.hasNext()) {
            old_point = (Node) iterator.next();
            if (old_point.N == next_n.N) {
                break;
            }
        }
        return old_point;
    }

    class PointComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.F < o2.F) {
                return -1;
            } else if (o1.F == o2.F) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public double getDis(Node p1, Node p2) {
        double dis = Math.sqrt(Math.abs(p1.x - p2.x) * Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) * Math.abs(p1.y - p2.y));
        return dis;
    }

    public static void main(String args[]) {
        long total = Runtime.getRuntime().maxMemory();
        Strat = System.currentTimeMillis();
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        A_star1 a_star = new A_star1(graph);
        Runtime.getRuntime().totalMemory();
        a_star.runA_star(graph, graph.getNodes().get(0), graph.getNodes().get(4));
        End = System.currentTimeMillis();
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("the total time is " + (End - Strat) + " nanoseconds");
        System.out.println("the required memory is " + (total - freeMemory) / (1024) + "kb");
    }

    //    private void ChooseBestPath3(List all_bus) {
//        GComparator gcomp = new GComparator();
//        UComparator ucomp = new UComparator();
//
//        PriorityQueue g_pq = new PriorityQueue(all_bus.size(), gcomp);
//        PriorityQueue u_pq = new PriorityQueue(all_bus.size(), ucomp);
//        for (Object path :all_bus)
//        {
//            double u = 0;
//            double G = 0;
//            ArrayList stack = (ArrayList) path;
//            Iterator iterator = stack.iterator();
//            while (iterator.hasNext())
//            {
//                Node temp = (Node) iterator.next();
//                u = u + temp.P;
//                G = G +g[temp.N];
//            }
//            Path temp_ptah = new Path(G,u,stack);
//            g_pq.offer(temp_ptah);
//        }
//
//        Path min_bus = (Path) g_pq.poll();
//        double L = min_bus.G*0.15;
//
//        while(!g_pq.isEmpty()) {
//            Path temp_bus = (Path) g_pq.poll();
//            u_pq.add(min_bus);
//            if (min_bus.G + L > temp_bus.G)
//            {
//
//                u_pq.add(temp_bus);
//            }
//        }
//
//        Path max_u_bus = (Path) u_pq.poll();
//
//        System.out.println("the sum of u is :" +max_u_bus.U);
//        System.out.println("the sum of dis is :"+max_u_bus.G);
//        System.out.print("the best path:");
//        ArrayList stack = max_u_bus.stack;
//        if (stack == null)
//        {
//            System.out.print("impossible to reach");
//        }
//        else
//        {
//            Iterator iterator = stack.iterator();
//            while (iterator.hasNext())
//            {
//                Node temp = (Node) iterator.next();
//                System.out.print(temp.N+" <-");
//
//            }
//            System.out.println();
//
//        }
//        System.out.println("-----------------------------------------------");
//    }
//    class UComparator implements Comparator<Path> {
//        @Override
//        public int compare(Path o1, Path o2) {
//            if (o1.U > o2.U) {
//                return -1;
//            } else if (o1 == o2) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }
//    }
//    class GComparator implements Comparator<Path> {
//        @Override
//        public int compare(Path o1, Path o2) {
//            if (o1.G < o2.G) {
//                return -1;
//            } else if (o1 == o2) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }
//    }
    private void showPath2(List all_bus) {
        for (Object path : all_bus) {
            ArrayList stack = (ArrayList) path;
            if (stack == null) {
                System.out.print("impossible to reach");
            } else {
                Iterator iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Node temp = (Node) iterator.next();
                    System.out.print(temp.N + " <-");

                }
                System.out.println();

            }
        }
    }
}
