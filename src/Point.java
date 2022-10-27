import java.awt.*;

/**
 * Our own Point class implementation so that the point has a Color instance
 *
 * @author Ryan Smith
 * @author Tahsin Amio
 * @author Iliya Dehsarvi
 */
public class Point {
    private int x, y;
    private Color c;

    public Point(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public void drawPoint(Graphics g){
        g.setColor(c);
        g.fillOval(x,y,5,5);
    }

    public Color getC() {
        return c;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setC(Color c) {
        this.c = c;
    }
}
