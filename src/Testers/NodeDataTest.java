import dataStructure.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Point3D;


class NodeDataTest {

    NodeData node ;

    @BeforeEach
    void init()
    {
       node = new NodeData(1);

    }

    @Test
    void getKey()
    {

        Assertions.assertEquals(1,node.getKey());
    }

    @Test
    void getLocation()
    {
        Assertions.assertEquals(0,node.getLocation().x());
        Assertions.assertEquals(0,node.getLocation().y());
    }


    @Test
    void setLocation()
    {
        Assertions.assertEquals(0,node.getLocation().x());
        Assertions.assertEquals(0,node.getLocation().y());
        node.setLocation(new Point3D(1,1,1));
        Assertions.assertEquals(1,node.getLocation().x());
        Assertions.assertEquals(1,node.getLocation().y());
    }

    @Test
    void getWeight()
    {
        Assertions.assertEquals(Double.POSITIVE_INFINITY,node.getWeight());
    }

    @Test
    void setWeight()
    {
        Assertions.assertEquals(Double.POSITIVE_INFINITY,node.getWeight());
       node.setWeight(5);
        Assertions.assertEquals(5,node.getWeight());

    }

    @Test
    void getInfo()
    {
        Assertions.assertEquals("", node.getInfo());

    }

    @Test
    void setInfo()
    {
        Assertions.assertEquals("", node.getInfo());
        node.setInfo("hello");
        Assertions.assertEquals("hello", node.getInfo());

    }

    @Test
    void getTag()
    {
        Assertions.assertEquals(0, node.getTag());

    }

    @Test
    void setTag()
    {
        Assertions.assertEquals(0, node.getTag());
        node.setTag(2);
        Assertions.assertEquals(2, node.getTag());

    }
}