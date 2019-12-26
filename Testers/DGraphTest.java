import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DGraphTest {
    static DGraph graph;
    @BeforeAll
    static void init()
    {
        graph = new DGraph(5);

    }
    @Test
    void getNode()
    {
        Assertions.assertEquals(graph.getNode(1), graph.connectivity.get(1));
    }

    @Test
    void getEdge() {
        graph.connect(1,4 , 4.5);
        Assertions.assertEquals(((Edge)graph.getEdge(1,4)).weight, 4.5 );
    }

    @Test
    void addNode()
    {

    }

    @Test
    void connect() {
        graph.connect(0, 2, 2.5);
        NodeData n1 = (NodeData) graph.connectivity.get(0);
        Assertions.assertNotNull(n1.getEdgesByKey(2));
        Assertions.assertNull(n1.getEdgesByKey(1));
        Assertions.assertEquals(graph.connectivity.get(2), n1.getEdgesByKey(2).node);
    }

    @Test
    void getV() {
        Assertions.assertEquals(5,graph.getV().size());
    }

    @Test
    void getE() {
    }

    @Test
    void removeNode()
    {
        graph.connect(2, 0, 2.5);
        graph.connect(2, 1, 2.5);

        graph.removeNode(0);
        Assertions.assertNull(graph.connectivity.get(2).getEdgesByKey(0));
        Assertions.assertNull(graph.connectivity.get(0));

        Assertions.assertNotNull(graph.connectivity.get(1));
        Assertions.assertNotNull(graph.connectivity.get(2).getEdgesByKey(1));



    }

    @Test
    void removeEdge()
    {
        graph.connect(2, 0, 2.5);
        graph.connect(2, 1, 2.5);
        Assertions.assertNull(graph.connectivity.get(2).getEdgesByKey(0));
        graph.removeEdge(2,0);
        Assertions.assertNull(graph.connectivity.get(2).getEdgesByKey(0));
        Assertions.assertNull(graph.connectivity.get(0));

    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}