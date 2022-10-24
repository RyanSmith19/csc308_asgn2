import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    private Color color;
    private ArrayList<Edge> res;

    // Function to get lines that connect the points 
    public ArrayList<Edge> getLines(ArrayList<Point> points)
    {
        Set<Edge> set = new HashSet<Edge> (); 
        res = new ArrayList<Edge>();
        double distance;

        // sort points by x-coord
        Collections.sort(points,new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.getX(), o2.getX());
            }
        });


        for (int i = 0; i < points.size(); i++) {
           double minDist = Double.POSITIVE_INFINITY;
           Edge edge1 = new Edge(points.get(i), points.get(i));
           Edge edge2 = new Edge(points.get(i), points.get(i));
           for (int j = 0; j < points.size(); j++) {    
               if (i != j) {
                    distance = Math.hypot(points.get(i).getX()-points.get(j).getX(), points.get(i).getY()-points.get(j).getY());
                    
                    if (distance < minDist) {
                        minDist = distance;
                        Point x1 = points.get(i);
                        Point x2 = points.get(j);
                        edge1 = new Edge(x1, x2);
                        edge2 = new Edge(x2, x1);
                        set.add(edge1);
                        set.add(edge2);
                    }
               }
           }

           res.add(edge1);  
       }
      
       System.out.print("\n");
       int x = res.get(0).getFX();
       System.out.print(x);
       return res;
    }

    class Edge<Point, Point> {
        Point first;
        Point second;

        public Edge(Point first, Point second) {
            this.first = first;
            this.second = second;
        }

        Point getFirst() { return first; }

        Point getSecond() { return second; }

        int getFX() { return first.getX(); }

    }
}
