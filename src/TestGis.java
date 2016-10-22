import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruan on 16-10-21.
 */
public class TestGis {

    private static Connection conn;

    public static void init() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/MyMap";
        conn = DriverManager.getConnection(url, "postgres", "123");
        Statement stmt = conn.createStatement();

//        ResultSet s_node = stmt.executeQuery("SELECT array_to_json(array_agg(t)) FROM Node As t;");

        ResultSet s_edge = stmt.executeQuery("select row_to_json(t)  from edge as t;");
        List<EdgeBean> beanns = new ArrayList<EdgeBean>();
        Gson gson = new Gson();
        while (s_edge.next()) {
            EdgeBean bean = new EdgeBean();
            bean = gson.fromJson(s_edge.getString(1), EdgeBean.class);
            beanns.add(bean);
        }
        for (EdgeBean beann : beanns) {
            System.out.println(beann.getAdjNode());
        }


        ResultSet s_node = stmt.executeQuery("select row_to_json(t)  FROM (select id, ST_ASTEXT(the_geom) from Node) As t");
        List<MapNode> MapNodeBeanns = new ArrayList<MapNode>();
        while (s_node.next()) {
            MapNode bean = new MapNode();
            bean = gson.fromJson(s_node.getString(1), MapNode.class);
            MapNodeBeanns.add(bean);
        }

        stmt.close();

        Graph graph = new Graph(beanns, MapNodeBeanns);
        A_star2 a_star = new A_star2(graph);
        a_star.runA_star(graph, graph.getNodes().get(4), graph.getNodes().get(15));

    }

    public void destroy() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String args[]) throws Exception {
        init();
    }

}