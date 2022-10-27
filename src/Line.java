import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    private ArrayList<Edge> res;

    // Function to get lines that connect the points 
    public ArrayList<Edge> getLines(ArrayList<Point> points)
    {
        res = new ArrayList<Edge>();

        // sort points by x-coord
        Collections.sort(points,new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.getX(), o2.getX());
            }
        });

        Set<Point> pointSet = new HashSet<>();
        Point currPoint = points.get(0);
        Point nextPoint = points.get(0);
        while (pointSet.size() != points.size()) {
            pointSet.add(currPoint);
            double distance = Double.POSITIVE_INFINITY;
            for (int i = 0; i < points.size(); i++) {
                if (pointSet.contains(points.get(i)) == false) {
                    double temp = Math.hypot(currPoint.getX()-points.get(i).getX(), currPoint.getY()-points.get(i).getY());
                    if (temp < distance) {
                        distance = temp;
                        nextPoint = points.get(i);
                    }
                }
            }

            res.add(new Edge(currPoint, nextPoint));
            currPoint = nextPoint;
        }
    

       return res;
    }
}
