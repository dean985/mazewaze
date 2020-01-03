import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Graph_AlgoTest {

    static Graph_Algo g = new Graph_Algo();

    @BeforeAll
   static void init_for_test()
    {
        g.dGraph = new DGraph(5);
    }

    @Test
    void init() {
    }

    @Test
    void testInit()
    {

    }

    @Test
    void save()
    {
        Graph_Algo g2 = new Graph_Algo();
        g.dGraph.connect(0,1,1);
        g.save("file_test.txt");
        g2.init("file_test.txt");
        Assertions.assertNotNull(((NodeData)((DGraph)g2.dGraph).connectivity.get(0)).adjacency.get(1));
    }

    @Test
    void isConnected()
    {

        for (int i = 0; i <g.dGraph.getV().size(); i++) {
            for (int j = 0; j <g.dGraph.getV().size(); j++) {
                g.dGraph.connect(i,j,1);

            }
        }

        Assertions.assertTrue(g.isConnected());
    }

    @Test
    void shortestPathDist()
    {
       // g.dGraph.connect(0,1,1);
        g.dGraph.connect(1,3,1);
        g.dGraph.connect(3,4,1);
       // g.dGraph.connect(3,0,1);
        g.dGraph.connect(4,2,1);
        g.dGraph.connect(2,1,1);

        Assertions.assertEquals(3,g.shortestPathDist(1,2));
        g.dGraph.addNode(new NodeData(5));
        Assertions.assertThrows(Exception.class,()->g.shortestPathDist(1,2));
    }

    @Test
    void shortestPath()
    {
        g.dGraph.connect(1,3,1);
        g.dGraph.connect(3,4,1);
        g.dGraph.connect(4,2,1);
        g.dGraph.connect(2,1,1);
        Iterator<node_data> list =  g.shortestPath(1,2).listIterator();
        int [] arr = {1,3,4,2};

        for (int i = 0 ; i < arr.length;i++)
        {
            Assertions.assertEquals(list.next().getKey(),arr[i]);
        }
    }

    @Test
    void TSP() {
    }

    @Test
    void copy()
    {

    }
}