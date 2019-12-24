import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.*;


class NodeDataTest {

    NodeData node ;

    @BeforeAll
    void init()
    {
       node = new NodeData(1,new Point3D(1,1,0));

    }

    @Test
    void getKey()
    {

        Assertions.assertEquals(1,node.getKey());
    }

    @Test
    void getLocation() {
    }


    @Test
    void setLocation() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }
}