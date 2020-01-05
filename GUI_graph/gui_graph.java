//import Point3D;
import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class gui_graph extends JFrame implements  MenuListener,ActionListener, MouseListener {
    ///////////////////////////////////////////////////////////////////
    /////////////////////////// FIELDS ////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    private graph Graph;
    private Graph_Algo algo;////////////////////
    private boolean custum_graph = false;       // making your own graph by clicking to make new nodes
    private final int size_node = 5;

    private boolean tsp = false;
    private boolean tsp_rec = false;

    private boolean connected = false;
    private boolean is_connected_on = false;
    private boolean shortest_path_on = false;

    private ArrayList<node_data> targets = new ArrayList<node_data>();
    ///////////////////////////////////////////////////////////////////
    /////////////////////////// Constructors //////////////////////////
    ///////////////////////////////////////////////////////////////////


    public gui_graph(){
        initGraph();
    }

    public gui_graph(graph g){
        initGraph();
        //this.algorithm =
        this.Graph = g;
    }
    ///////////////////////////////////////////////////////////////////
    /////////////////////////// METHODS ///////////////////////////////
    ///////////////////////////////////////////////////////////////////
    public void initGraph(){
        Graph = new DGraph();
        //algo = new Graph_Algo(Graph);

        final int width_window = 800;
        final int height_window = 800;

        this.setSize(width_window, height_window);

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Menu");
        Menu menu_algo = new Menu("Algorithms");

        menuBar.add(menu);
        menuBar.add(menu_algo);

        this.setMenuBar(menuBar);

        //Menu
        MenuItem item1 = new MenuItem("Load Graph");
        item1.addActionListener(this);

        MenuItem item2 = new MenuItem("Save Graph");
        item2.addActionListener(this);

        // Algorithms
        MenuItem item3 = new MenuItem("Is Connected");
        item3.addActionListener(this);

        MenuItem item4 = new MenuItem("Shortest Path");
        item4.addActionListener(this);

        MenuItem item5 = new MenuItem("TSP");
        item5.addActionListener(this);

        menu.add(item1);
        menu.add(item2);
        menu_algo.add(item3);
        menu_algo.add(item4);
        menu_algo.add(item5);

        setVisible(true);
//        this.addMouseListener(this);
//        this.addMouseMotionListener(this);

    }
    @Override
    public void paint(Graphics p) {
        super.paint(p);
        Font font = p.getFont().deriveFont((float) 16.5);
        p.setFont(font);

        Collection<node_data> c1 = Graph.getV();            // collection of the nodes
        Iterator<node_data> nodeIter = c1.iterator();

        while(nodeIter.hasNext()) {
            node_data n = nodeIter.next();
            Point3D point = n.getLocation();

            p.setColor(Color.BLUE);
            p.fillOval(point.ix() - size_node, point.iy() - size_node, 2 * size_node, 2 * size_node);
            p.drawString(n.getKey()+"", point.ix() - size_node, point.iy() - size_node-2);


            Collection<edge_data> c2 = Graph.getE(n.getKey()); // collection of the edges
            Iterator<edge_data> edgeIter = c2.iterator();
            p.setColor(Color.YELLOW);
            // painting the graph by the iterating over the edges
            while(edgeIter.hasNext()) {
                edge_data e1 = edgeIter.next();
                //get p1->p2 of that e1
                Point3D p1 = Graph.getNode(e1.getSrc()).getLocation();
                Point3D p2 = Graph.getNode(e1.getDest()).getLocation();
                //Draw the edge
                p.drawLine(p1.ix(), p1.iy(), p2.ix(), p2.iy());

                p.setColor(Color.BLUE);
                // draw node source
                int x = (int)(0.8*p2.x() + 0.2*p1.x());
                int y = (int)(0.8*p2.y() + 0.2*p1.y());
                p.fillOval(x - size_node , y - size_node , 2 * size_node, 2 * size_node);
                // draw node destination
                x = (int)(0.7*p2.x() + 0.3*p1.x());
                y = (int)(0.7*p2.y() + 0.3*p1.y()-4);
                p.setColor(Color.GREEN);
                p.drawString(String.format("%.1f", e1.getWeight()), x, y);
            }
        }

        if(is_connected_on && connected) {
            p.setColor(Color.BLACK);
            p.drawString("graph is connected", 250, 80);
            is_connected_on = false;
        } else if(is_connected_on && !connected) {
            p.setColor(Color.BLACK);
            p.drawString("graph is not connected", 250, 80);
            is_connected_on = false;
        }

        if(shortest_path_on) {
            p.setColor(Color.BLACK);
            p.drawString("Click on the Source node, then click on the Destination node:", 100, 80);
            p.drawString("The Shortest path between them will be marked with cyan,", 100, 100);
            if(targets.size()==2) {
                algo.init(Graph);
                ArrayList<node_data> ans = (ArrayList<node_data>)
                        algo.shortestPath(targets.get(0).getKey(), targets.get(1).getKey());
                p.setColor(Color.CYAN);

                for(int i = 0; ans != null && i<ans.size()-1 ; i++) {
                    node_data n0 = ans.get(i);
                    node_data n1 = ans.get(i+1);
                    Point3D p0 = n0.getLocation();
                    Point3D p1 = n1.getLocation();
                    p.drawLine(p0.ix()-size_node, p0.iy()-size_node, p1.ix(), p1.iy());
                }

                double sum = algo.shortestPathDist(targets.get(0).getKey(), targets.get(1).getKey());
                p.setColor(Color.BLACK);
                p.drawString("length of the shortest path between "+targets.get(0).getKey()+" and "+targets.get(1).getKey()+" is: "+String.format("%.1f", sum), 100, 120);
                shortest_path_on = false;
            }
        }

        if(tsp) {
            p.setColor(Color.BLACK);
            p.drawString("Click on nodes as you like ,when you finish click on the orange button on the left.", 120, 100);
            p.drawString("A gray path among the nodes you chose will show, if possible.", 120, 120);
            /*if(!targets.isEmpty()) {
                p.drawString("So far you chose these nodes: "+targets, 100, 120);
            }*/
            if (targets.size() == 2){
                p.drawString("You chose: "+ targets, 80, 100);
            }
            //tsp button
            p.setColor(Color.ORANGE);
            p.drawRect(50, 130, 30, 30);
            if(tsp_rec) {
                p.setColor(Color.GRAY);
                algo.init(Graph);

                ArrayList<Integer> nodesKeys = new ArrayList<Integer>();
                for(int i =0 ; i<targets.size(); i++)
                    nodesKeys.add(targets.get(i).getKey());

                ArrayList<node_data> tsp_ans = (ArrayList<node_data>) algo.TSP(nodesKeys);
                String path = "";
                for(int i = 0; tsp_ans != null && i<tsp_ans.size()-1 ; i++) {
                    node_data n1 = tsp_ans.get(i);
                    node_data n2 = tsp_ans.get(i+1);
                    path += n1.getKey()+">";
                    Point3D p1 = n1.getLocation();
                    Point3D p2 = n2.getLocation();
                    p.drawLine(p1.ix()-size_node, p1.iy()-size_node, p2.ix(), p2.iy());
                }
                p.setColor(Color.BLACK);
                if(tsp_ans!=null) {
                    path += tsp_ans.get(tsp_ans.size() -1).getKey();
                    p.drawString("The TSP path is: "+path, 100, 140);
                } else {
                    p.drawString("There is no Path that goes through all the selected nodes. ", 100, 140);
                }

                tsp_rec = false;
                tsp = false;
            }
        }

        if(custum_graph) {
            p.setColor(Color.BLACK);
            p.drawString("Make your owm Directed Graph! ", 100, 80);
            p.drawString("Click anywhere to deploy nodes, and click on 2 nodes to make an edge between them. ", 100, 100);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        if (str.equals("Save Graph")) {
            FileDialog saver = new FileDialog(this, "Save your Graph", FileDialog.SAVE);
            saver.setVisible(true);
            String filename = saver.getFile();  //file name
            String path = saver.getDirectory(); //path of the newly created file
            System.out.println(filename);
//            if(filename!=null) {                //  save the algorithms of the graph
//                algo.init(g);
//                algo.save(path + filename +".txt");
//            }
        } else if (str.equals("Load Graph")) {
            FileDialog loader = new FileDialog(this, "Load your Graph", FileDialog.LOAD);
            loader.setVisible(true);
            String filename = loader.getFile();     //filename
            String path = loader.getDirectory();    //path of the created file
//            if(filename!=null) {              // loading graph
//                algo.init(path + filename);
//                g = algo.copy();
//                repaint();
//            }

        } else if(str.equals("Is Connected")) {
            this.clearData();
            is_connected_on = true;
            algo.init(Graph);
            connected = algo.isConnected();
            repaint();
        } else if(str.equals("Shortest Path")) {
            this.clearData();
            shortest_path_on = true;
            repaint();
        } else if(str.equals("TSP")) {
            clearData();
            tsp = true;
            repaint();
        } else if(str.equals("Nem Custum DGraph")) {
            clearData();
            Graph = new DGraph();
            custum_graph = true;
            repaint();
        }

    }

    public void clearData() {

        tsp = false;
        is_connected_on = false;

        connected = false;
        tsp_rec = false;
        custum_graph = false;
        shortest_path_on = false;
        shortest_path_on = false;
        targets.clear();
    }


}
