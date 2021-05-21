package leetcode;

/**
 * Created by adarsh.sharma on 23/08/18.
 */
public class MinimizeMaxDistanceToGasStations {

//    public double minmaxGasDist(int[] stations, int K) {
//        int N = stations.length;
//        double[] deltas = new double[N-1];
//        for (int i = 0; i < N-1; ++i)
//            deltas[i] = stations[i+1] - stations[i];
//
//        int[] count = new int[N-1];
//        Arrays.fill(count, 1);
//
//        for (int k = 0; k < K; ++k) {
//            // Find interval with largest part
//            int best = 0;
//            for (int i = 0; i < N-1; ++i)
//                if (deltas[i] / count[i] > deltas[best] / count[best])
//                    best = i;
//
//            // Add gas station to best interval
//            count[best]++;
//        }
//
//        double ans = 0;
//        for (int i = 0; i < N-1; ++i)
//            ans = Math.max(ans, deltas[i] / count[i]);
//
//        return ans;
//    }

    public double minmaxGasDist(int[] stations, int K) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < stations.length; i++) {
            max = Math.max(max, stations[i] - stations[i - 1]);
        }

        double low = 0.0;
        double high = max * 1.0;
        double min = Double.MAX_VALUE;
        while ((high - low) > 1e-6) {
            double mid = (low + high) / 2;
            int k = getNewStations(stations, K, mid);
            if (k <= K) {
                min = Math.min(min, mid);
                high = mid;
            } else {
                low = mid;
            }
        }
        return min;
    }

    private int getNewStations(int[] stations, int K, double mid) {
        int k = 0;
        for (int i = 1; i < stations.length; i++) {
            double diff = (stations[i] - stations[i - 1]) * 1.0;
            k += new Double(Math.ceil((diff / mid - 1))).intValue();
        }
        return k;
    }

    public static void main(String[] args) {
        MinimizeMaxDistanceToGasStations m = new MinimizeMaxDistanceToGasStations();
//        int K = 9;
//        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] stations = {10, 19, 25, 27, 56, 63, 70, 87, 96, 97};
        int K = 3;
        System.out.println(m.minmaxGasDist(stations, K));
    }
}
