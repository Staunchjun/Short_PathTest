

/**
 * Created by ruan on 9/12/16.
 */
public class Main {
    public static void main(String args[]){
        In in = new In(args[0]);
        Graph map = new Graph(in);
        A_star a_star = new A_star();

        a_star.printPath(map,map.getNodeMap().get(0),map.getNodeMap().get(6));


  }

}
