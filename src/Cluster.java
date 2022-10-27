
import java.awt.*;


public class Cluster {

    private class DoubleClusterExt extends DoubleCluster {

        public DoubleClusterExt(double[][] centroids, double[][] points, DoubleDistanceFunction doubleDistanceFunction) {
            super(centroids, points, doubleDistanceFunction);
        }

        public int[] getAssignments() {
            return assignments;
        }

        public int[] getCounts() {
            return counts;
        }
    }

    private static final int MIN = 0;
    private static final int MAX = 1;
    private static final int LEN = 2;

    private static final int X = 0;
    private static final int Y = 1;
    public static final int K = 2;


    private double[][] centroids = null;
    private double[][] points = null;
    private double[][] minmaxlens = null;
    private DoubleClusterExt cluster = null;

    public Cluster(){}
    public Cluster(double[][] points) {
        this.points = points;

    }

    private void paint(Graphics g, int width, int height) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        if (minmaxlens == null) {
            return;
        }
        double widthRatio = (width - 6d) / minmaxlens[LEN][X];
        double heightRatio = (height - 6d) / minmaxlens[LEN][Y];
        if (points == null) {
            return;
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < points.length; i++) {
            int px = 3 + (int) (widthRatio * (points[i][X] - minmaxlens[MIN][X]));
            int py = 3 + (int) (heightRatio * (points[i][Y] - minmaxlens[MIN][Y]));
            g.drawRect(px - 2, py - 2, 4, 4);
        }
        if (cluster == null) {
            return;
        }
        int[] assignments = cluster.getAssignments();
        int[] counts = cluster.getCounts();
        int s = 225 / centroids.length;
        for (int i = 0; i < points.length; i++) {
            int assignment = assignments[i];
            if (assignment == -1) {
                continue;
            }
            int cx = 3 + (int) (widthRatio * (centroids[assignment][X] - minmaxlens[MIN][X]));
            int cy = 3 + (int) (heightRatio * (centroids[assignment][Y] - minmaxlens[MIN][Y]));
            int px = 3 + (int) (widthRatio * (points[i][X] - minmaxlens[MIN][X]));
            int py = 3 + (int) (heightRatio * (points[i][Y] - minmaxlens[MIN][Y]));
            int c = assignment * s;
            g.setColor(new Color(c, c, c));
            g.drawLine(cx, cy, px, py);
        }
        g.setColor(Color.GREEN);
        for (int i = 0; i < centroids.length; i++) {
            int cx = 3 + (int) (widthRatio * (centroids[i][X] - minmaxlens[MIN][X]));
            int cy = 3 + (int) (heightRatio * (centroids[i][Y] - minmaxlens[MIN][Y]));
            g.drawLine(cx, cy - 2, cx, cy + 2);
            g.drawLine(cx - 2, cy, cx + 2, cy);
            int count = counts[i];
            g.drawString(String.valueOf(count), cx, cy);
        }
    }

    public int[] getAssignments(){
        return cluster.getAssignments();
    }

    public static void main(String[] args){
        new Cluster();
    }

}