package utils;//import utils.StdDraw;

import dataStructure.DGraph;

public class gui {

    public static void main(String[] args) {
       // initGraph_menuUP();
    }

    public static void initGraph_menuDOWN(){
        final int width_window = 800;
        final int height_window = 800;
        StdDraw.setCanvasSize(width_window, height_window);
        StdDraw.setXscale(0, width_window);
        StdDraw.setYscale(0,height_window);

        int BAR_W = width_window/2 -5;           //Bar half Width
        int BAR_H = 50;                          //Bar half height

        ///////////////////////////Button Bar
        StdDraw.setPenColor(255,0,0);
        StdDraw.rectangle(width_window/2, height_window/12, BAR_W, BAR_H);

        //Button 1
        int first_button_x = width_window/12;

        StdDraw.text(first_button_x, 60, "Button 1");

        //Button 2
        StdDraw.text(first_button_x*2.2  , 60, "Button 2");
        //Button 3
        StdDraw.text(first_button_x*3.4 , 60, "Button 3");
        //Button 4
        StdDraw.text(first_button_x*4.6 , 60, "Button 4");

        //////////////////////////////////Status bar
        StdDraw.setPenColor(0,0,255);
        StdDraw.rectangle(width_window/2, height_window/5.5, BAR_W, BAR_H/2);
        StdDraw.text(width_window/2, 2.8*BAR_H,"DATA:  Number of Nodes : 123 ,Number of Edges : 3232 Dean & Elon (c) " );

        ///////////////////////////////// graph GUI


    }
    public static void initGraph_menuUP(){
        final int width_window = 800;
        final int height_window = 800;
        StdDraw.setCanvasSize(width_window, height_window);
        StdDraw.setXscale(0, width_window);
        StdDraw.setYscale(0, height_window);

        int BAR_W = width_window/2 -5;                          //Bar half Width
        int BAR_H =  30;                                        //Bar half height

        ///////////////////////////Button Bar
        StdDraw.setPenColor(255,0,0);
        StdDraw.rectangle(width_window/2, (height_window*0.95), BAR_W, BAR_H);

        //Button 1
        int first_button_x = width_window/12;
        int height_of_button = height_window - 30;

        StdDraw.text(first_button_x, height_of_button, "Add Node");

        //Button 2
        double second_button_x = first_button_x+90;
        StdDraw.text( second_button_x , height_of_button, "Add Edge");

        //Button 3
        double third_button_x = second_button_x +100;
        StdDraw.text(third_button_x , height_of_button, "Remove Node");
        //Button 4
        double fourth_button_x = third_button_x +120;
        StdDraw.text(fourth_button_x , height_of_button, "Remove Edge");
        //Button 5
        double fifth_button_x = fourth_button_x +100;
        StdDraw.text(fifth_button_x, height_of_button, "Connect");
        //Button 6
        double sixth_button_x = fifth_button_x+100;
        StdDraw.text(sixth_button_x, height_of_button, "Exit");
        //Button pixel array
        double[] buttonArr = {first_button_x, second_button_x, third_button_x, fourth_button_x,fifth_button_x, sixth_button_x};

        //////////////////////////////////Status bar
        StdDraw.setPenColor(0,0,255);
        double heightStatusBar = height_window*0.887;
        StdDraw.rectangle(width_window/2, heightStatusBar , BAR_W, BAR_H/2);
        StdDraw.text(width_window/2, heightStatusBar,"DATA:  Number of Nodes : 123 ,Number of Edges : 3232 Dean & Elon (c) " );

        boolean running = true;
        while (running){
            if (StdDraw.isMousePressed() && StdDraw.mouseX() - first_button_x <= 10 && StdDraw.mouseY() -height_of_button <=10 ){
                System.out.println("Pressed button 1!!!!");
                continue;
            }
            if (StdDraw.isMousePressed() && StdDraw.mouseX() - second_button_x <= 10 && StdDraw.mouseY() -height_of_button <=10){
                System.out.println("Second button is pressed!!!");
            }
            if (StdDraw.isMousePressed() && StdDraw.mouseX() - third_button_x <= 10 && StdDraw.mouseY() -height_of_button <=10){
                System.out.println("Third button is pressed!!!");
            }
            if (StdDraw.isMousePressed() && StdDraw.mouseX() - fourth_button_x <= 10 && StdDraw.mouseY() -height_of_button <=10){
                System.out.println("Fourth button is pressed!!!");
            }

        }




    }

    /**
     * Private method used by init. The method receives a graph, and the dimensions
     * in which the graph is to be viewed within the window created in init()
     * @param graph - graph to be showed
     * @param height_graph_window - Height of graph area, within pixels created in init function
     * @param width_graph_window - Width of graph area, within pixels created in init function
     */
    private void displayGraph(DGraph graph, int height_graph_window, int width_graph_window){
        // this function creates the graph in a window that has been initialized in init() function
        for(int i = 0 ; i <graph.nodeSize(); i++){
            // for each Node, create that point in the graph.
            double x = graph.connectivity.get(i).getLocation().x();
            double y = graph.connectivity.get(i).getLocation().y();
            StdDraw.point(x,y);
        }
    }












    public static void Triangle(){
        int T = 10000;
        double [] cx = {0, 1, 0.5};
        double [] cy = {0 ,0 ,0.866};

        double x = 0, y = 0;
        for (int t = 0; t< T; t++){
            int r = (int) (Math.random()*3);
            x = (x+cx[r])/2;
            y = (y+cy[r])/2;
            switch (r){
                case 0: StdDraw.setPenColor(0,0,255);break;
                case 1: StdDraw.setPenColor(0,255,0);break;
                case 2: StdDraw.setPenColor(255,0,0);break;
            }
            StdDraw.point(x,y);
        }
    }
    public static void Leaf(){
        int n = 1000000;                                //Number of points to draw
        //StdDraw.setScale(-0.1,0.1);                     //Leave a 10% border
        //StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);         //Background color
        StdDraw.setPenColor(0,255,0);    //Shade of Green

        //Starting point
        double x = 100;
        double y = -2;

        // repeated choose one of four update rules at random
        for (int i = 0; i < n; i++) {
            double tempx, tempy;
            double r = Math.random();

            // stem
            if (r <= 0.01)  {
                tempx = 0.50;
                tempy = 0.16 * y;
            }

            // largest left-hand leaflet
            else if (r <= 0.08) {
                tempx =  0.20 * x - 0.26 * y + 0.400;
                tempy =  0.23 * x + 0.22 * y - 0.045;
            }

            // largest right-hand leaflet
            else if (r <= 0.15) {
                tempx = -0.15 * x + 0.28 * y + 0.575;
                tempy =  0.26 * x + 0.24 * y - 0.086;
            }

            // successively smaller leaflets
            else {
                tempx =  0.85 * x + 0.04 * y + 0.075;
                tempy = -0.04 * x + 0.85 * y + 0.180;
            }

            // update (x, y) and draw point
            x = tempx;
            y = tempy;
            StdDraw.point(x, y);
        }
    }

}
