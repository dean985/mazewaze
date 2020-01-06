import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Graph_AlgoTest {

    static Graph_Algo g = new Graph_Algo();

   @BeforeEach
    void init_for_test()
    {
        g.dGraph = new DGraph(5);

    }


    @Test
    void saveAndInit()
    {
        Graph_Algo g2 = new Graph_Algo();
        g.dGraph.connect(0,1,1);


        g.save("file_test.txt");
        g2.init("file_test.txt");
        NodeData n = (NodeData) ((DGraph)g2.dGraph).connectivity.get(0);
        Assertions.assertNotNull(((NodeData)((DGraph)g2.dGraph).connectivity.get(0)).adjacency.get(1));
        //Assertions.assertNotNull(n.adjacency.get(0));
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
         g.dGraph.connect(3,0,1);

        g.dGraph.connect(1,3,1);
        g.dGraph.connect(3,4,1);
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

        g.dGraph.addNode(new NodeData(1));
        g.dGraph.addNode(new NodeData(2));
        g.dGraph.addNode(new NodeData(3));
        g.dGraph.addNode(new NodeData(4));
        g.dGraph.addNode(new NodeData(5));

        g.dGraph.connect(1,3,1);
        g.dGraph.connect(3,4,1);
        g.dGraph.connect(4,5,1);
        g.dGraph.connect(5,1,1);
        g.dGraph.connect(1,2,1);
        g.dGraph.connect(2,3,1);

        List<Integer> targets = new ArrayList<Integer>();
        targets.add(4);
        targets.add(3);
        targets.add(2);
        // ANSWER SHOULD BE: [4,5,1,3,4,5,1,2]

        int[] real_ans = {4,5,1,3,4,5,1,2};

        g.TSP(targets).forEach((n)-> {
            System.out.print(((NodeData)n).getKey()+",");
        });

        targets.clear();
        targets.add(1);
        targets.add(4);
        targets.add(5);

        // ANSWER SHOULD BE: [1,3,4,5]
        int[] real_ans2 = {1,3,4,5};
        g.TSP(targets).forEach((n)-> {
            System.out.print(((NodeData)n).getKey()+",");
        });



/*

        g.dGraph.addNode(new NodeData(1,0, new Point3D(100,200,300)));
        g.dGraph.addNode(new NodeData(2,0, new Point3D(200,300,400)));
        g.dGraph.connect(1, 2, 5);
        g.dGraph.connect(2, 1, 10);
        List<Integer> targets = new ArrayList<Integer>();
        targets.add(1);
        targets.add(2);
        targets.add(1);
       // g.init(g.dGraph);
        List<node_data> ans =g.TSP(targets);
        if(ans.isEmpty())
            System.out.println("null");
        else {
            for (int i = 0; i < ans.size(); i++) {
                System.out.print(ans.get(i).getKey() + " ");
            }
        }*/
    }

    @Test
    void copy()
    {
       DGraph copied_graph ;
       copied_graph =(DGraph) g.copy();
       Iterator<node_data> iter =  g.dGraph.getV().iterator();
       Iterator<node_data> iter2 = copied_graph.getV().iterator();

       while (iter.hasNext() && iter2.hasNext())
       {
           Assertions.assertEquals(iter.next(),iter2.next());
       }


    }
    @Test
    void sp_test()
    {
        DGraph g1 = new DGraph();
       /* NodeData n1 = new NodeData(0, 0, new Point3D(200,160,0));
        NodeData n2 = new NodeData(1, 0, new Point3D(300,300,0));

        g1.addNode(n1);
        g1.addNode(n2);

        g1.connect(0,1,0.5);*/
        NodeData n0 = new NodeData(0,2, new Point3D(130,130,0));
        NodeData n1 = new NodeData(1,2, new Point3D(200,300,0));
        NodeData n2 = new NodeData(2,2, new Point3D(150,260,0));
        NodeData n3 = new NodeData(3,2, new Point3D(175,210,0));
        NodeData n4 = new NodeData(4,2, new Point3D(125,310,0));
        g1.addNode(n0);
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addNode(n3);
        g1.addNode(n4);

        g1.connect(0,1,2);
        g1.connect(1,3,1);
        g1.connect(3,4,1);
        g1.connect(4,2,1);
        g1.connect(2,1,1);
        g.init(g1);
        //g.dGraph = g1;
        Iterator<node_data> list =  (g.shortestPath(1,2)).listIterator();
        while (list.hasNext())
        {
            System.out.println( list.next().getKey());
        }
    }


}