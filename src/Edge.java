import java.awt.*;

public class Edge {
      Point first;
      Point second;

      public Edge(Point first, Point second) {
          this.first = first;
          this.second = second;
      }

      public void drawEdge(Graphics g){
        g.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
      }

      Point getFirst() { return first; }

      Point getSecond() { return second; }

      @Override
      public boolean equals(Object obj) {
        if (obj == null) return false;
        
        final Edge other = (Edge) obj;
        if (other.first.getX() == this.first.getX() && other.first.getY() == this.first.getY() && other.second.getX() == this.second.getX() && other.second.getY() == this.second.getY()) {
            return true;
        }

        if (other.first.getX() == this.second.getX() && other.first.getY() == this.second.getY() && other.second.getX() == this.first.getX() && other.second.getY() == this.first.getY()) {
            return true;
        }

        return false;

      } 

      @Override
      public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.first.getX() + this.first.getY() + this.second.getX() + this.second.getY();
      
        return hash;
      }

}
