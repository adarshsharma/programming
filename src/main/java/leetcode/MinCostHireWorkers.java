package leetcode;

/**
 * Created by adarsh.sharma on 12/09/18.
 */
public class MinCostHireWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        double[][] A = new double[n][K+1]; //cost per quality
        int[][] Q = new int[n][K+1];   //total quality hired

        A[0][1] = (double)wage[0]/(double)quality[0];
        Q[0][1] = quality[0];
        for(int i=1;i<n;i++) {
            for(int j=1;j<=Math.min(K, i+1);j++) {
                double a = (double)wage[i]/(double)quality[i];
                if(j>1 && A[i-1][j-1]*quality[i]>=wage[i]) {
                    a = A[i-1][j-1];
                }
                int includeQ = quality[i];
                if(j>1) {
                    includeQ+=Q[i-1][j-1];
                }

                if(j==i+1 || a*includeQ < A[i-1][j]*Q[i-1][j]) {
                    A[i][j] = a;
                    Q[i][j] = includeQ;
                } else {
                    A[i][j] = A[i-1][j];
                    Q[i][j] = Q[i-1][j];
                }
            }
        }

        return A[n-1][K]*Q[n-1][K];
    }
}
