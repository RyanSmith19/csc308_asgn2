
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


    public Cluster(double[][] points) {
        this.points = points;
        minmaxlens = new double[][]{
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY},
                {0d, 0d}
        };

        for(double[] p: points){
            if (p[X] < minmaxlens[MIN][X]) {
                minmaxlens[MIN][X] = p[X];
            }
            if (p[Y] < minmaxlens[MIN][Y]) {
                minmaxlens[MIN][Y] = p[Y];
            }
            if (p[X] > minmaxlens[MAX][X]) {
                minmaxlens[MAX][X] = p[X];
            }
            if (p[Y] > minmaxlens[MAX][Y]) {
                minmaxlens[MAX][Y] = p[Y];
            }
        }
        minmaxlens[LEN][X] = minmaxlens[MAX][X] - minmaxlens[MIN][X];
        minmaxlens[LEN][Y] = minmaxlens[MAX][Y] - minmaxlens[MIN][Y];
        centroids = new double[K][2];
        for (int i = 0; i < K; i++) {
            centroids[i][X] = minmaxlens[MIN][X] + (minmaxlens[LEN][X] / 2d);
            centroids[i][Y] = minmaxlens[MIN][Y] + (minmaxlens[LEN][Y] / 2d);
        }
        cluster = new DoubleClusterExt(centroids, points, DoubleCluster.EUCLIDEAN_DISTANCE_FUNCTION);
        int move = cluster.makeAssignments();
        while(move != 0){
            move = cluster.remakeAssignments(move);
        }
    }

    public int[] getAssignments2(){
        return cluster.getAssignments();
    }

}