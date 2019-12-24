

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

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
    void addNode() {
    }

    @Test
    void connect() {
        graph.connect(0, 2, 2.5);
        Assertions.assertNotNull(graph.connectivity.get(0).getEdgesByKey(2));
        Assertions.assertNull(graph.connectivity.get(0).getEdgesByKey(1));
        Assertions.assertEquals(graph.connectivity.get(2), graph.connectivity.get(0).getEdgesByKey(2).node);
    }

    @Test
    void getV() {
    }

    @Test
    void getE() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
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