import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

public class DrawPanel extends JPanel implements MouseListener {
    private int x, y;
    private Color color;

    public ArrayList<Point> list;

    public DrawPanel(){
        addMouseListener(this);
        list = new ArrayList<Point>();
        setBackground(Color.GRAY);
        repaint();
    }

    public void paintComponent (Graphics g){
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

    public void recreateCluster(){

    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public void setColor(Color color) {
        this.color = color;
    }
}
