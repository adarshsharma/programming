package leetcode;

/**
 * Created by adarsh.sharma on 23/08/18.
 */
public class FindMaxAverage {
//    public double findMaxAverage(int[] nums, int k) {
//        int n = nums.length;
//        double[] A = new double[n];
//        int[] N = new int[n];
//
//        int sum = 0;
//        for(int i=0;i<k;i++) {
//            sum+=nums[i];
//        }
//        A[k-1] = (sum*1.0)/k;
//        N[k-1] = k;
//        double maxAverage = A[k-1];
//
//        for(int i=k;i<n;i++) {
//            A[i] = (A[i-1]*N[i-1] + nums[i])/(N[i-1] + 1);
//            double avg = A[i];
//            int j=i-N[i-1];
//            int minJ = j;
//            while(j<=i-k) {
//                A[i] = (A[i]*(i-j+1) - nums[j])/(i-j);
//                if(avg < A[i]) {
//                    avg = A[i];
//                    minJ = j+1;
//                }
//                j++;
//            }
//            A[i] = avg;
//            N[i] = i-minJ+1;
//            maxAverage = Math.max(maxAverage, A[i]);
//        }
//
//        return maxAverage;
//    }

    public double findMaxAverage(int[] nums, int k) {
        double max_val = Integer.MIN_VALUE;
        double min_val = Integer.MAX_VALUE;
        for (int n: nums) {
            max_val = Math.max(max_val, n);
            min_val = Math.min(min_val, n);
        }
        double prev_mid = max_val, error = Integer.MAX_VALUE;
        while (error > 0.00001) {
            double mid = (max_val + min_val) * 0.5;
            if (check(nums, mid, k))
                min_val = mid;
            else
                max_val = mid;
            error = Math.abs(prev_mid - mid);
            prev_mid = mid;
        }
        return min_val;
    }
    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - mid;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prev += nums[i - k] - mid;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FindMaxAverage findMaxAverage = new FindMaxAverage();
        int k = 4;
        int[] nums = {1, 12, -5, -6, 50, 3};
        System.out.println(findMaxAverage.findMaxAverage(nums, k));
    }
}
