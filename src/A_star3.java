import java.util.*;

/**
 * Created by ruan on 9/12/16.
 */
public class A_star3 {
    private double L = 0;
    private static long Strat;
    private static long End;
    private boolean isFirstPath;
    private List<ArrayList<Node>> allPaths;
    private Path bestPath;
    private Path shortestPath;
    private Graph graph;
    private boolean isRun;

    //init data
    public A_star3(Graph graph) {
        allPaths = new ArrayList<ArrayList<Node>>();
        this.graph = graph;
        this.isFirstPath = true;
        this.isRun = true;

    }

    private void ShowBestPath(Path bestPath) {

        ArrayList stack = bestPath.stack;
        if (stack == null) {
            System.out.print("There must be no best path ~~~");
        } else {
            Iterator iterator = stack.iterator();
            while (iterator.hasNext()) {
                Node temp = (Node) iterator.next();
                System.out.print(temp.N + " <-");
            }

            System.out.println();
            System.out.println("Distaance cost:" + bestPath.G);
            System.out.println("Sum of utility:" + bestPath.U);

        }
    }

    private void SavePath(Node node) {
        ArrayList<Node> path = new ArrayList();


        float tempCost = (float) node.G;
        float tempUtility = 0;
        while (node != null) {
            Node FromNode = node;
            path.add(node);
            node = node.parent;
            Node ToNode = node;
            if (ToNode != null) {
                tempUtility = (float) (tempUtility + FromNode.getAdjEdge().get(ToNode.N).utility);
            }

        }
        //tempPath is used to compare Cost and Utility
        Path tempPath = new Path(tempCost, tempUtility, path);


        if (isFirstPath) {
            shortestPath = tempPath;
            bestPath = shortestPath;
            //threshold
            L = bestPath.G * 2;
        } else {
            if (bestPath != null) {
                if (shortestPath.G + L < tempPath.G) {
                    isRun = false;
                } else if (shortestPath.G + L > tempPath.G && bestPath.U < tempPath.U) {
                    bestPath = tempPath;
                }
            } else {
                System.out.println("occur some problem！");
            }
        }
        //store path
        allPaths.add(path);

    }

    public void runA_star(Graph graph, Node s, Node d) {
        PointComparator pointComparator = new PointComparator();
        PriorityQueue priorityQueue = new PriorityQueue(graph.getV(), pointComparator);

        s.G = 0;
        s.H = (float) Util.getDis(s, d);
        s.F = s.G + s.H;

        s.parent = null;
        priorityQueue.offer(s);


        while (!priorityQueue.isEmpty() && isRun) {
            Node n = (Node) priorityQueue.poll();
            for (Node nn : n.getNeighbors()) {
                if (!IsNParentContainNN(n, nn)) {

                    double temp_G = n.G + Util.getDis(n, nn);
                    double temp_H = Util.getDis(nn, d);
                    double temp_F = temp_G + temp_H;

                    //使用树结构的形式存储每一条路径 就不会出现只有4条路径的问题，因为每一次都有.parent覆盖了原有的.parent

                    if (nn.N == d.N) {

                        nn.G = temp_G;
                        nn.H = temp_H;
                        nn.F = temp_F;

                        d.parent = n;
                        Node node = d;
                        SavePath(node);
                        isFirstPath = false;

                        continue;

                    }
                    // 使用下面代码的意思是说 不涉及原有数据的变化，每有一个node都需要新建立
                    if (MyPqContains(priorityQueue, nn)) {
                        Node old_point = getNodeFromPq(priorityQueue, nn);
                        if (temp_F < old_point.F) {
                            updatePq(priorityQueue, n, nn, temp_G, temp_H, temp_F, old_point);
//                            Node new_nn = new Node();
//                            insertPq(priorityQueue, n, nn.clone(new_nn), temp_G, temp_H, temp_F);
                        } else {
                            continue;
//                            Node new_nn = new Node();
//                            insertPq(priorityQueue, n, nn.clone(new_nn), temp_G, temp_H, temp_F);
                        }
                    } else {
                        Node new_nn = new Node();
                        insertPq(priorityQueue, n, nn.clone(new_nn), temp_G, temp_H, temp_F);
                    }
                }

            }

        }

//        if (d.parent == null) {
//            allPaths.add(null);
//        }
        ShowBestPath(bestPath);
        ShoAllwPath(allPaths);
    }

    private boolean MyPqContains(PriorityQueue priorityQueue, Node nn) {
        Iterator iterator = priorityQueue.iterator();
        Node old_point = null;
        while (iterator.hasNext()) {
            old_point = (Node) iterator.next();
            if (old_point.N == nn.N) {
                return true;
            }
        }
        return false;
    }

    private boolean IsNParentContainNN(Node n, Node nn) {
        while (n != null) {
            if (nn.N == n.N) {
                return true;
            }
            n = n.parent;
        }
        return false;
    }

    private synchronized void insertPq(PriorityQueue priorityQueue, Node n, Node next_n, double temp_G, double temp_H, double temp_F) {

        next_n.G = temp_G;
        next_n.H = temp_H;
        next_n.F = temp_F;

        next_n.parent = n;

        priorityQueue.offer(next_n);


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


    private void ShoAllwPath(List all_bus) {
        System.out.println("paths:" + (all_bus.size() - 1));
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
