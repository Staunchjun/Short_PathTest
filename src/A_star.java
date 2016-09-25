import java.util.*;
import java.util.Map;

/**
 * Created by ruan on 9/12/16.
 */
public class A_star {
    public void printPath(Graph map, Node s, Node d) {
        PointComparator pointComparator = new PointComparator();
        PriorityQueue priorityQueue = new PriorityQueue(map.getV(), pointComparator);
        Map<Double,ArrayList> all_bus = new HashMap<Double,ArrayList>();
        s.G =0;
        s.H =0;
        s.F =s.G+s.H;
        s.parent = null;
        priorityQueue.offer(s);
        while (!priorityQueue.isEmpty())
        {
            Node n = (Node) priorityQueue.poll();
            for (int i :map.return_adj(n.N)) {
                Node next_n = map.getNodeMap().get(i);

                double temp_G = n.G+Node.getDis(n,next_n);
                double temp_H = Node.getDis(next_n, d);
                double temp_F =temp_G+temp_H;

                if (next_n.N == d.N)
                {
                    next_n.G=(temp_G);
                    next_n.H=(temp_H);
                    next_n.F=(temp_F);
                    d.parent = n;
                    Node node = d;
                    ArrayList<Node> arrayList = new ArrayList();
                    while (node != null)
                    {
                        arrayList.add(node);
                        node = node.parent;
                    }
                    all_bus.put(map.getNodeMap().get(6).G,arrayList);

                    break;
                }

             //这里有问题不知道为什么当arrayList.add(node);添加完结后，继续第二轮数据会出现变动？？
                if (priorityQueue.contains(next_n)) {
                    Iterator iterator = priorityQueue.iterator();
                    Node point = null;
                    while (iterator.hasNext()) {
                        point = (Node) iterator.next();
                        if (point.N==next_n.N)
                        {
                          break;
                        }
                    }
                    if (temp_F < point.F) {
                        priorityQueue.remove(point);
                        next_n.G=temp_G;
                        next_n.H=temp_H;
                        next_n.F=temp_F;
                        priorityQueue.offer(next_n);
                        next_n.parent = n;
                    }
                } else {

                    next_n.G=temp_G;
                    next_n.H=temp_H;
                    next_n.F=temp_F;
                    priorityQueue.offer(next_n);
                    next_n.parent = n;
                }
            }

        }
        
        ChooseBestPath2(all_bus);

    }
    private void ChooseBestPath2(Map<Double, ArrayList> all_bus) {
        GComparator gcomp = new GComparator();
        UComparator ucomp = new UComparator();

        PriorityQueue g_pq = new PriorityQueue(all_bus.size(), gcomp);
        PriorityQueue u_pq = new PriorityQueue(all_bus.size(), ucomp);
        for (Map.Entry<Double,ArrayList> stack :all_bus.entrySet() )
        {
            double u = 0;
            Iterator iterator = stack.getValue().iterator();
            while (iterator.hasNext())
            {
                Node temp = (Node) iterator.next();
                u = u + temp.P;
            }
                Bus bus = new Bus(stack.getKey(),u);
                g_pq.offer(bus);
        }

        Bus min_bus = (Bus) g_pq.poll();
        double L = min_bus.G*0.25;

        while(!g_pq.isEmpty()) {
            Bus temp_bus = (Bus) g_pq.poll();
            if (min_bus.G + L > temp_bus.G)
            {

                u_pq.add(temp_bus);
            }
        }

        Bus max_u_bus = (Bus) u_pq.poll();

        System.out.println("the sum of u is :" +max_u_bus.U);
        System.out.println("the sum of dis is :"+max_u_bus.G);
        ArrayList stack = all_bus.get(max_u_bus.G);
        if (stack == null)
        {
            System.out.print("impossible to reach");
        }
        else
        {
            Iterator iterator = stack.iterator();
            while (iterator.hasNext())
            {
                Node temp = (Node) iterator.next();
                System.out.print(temp.N+" <-");

            }
            System.out.println();

        }
    }
    //Try to  get the upper left corner ,create a division ,take apart ,just get the point at left region ,and on the left region,we get the max u
    private void ChooseBestPath(Map<Double, ArrayList> all_bus) {
        double Cut_off  = 0;
        UComparator ucomp = new UComparator();
        PriorityQueue u_pq = new PriorityQueue(all_bus.size(), ucomp);
        for (Map.Entry<Double,ArrayList> stack :all_bus.entrySet() ) {


               Cut_off =   Cut_off + stack.getKey();

            }
            Cut_off = Cut_off/all_bus.size();
        for (Map.Entry<Double,ArrayList> stack :all_bus.entrySet() )
        {
            double u =0;
            if (stack.getKey() <= Cut_off)
            {
                Iterator iterator = stack.getValue().iterator();
                while (iterator.hasNext())
                {
                    Node temp = (Node) iterator.next();
                    u = u + temp.P;

                }
                Bus bus = new Bus(stack.getKey(),u);
                u_pq.offer(bus);
            }
        }
        //get the left uperr point
        double L = 0.25;
        ArrayList<Bus> standby_bus = new ArrayList();
        while (!u_pq.isEmpty()) {
            Bus min_bus = (Bus) u_pq.poll();
            Iterator iterator = u_pq.iterator();
            if (!iterator.hasNext())
            {
                standby_bus.add(min_bus);
            }
            while (iterator.hasNext()) {
                Bus temp_bus = (Bus) iterator.next();
                if (min_bus.G+L > temp_bus.G && temp_bus.G > min_bus.G-L)
                {
                 if (min_bus.U < temp_bus.U)
                 {
                     standby_bus.add(temp_bus);
                 }
                 else
                 {
                     standby_bus.add(min_bus);
                 }
                }
                else
                {
                    standby_bus.add(min_bus);
                }
            }
        }
//        Bus s_bus = (Bus) u_pq.poll();
        for (Bus s_bus:standby_bus) {

        System.out.println("the sum of u is :" +s_bus.U);
        System.out.println("the sum of dis is :"+s_bus.G);
         ArrayList stack = all_bus.get(s_bus.G);
        if (stack == null)
            {
                System.out.print("impossible to reach");
            }
            else
            {
                Iterator iterator = stack.iterator();
                while (iterator.hasNext())
                {
                    Node temp = (Node) iterator.next();
                    System.out.print(temp.N+" <-");

                }
                System.out.println();

            }
        }

    }


     class UComparator implements Comparator<Bus> {
        @Override
        public int compare(Bus o1, Bus o2) {
            if (o1.U > o2.U) {
                return -1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return 1;
            }
        }
    }
     class GComparator implements Comparator<Bus> {
        @Override
        public int compare(Bus o1, Bus o2) {
            if (o1.G < o2.G) {
                return -1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return 1;
            }
        }
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
}
