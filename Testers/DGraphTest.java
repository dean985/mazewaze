import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Collection;

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
        Assertions.assertEquals(graph.getNode(-1), null);
    }

    @Test
    void getEdge() {
        graph.connect(1,4 , 4.5);
        Assertions.assertEquals(((Edge)graph.getEdge(1,4)).weight, 4.5 );
    }

    @Test
    void addNode()
    {
        node_data n = new NodeData(6);
        graph.addNode(n);
        Assertions.assertEquals(6, graph.nodeSize());
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
        graph.connect(0,1,2);
        graph.connect(0, 2 , 4.5);
        graph.connect(3, 4, 1);
        graph.connect(1, 2 , 2);

        Collection<edge_data> e = graph.getE(0);
        Collection<edge_data> e_null = graph.getE(2);
        Assertions.assertEquals(2, e.size());
        Assertions.assertEquals(0,e_null.size());
    }

    @Test
    void removeNode()
    {
        graph.connect(2, 0, 2.5);
        graph.connect(2, 1, 2.5);

        graph.removeNode(0);
        Assertions.assertNull(((NodeData)graph.connectivity.get(2)).getEdgesByKey(0));
        Assertions.assertNull(graph.connectivity.get(0));

        Assertions.assertNotNull(graph.connectivity.get(1));
        Assertions.assertNotNull((((NodeData)graph.connectivity.get(2)).getEdgesByKey(1)));



    }

    @Test
    void removeEdge()
    {
        graph.connect(2, 0, 2.5);
        graph.connect(2, 1, 2.5);
        Assertions.assertNotNull(((NodeData)graph.connectivity.get(2)).getEdgesByKey(0));
        graph.removeEdge(2,0);
        Assertions.assertNull(((NodeData)graph.connectivity.get(2)).getEdgesByKey(0));
        Assertions.assertNotNull(graph.connectivity.get(0));

    }

    @Test
    void nodeSize() {
        Assertions.assertEquals(5, graph.nodeSize());
    }

    @Test
    void edgeSize() {
        graph.connect(0,1,1);
        graph.connect(1,2,2);
        graph.connect(3,4,2);
        Assertions.assertEquals(3, graph.edgeSize());
    }

    @Test
    void getMC() {
        Assertions.assertEquals(0, graph.getMC());
        graph.connect(0,1,1);
        node_data n = new NodeData(6);
        graph.addNode(n);
        Assertions.assertEquals(2, graph.getMC());
        graph.removeNode(6);
        Assertions.assertEquals(3,graph.getMC());

    }
}