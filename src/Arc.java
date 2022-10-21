import java.awt.*;

public class Arc extends Shape{
    public Arc(int x1,int y1, int x2, int y2, Color color){
        super(x1,y1,x2,y2,color);
    }
    public void drawArea(Graphics g) {
        double dy = y2-y1;
        double dx = x2-x1;
        double anglelnRadians = Math.atan2(-dy, dx);
        double length = Math.toDegrees(anglelnRadians);
        g.fillArc(x1, y1, x2-x1, y2-y1, 0, (int) length);
    }
}
