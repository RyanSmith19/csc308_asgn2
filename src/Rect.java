import java.awt.*;

public class Rect extends Shape {

    public Rect(int x1,int y1, int x2, int y2, Color color){
        super(x1,y1,x2,y2,color);
    }

    public void drawArea(Graphics g) {
        g.setColor(color);
        g.fillRect(x1,y1,x2-x1,y2-y1);
    }
}
