//import java.util.*;
//
///**
// * Created by ruan on 9/12/16.
// */
//public class A_star {
//    private static long Strat;
//    private static long End;
//    private List<ArrayList<Node>> allPaths;
////    private List<Float> allCosts;
////    private List<Float> allUtilities;
//    private ArrayList<Node> bestPath;
//    private float cost, utility;
//    private Graph graph;
//    private float f[], g[], h[];
////    private Node parent[];
//
//
//    public A_star(Graph graph){
//        allPaths = new ArrayList<ArrayList<Node>>();
//        this.graph = graph;
//        f = new float[graph.getE()];
//        g = new float[graph.getE()];
//        h = new float[graph.getE()];
////        parent = new Node[graph.getE()];
//    }
//
//    private void showPath (Map<Double, ArrayList> all_bus) {
//        for (Map.Entry<Double, ArrayList> bus : all_bus.entrySet()) {
//            ArrayList stack = bus.getValue();
//            if (stack == null) {
//                System.out.print("impossible to reach");
//            } else {
//                Iterator iterator = stack.iterator();
//                while (iterator.hasNext()) {
//                    Node temp = (Node) iterator.next();
//                    System.out.print(temp.N + " <-");
//
//                }
//                System.out.println();
//
//            }
//        }
//    }
//    private void showPath2 (List all_bus) {
//        for (Object path : all_bus) {
//            ArrayList stack = (ArrayList) path;
//            if (stack == null) {
//                System.out.print("impossible to reach");
//            } else {
//                Iterator iterator = stack.iterator();
//                while (iterator.hasNext()) {
//                    Node temp = (Node) iterator.next();
//                    System.out.print(temp.N + " <-");
//
//                }
//                System.out.println();
//
//            }
//        }
//    }
//
////    public void printPath2(Graph_arraylist map, Node s, Node d) {
////        PointComparator pointComparator = new PointComparator();
////        PriorityQueue priorityQueue = new PriorityQueue(map.getV(), pointComparator);
////        PriorityQueue closeQueue = new PriorityQueue(map.getV(), pointComparator);
////        Map<Double, ArrayList> all_path = new HashMap<Double, ArrayList>();
////        s.G =0;
////        s.H = getDis(s, d);
////        s.F =s.G+s.H;
////        s.parent = null;
////        priorityQueue.offer(s);
////        while (!priorityQueue.isEmpty())
////        {
////            Node n = (Node) priorityQueue.poll();
////            closeQueue.add(n);
////            for (int i :map.return_adj(n.N)) {
////                Node next_n = map.getNodes().get(i);
////
////                double temp_G = n.G+getDis(n,next_n);
////                double temp_H = getDis(next_n, d);
////                double temp_F =temp_G+temp_H;
////
////                ArrayList<Node> arrayList = null;
////                if (next_n.N == d.N)
////                {
////                    next_n.G = temp_G;
////                    next_n.H = temp_H;
////                    next_n.F = temp_F;
////                    d.parent = n;
////                    Node node = d;
////
////                    arrayList = new ArrayList();
////
////                    while (node != null)
////                    {
////                        arrayList.add(node);
////                        node = node.parent;
////                    }
////                    all_path.put(map.getNodes().get(d.N).G, arrayList);
////
////                    break;
////                }
////
////                //这里有问题不知道为什么当arrayList.add(node);添加完结后，继续第二轮数据会出现变动？？
////                if (priorityQueue.contains(next_n)) {
////                    Node old_point = getNodeFromPq(priorityQueue, next_n);
////                    if (temp_F < old_point.F) {
////                        updatePq(priorityQueue, n, next_n, temp_G, temp_H, temp_F, old_point);
////                    } else {
////                        continue;
////                    }
////                } else if (closeQueue.contains(next_n)) {
////                    Node old_point = getNodeFromPq(closeQueue, next_n);
////                    if (temp_F < old_point.F) {
////                        updatePq(closeQueue, n, next_n, temp_G, temp_H, temp_F, old_point);
////                    } else {
////                        continue;
////                    }
////                } else {
////                    insertPq(priorityQueue, n, next_n, temp_G, temp_H, temp_F);
////                }
////            }
////
////        }
////
////        if (d.parent == null) {
////            all_path.put(0.0, null);
////        }
////        ChooseBestPath2(all_path);
////        showPath(all_path);
////
////    }
//
//    private void savePath(Node node){
//        ArrayList<Node> path = new ArrayList();
//
//        while (node != null) {
//            path.add(node);
//            node = node.parent;
//        }
//        allPaths.add(path);
//    }
//
////    public void printPath(Graph graph, Node s, Node d) {
////        PointComparator pointComparator = new PointComparator();
////        PriorityQueue priorityQueue = new PriorityQueue(graph.getV(), pointComparator);
////        PriorityQueue closeQueue = new PriorityQueue(graph.getV(), pointComparator);
////
//////        s.G = 0; // g[s.getIndex()]
//////        s.H = getDis(s, d);
//////        s.F = s.G + s.H;
//////        s.parent = null;
////        g[s.N] = 0;
////        h[s.N] = (float) getDis(s,d);
////        f[s.N] = g[s.N]+ h[s.N];
////
////        priorityQueue.offer(s);
////        while (!priorityQueue.isEmpty()) {
////            Node n = (Node) priorityQueue.poll();
////            closeQueue.add(n);
////            for (Edge edge : n.getEdge()) {
////                Node nn = edge.other(n);
////                float temp_G = g[n.N] + edge.cost;
////                float temp_H = getDis(nn, d);
////                float temp_F = temp_G + temp_H;
////
////                if (nn.N == d.N) {
////                   g[nn.N]=temp_G;
////                   h[nn.N] =temp_H;
////                   f[nn.N]=temp_F;
////                    d.parent = n;
////                    Node node = d;
////
//////                    parent[nn.N] = n;
////                    savePath(node);
////
////                    break;
////                }
////                if (priorityQueue.contains(nn)) {
////                    Node old_point = getNodeFromPq(priorityQueue, nn);
////                    if (f[nn.N] < f[old_point.N b]) {
////                        updatePq(priorityQueue, n, nn, temp_G, temp_H, temp_F, old_point);
////                    } else {
////                        continue;
////                    }
////                } else if (closeQueue.contains(nn)) {
////                    Node old_point = getNodeFromPq(closeQueue, nn);
////                    if (temp_F < old_point.F) {
////                        updatePq(closeQueue, n, nn, temp_G, temp_H, temp_F, old_point);
////                    } else {
////                        continue;
////                    }
////                } else {
////                    insertPq(priorityQueue, n, nn);
////                }
////            }
////
////        }
////
////        if (d.parent == null) {
////            allPaths.add(null);
////        }
////        ChooseBestPath3(allPaths);
////        showPath2(allPaths);
////
////    }
//
//    private void insertPq(PriorityQueue priorityQueue, Node n, Node next_n) {
//        priorityQueue.offer(next_n);
//        next_n.parent = n;
//    }
//
////    private void updatePq(PriorityQueue closeQueue, Node n, Node next_n, double temp_G, double temp_H, double temp_F, Node old_point) {
////        closeQueue.remove(old_point);
////        insertPq(closeQueue, n, next_n, temp_G, temp_H, temp_F);
////    }
//
//    private Node getNodeFromPq(PriorityQueue closeQueue, Node next_n) {
//        Iterator iterator = closeQueue.iterator();
//        Node old_point = null;
//        while (iterator.hasNext()) {
//            old_point = (Node) iterator.next();
//            if (old_point.N == next_n.N) {
//                break;
//            }
//        }
//        return old_point;
//    }
//
//    private void ChooseBestPath2(Map<Double, ArrayList> all_bus) {
//        GComparator gcomp = new GComparator();
//        UComparator ucomp = new UComparator();
//
//        PriorityQueue g_pq = new PriorityQueue(all_bus.size(), gcomp);
//        PriorityQueue u_pq = new PriorityQueue(all_bus.size(), ucomp);
//        for (Map.Entry<Double,ArrayList> stack :all_bus.entrySet() )
//        {
//            double u = 0;
//            Iterator iterator = stack.getValue().iterator();
//            while (iterator.hasNext())
//            {
//                Node temp = (Node) iterator.next();
//                u = u + temp.P;
//            }
//            Path bus = new Path(stack.getKey(),u);
//                g_pq.offer(bus);
//        }
//
//        Path min_bus = (Path) g_pq.poll();
//        double L = min_bus.G*0.25;
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
//        ArrayList stack = all_bus.get(max_u_bus.G);
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
//    private void ChooseBestPath3(List all_bus) {
//        GComparator gcomp = new GComparator();
//        UComparator ucomp = new UComparator();
//
//        PriorityQueue g_pq = new PriorityQueue(all_bus.size(), gcomp);
//        PriorityQueue u_pq = new PriorityQueue(all_bus.size(), ucomp);
//        for (Object path :all_bus)
//        {
//            double u = 0;
//            double g = 0;
//            ArrayList stack = (ArrayList) path;
//            Iterator iterator = stack.iterator();
//            while (iterator.hasNext())
//            {
//                Node temp = (Node) iterator.next();
//                u = u + temp.P;
//                g = g + temp.G;
//            }
//                Path temp_ptah = new Path(g,u,stack);
//                g_pq.offer(temp_ptah);
//        }
//
//        Path min_bus = (Path) g_pq.poll();
//        double L = min_bus.G*0.25;
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
//
//     class UComparator implements Comparator<Path> {
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
//     class GComparator implements Comparator<Path> {
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
//     class PointComparator implements Comparator<Node> {
//        @Override
//        public int compare(Node o1, Node o2) {
//            if (o1.F < o2.F) {
//                return -1;
//            } else if (o1.F == o2.F) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }
//    }
//
//    public  float getDis(Node p1, Node p2) {
//        float dis = (float) Math.sqrt( Math.abs(p1.x - p2.x) * Math.abs(p1.x - p2.x)+Math.abs(p1.y - p2.y)*Math.abs(p1.y - p2.y) );
//        return dis;
//    }
//
//    public static void main(String args[]) {
//        long total = Runtime.getRuntime().maxMemory();
//        Strat = System.currentTimeMillis();
//        In in = new In(args[0]);
//        Graph graph = new Graph(in);
//        A_star a_star = new A_star(graph);
//        Runtime.getRuntime().totalMemory();
//        a_star.printPath(graph, graph.getNodes().get(0), graph.getNodes().get(11));
//        End = System.currentTimeMillis();
//        long freeMemory = Runtime.getRuntime().freeMemory();
//        System.out.println("the total time is " + (End - Strat) + " nanoseconds");
//        System.out.println("the required memory is " + (total - freeMemory) / (1024) + "kb");
//    }
//}
