import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        g.save("yard.txt");
        g2.init("yard.txt");
        Assertions.assertNotNull(((NodeData)((DGraph)g2.dGraph).connectivity.get(0)).adjacency.get(1));
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void TSP() {
    }

    @Test
    void copy() {
    }
}