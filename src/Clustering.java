import java.awt.*;
import java.util.ArrayList;

public class Clustering {
    private int x,j,k =2;
    private ArrayList<int[]> cluster1;
    private ArrayList<int[]> cluster2;
    float mean1[][] = new float[1][2];
    float mean2[][] = new float[1][2];
    float temp1[][] = new float[1][2];
    float temp2[][] = new float[1][2];
    int sum11 = 0, sum12 = 0, sum21 = 0, sum22 = 0;
    double dist1, dist2;
    int i1 = 0, i2 = 0, itr = 0;

    public Clustering(){
        cluster1 = new ArrayList<>();
        cluster2 = new ArrayList<>();

        mean1[0][0] = 18;
        mean1[0][1] = 10;
        mean2[0][0] = 20;
        mean2[0][1] = 80;
    }

    public void resetCluster(){
        /*while(!Arrays.deepEquals(mean1, temp1) || !Arrays.deepEquals(mean2, temp2)) {

            //Empty the partitions
            for(x=0;x<cluster1.size();x++) {
                cluster1.get(x)[0] = 0;
                cluster1.get(x)[1] = 0;
                cluster2.get(x)[0] = 0;
                cluster2.get(x)[1] = 0;
            }

            i1 = 0; i2 = 0;

            //Find the distance between mean and the data point and store it in its corresponding partition
            for(x=0;x<10;x++) {
                dist1 = Math.sqrt(Math.pow(res[x][0] - mean1[0][0],2) + Math.pow(res[x][1] - mean1[0][1],2));
                dist2 = Math.sqrt(Math.pow(res[x][0] - mean2[0][0],2) + Math.pow(res[x][1] - mean2[0][1],2));

                if(dist1 < dist2) {
                    cluster1.get(i1)[0] = res[x][0];
                    cluster1.get(i1)[1] = res[x][1];

                    i1++;
                }
                else {
                    cluster2.get(i2)[0] = res[x][0];
                    cluster2.get(i2)[1] = res[x][1];

                    i2++;
                }
            }


            temp1[0][0] = mean1[0][0];
            temp1[0][1] = mean1[0][1];
            temp2[0][0] = mean2[0][0];
            temp2[0][1] = mean2[0][1];

            //Find the new mean for new partitions
            sum11 = 0; sum12 = 0; sum21 = 0; sum22 = 0;

            for(x=0;x<i1;x++) {
                sum11 += cluster1.get(x)[0];
                sum12 += cluster1.get(x)[1];
            }
            for(x=0;x<i2;x++) {
                sum21 += cluster2.get(x)[0];
                sum22 += cluster2.get(x)[1];
            }
            mean1[0][0] = (float)sum11/i1;
            mean1[0][1] = (float)sum12/i1;
            mean2[0][0] = (float)sum21/i2;
            mean2[0][1] = (float)sum22/i2;

            itr++;
        }
        */
    }



}
