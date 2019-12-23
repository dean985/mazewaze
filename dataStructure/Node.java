package dataStructure;

import utils.Point3D;

public class Node implements node_data {
    int key;
    Point3D location;
    double weight;
    String info;
    int tag;

    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public Point3D getLocation() {
        return null;
    }

    @Override
    public void setLocation(Point3D p) {

    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
