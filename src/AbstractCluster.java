
import java.util.Arrays;

/**
 * Abstract Cluster is contains most the functions needed to assign
 * points to different clusters
 * k means is the algorithm implemented
 *
 * @author Ryan Smith
 * @author Tahsin Amio
 * @author Iliya Dehsarvi
 */
public class AbstractCluster<Centroid, Point> {


    public interface DistanceFunction<Centroid, Point> {

        void distance(boolean[] changed, double[][] distances, Centroid[] centroids, Point[] points);

    }

    public interface CenterFunction<Centroid, Point> {

        void center(boolean[] changed, int[] assignments, Centroid[] centroids, Point[] points);

    }

    protected final Centroid[] centroids;
    protected final Point[] points;
    protected final DistanceFunction<Centroid, Point> distanceFunction;
    protected final CenterFunction<Centroid, Point> centerFunction;

    protected final int idealCount;
    protected final double[][] distances;
    protected final int[] assignments;
    protected final boolean[] changed;
    protected final int[] counts;
    protected final boolean[] done;

    public AbstractCluster(Centroid[] centroids, Point[] points, DistanceFunction<Centroid, Point> distanceFunction, CenterFunction<Centroid, Point> centerFunction) {
        this.centroids = centroids;
        this.points = points;
        this.distanceFunction = distanceFunction;
        this.centerFunction = centerFunction;
        if (centroids.length > 0) {
            idealCount = points.length / centroids.length;
        } else {
            idealCount = 0;
        }
        distances = new double[centroids.length][points.length];
        assignments = new int[points.length];
        Arrays.fill(assignments, -1);
        changed = new boolean[centroids.length];
        Arrays.fill(changed, true);
        counts = new int[centroids.length];
        done = new boolean[centroids.length];
    }

    public int[] run() {
        return run(128);
    }

    public int[] run(int iteration) {
        calculateDistances();
        int move = makeAssignments();
        int i = 0;
        while (move > 0 && i++ < iteration) {
            if (points.length >= centroids.length) {
                move = fillEmptyCentroids();
            }
            moveCentroids();
            calculateDistances();
            move += makeAssignments();
        }
        return assignments;
    }

    protected void calculateDistances() {
        distanceFunction.distance(changed, distances, centroids, points);
        Arrays.fill(changed, false);
    }

    protected int makeAssignments() {
        int move = 0;
        Arrays.fill(counts, 0);
        for (int p = 0; p < points.length; p++) {
            int nc = nearestCentroid(p);
            if (nc == -1) {
                continue;
            }
            if (assignments[p] != nc) {
                if (assignments[p] != -1) {
                    changed[assignments[p]] = true;
                }
                changed[nc] = true;
                assignments[p] = nc;
                move++;
            }
            counts[nc]++;
            if ( counts[nc] > idealCount) {
                move += remakeAssignments(nc);
            }
        }
        return move;
    }

    protected int remakeAssignments(int cc) {
        int move = 0;
        double md = Double.POSITIVE_INFINITY;
        int nc = -1;
        int np = -1;
        for (int p = 0; p < points.length; p++) {
            if (assignments[p] != cc) {
                continue;
            }
            for (int c = 0; c < centroids.length; c++) {
                if (c == cc || done[c]) {
                    continue;
                }
                double d = distances[c][p];
                if (d < md) {
                    md = d;
                    nc = c;
                    np = p;
                }
            }
        }
        if (nc != -1 && np != -1) {
            if (assignments[np] != nc) {
                if (assignments[np] != -1) {
                    changed[assignments[np]] = true;
                }
                changed[nc] = true;
                assignments[np] = nc;
                move++;
            }
            counts[cc]--;
            counts[nc]++;
            if (counts[nc] > idealCount) {
                done[cc] = true;
                move += remakeAssignments(nc);
                done[cc] = false;
            }
        }
        return move;
    }

    protected int nearestCentroid(int p) {
        double md = Double.POSITIVE_INFINITY;
        int nc = -1;
        for (int c = 0; c < centroids.length; c++) {
            double d = distances[c][p];
            if (d < md) {
                md = d;
                nc = c;
            }
        }
        return nc;
    }

    protected int nearestPoint(int inc, int fromc) {
        double md = Double.POSITIVE_INFINITY;
        int np = -1;
        for (int p = 0; p < points.length; p++) {
            if (assignments[p] != inc) {
                continue;
            }
            double d = distances[fromc][p];
            if (d < md) {
                md = d;
                np = p;
            }
        }
        return np;
    }

    protected int largestCentroid(int except) {
        int lc = -1;
        int mc = 0;
        for (int c = 0; c < centroids.length; c++) {
            if (c == except) {
                continue;
            }
            if (counts[c] > mc) {
                lc = c;
            }
        }
        return lc;
    }

    protected int fillEmptyCentroids() {
        int move = 0;
        for (int c = 0; c < centroids.length; c++) {
            if (counts[c] == 0) {
                int lc = largestCentroid(c);
                int np = nearestPoint(lc, c);
                assignments[np] = c;
                counts[c]++;
                counts[lc]--;
                changed[c] = true;
                changed[lc] = true;
                move++;
            }
        }
        return move;
    }

    protected void moveCentroids() {
        centerFunction.center(changed, assignments, centroids, points);
    }
}

