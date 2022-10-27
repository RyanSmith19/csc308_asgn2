import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

public class DrawPanel extends JPanel implements MouseListener {
    private int x, y;

    private double[][] points = null;

    private static final int X = 0;
    private static final int Y = 1;

    public ArrayList<Point> list;
    public ArrayList<Edge> lines;
    public Cluster cluster;

    public DrawPanel(){
        addMouseListener(this);
        list = new ArrayList<Point>();
        setBackground(Color.GRAY);
        repaint();
    }

    public void paintComponent (Graphics g){
        if(lines!=null){
            for (Edge e: lines){
                e.drawEdge(g);
            }
        }

        if(list!=null){
            for (Point p: list){
                p.drawPoint(g);
            }
        }
    }

    public void mousePressed(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    public void mouseReleased(MouseEvent e){
        list.add(new Point(x, y, color));
        repaint();
    }

    public void drawLine() {
        Line line = new Line();
        lines = line.getLines(list);
        repaint();
    }

    public void orderCluster(){
        if(list!=null){
            points = new double[list.size()][2];
            for (int i = 0; i < list.size(); i++){
                points[i][X] = list.get(i).getX();
                points[i][Y] = list.get(i).getY();
            }
            cluster = new Cluster(points);
            cluster.getAssignments();

        }

    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

}
