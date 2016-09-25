import java.util.*;

/**
 * Created by ruan on 16-9-25.
 */
public class Temp {
    //Try to  get the upper left corner ,create a division ,take apart ,just get the point at left region ,and on the left region,we get the max u
    private void ChooseBestPath(Map<Double, ArrayList> all_bus) {
        double Cut_off = 0;
        UComparator ucomp = new UComparator();
        PriorityQueue u_pq = new PriorityQueue(all_bus.size(), ucomp);
        for (Map.Entry<Double, ArrayList> stack : all_bus.entrySet()) {


            Cut_off = Cut_off + stack.getKey();

        }
        Cut_off = Cut_off / all_bus.size();
        for (Map.Entry<Double, ArrayList> stack : all_bus.entrySet()) {
            double u = 0;
            if (stack.getKey() <= Cut_off) {
                Iterator iterator = stack.getValue().iterator();
                while (iterator.hasNext()) {
                    Node temp = (Node) iterator.next();
                    u = u + temp.P;

                }
                Bus bus = new Bus(stack.getKey(), u);
                u_pq.offer(bus);
            }
        }
        //get the left uperr point
        double L = 0.25;
        ArrayList<Bus> standby_bus = new ArrayList();
        while (!u_pq.isEmpty()) {
            Bus min_bus = (Bus) u_pq.poll();
            Iterator iterator = u_pq.iterator();
            if (!iterator.hasNext()) {
                standby_bus.add(min_bus);
            }
            while (iterator.hasNext()) {
                Bus temp_bus = (Bus) iterator.next();
                if (min_bus.G + L > temp_bus.G && temp_bus.G > min_bus.G - L) {
                    if (min_bus.U < temp_bus.U) {
                        standby_bus.add(temp_bus);
                    } else {
                        standby_bus.add(min_bus);
                    }
                } else {
                    standby_bus.add(min_bus);
                }
            }
        }
//        Bus s_bus = (Bus) u_pq.poll();
        for (Bus s_bus : standby_bus) {

            System.out.println("the sum of u is :" + s_bus.U);
            System.out.println("the sum of dis is :" + s_bus.G);
            ArrayList stack = all_bus.get(s_bus.G);
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
