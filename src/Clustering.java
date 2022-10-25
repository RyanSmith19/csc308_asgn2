import java.awt.*;
import java.util.ArrayList;

public class Clustering {




    public void initData(){

    }

    private double[][] findColMinMax(ArrayList<Double[]> items){
        int n = items.get(0).length;
        double min[] = new double[n];
        double max[] = new double[n];
        for(int i = 0; i < n; i++){
            min[i] = Double.MAX_VALUE;
            max[i] = Double.MIN_VALUE;
        }

        for(int i = 0; i < items.size(); i++){
            for(int j = 0; j < items.get(i).length; j++){
                if(items.get(i)[j] < min[j]){
                    min[j] = items.get(i)[j];
                }
                if(items.get(i)[j] > min[j]){
                    max[j] = items.get(i)[j];
                }
            }
        }
        double result[][] = new double[2][min.length];
        result[0] = min;
        result[1] = max;
        return result;
    }

    private double[][] initMeans(ArrayList<Double[]> items, double[] cMin, double[] cMax){
        double[][] means = new double[items.get(0).length][2];
        for( int i = 0; i < means.length; i++){
            for (int j = 0; j < means[i].length; j++){
                means[i][j] = Math.random()*(cMax[j]-1-cMin[j]+1)+cMin[j]+1;
            }
        }
        return means;
    }

    private double[] updateMean(int n, double[] mean, double[] item){
        double m = mean[0];
        for(int i = 0; i < mean.length; i++){
            m = (mean[i]*(n-1)+item[i])/((double) n);
            mean[i] = (double) Math.round(m * 1000d)/1000d;
        }
        return mean;
    }

    private double euclideanDistance(double[] x, double[] y){
        double sum = 0.0;
        for(int i = 0; i < x.length; i++){
            sum += Math.pow(x[i]-y[i], 2);
        }
        return sum;
    }

    private int classify(double[][] means, double[] item){
        double min = Double.MIN_VALUE;
        int idx = -1;
        double dis;
        for(int i = 0; i < means.length; i++){
            dis = euclideanDistance(item, means[i]);
            if(dis < min){
                min = dis;
                idx = i;
            }
        }
        return idx;
    }

    private double[][] calculateMeans(ArrayList<Double[]> items){
        int maxIter = 100000;
        double[][] colMinMax = findColMinMax(items);
        double[] cMin = colMinMax[0];
        double[] cMax = colMinMax[1];
        // double[] means = initMeans(items, cMin, cMax);

        double[] clusterSizes = new double[1];
        return colMinMax;

    }

    // private ArrayList<Double[]> findClusters(double[][] means, ArrayList<Double[]> items){
        // ArrayList<Double[]> clusters
    // }

}
