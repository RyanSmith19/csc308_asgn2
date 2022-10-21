import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

public class DrawPanel extends JPanel implements MouseListener {
    private int x1, y1;
    private Color color;

    public ArrayList<Point> list;

    public DrawPanel(){
        addMouseListener(this);
        list = new ArrayList<Point>();
    }

    public void paintComponent (Graphics g){
        if(list!=null){
            for (int i =0; i< list.size(); i++){
                list.get(i);
            }
        }
    }

    public void mousePressed(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
    }

    public void mouseReleased(MouseEvent e){

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
