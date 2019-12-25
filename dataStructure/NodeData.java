
import java.io.Serializable;
import java.util.Hashtable;

enum Colors{
    Red,Black,Green,Yellow, Purple, Gray;
        }


public class NodeData implements node_data, Serializable {
    private int key;
    private Point3D position;
    private double weight;
    private int tag;
    public int previous;
    public boolean visited;
    // K - dest_node's key , V - Edge that is directing towards that dest_node
    public Hashtable<Integer, Edge> adjacency = new Hashtable<Integer, Edge>();


    public NodeData (int key){
        Point3D p1 = new Point3D(Math.random()*10,Math.random()*10,Math.random()*10);
        this.key = key;
        this.weight = 0;
        previous = -1;
        visited = false;

    }

    public NodeData (int key, double weight){
        Point3D p1 = new Point3D(Math.random()*10,Math.random()*10,Math.random()*10);
        this.key = key;
        this.weight = weight;
        previous = -1;
        visited = false;

    }

    public NodeData(NodeData n){
        this.key = n.key;
        this.weight = n.key;
        this.previous = n.previous;
        this.visited = n.visited;
    }

   public NodeData(int key, double weight, Point3D point3D)
    {
        this.key = key;
        this.weight = weight;
        previous = -1;
        visited = false;
        this.position = point3D;
    }

    public Edge getEdgesByKey(int key){
        // key of destination
        return adjacency.get(key);
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        return position;
    }

    @Override
    public void setLocation(Point3D p) {
        position = p;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        weight = w;
    }

    @Override
    public String getInfo() {
       String str ;
       str = "key: " +  Double.toString(key) + " ";
       str += "(" + position.x() + "," + position.y() + "," + position.z() + ") \n";
       str += adjacency.toString();
        return str;
    }

    @Override
    public void setInfo(String s)
    {

    }
    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {

       tag = t;
    }


}
