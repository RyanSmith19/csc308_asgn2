import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    private int x1, y1, x2, y2;
    private Color color;
    private boolean isDrawing;
    private boolean isInPanel;
    private String shape = "Rectangle";
    public Stack<Shape> shapes = new Stack<>();

    public DrawPanel(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent (Graphics g){
        if(shapes!=null){
            for (int i =0; i<shapes.size(); i++){
                shapes.get(i).drawArea(g);
            }
        }
    }

    public void mousePressed(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
    }

    public void mouseReleased(MouseEvent e){
        x2 = e.getX();
        y2 = e.getY();
        System.out.println(color);
        if(shape.equals("Rectangle")){
            shapes.push(new Rect(x1,y1,x2,y2,color));
        }
        if(shape.equals("Circle")) {
            shapes.push(new Circle(x1,y1,x2,y2,color));
        }
        if(shape.equals("Arc")) {
            shapes.push(new Arc(x1,y1,x2,y2,color));
        }
        repaint();
    }

    public void popFromStack(){
        shapes.pop();
        repaint();
    }

    public void emptyStack(){
        while(!shapes.isEmpty()) {
            shapes.pop();
        }
        repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
