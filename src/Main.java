/**
 * Created by ruan on 9/12/16.
 */
public class Main {
    private static long Strat;
    private static long End;

    public static void main(String args[]) {
        long total = Runtime.getRuntime().totalMemory();
        Strat = System.currentTimeMillis();
        In in = new In(args[0]);
        Graph_arraylist map = new Graph_arraylist(in);
        A_star a_star = new A_star();
        Runtime.getRuntime().totalMemory();
        a_star.printPath2(map, map.getNodes().get(0), map.getNodes().get(4));
        End = System.currentTimeMillis();
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("the total time is " + (End - Strat) + " nanoseconds");
        System.out.println("the required memory is " + (total - freeMemory) / (1024) + "kb");
    }
}
