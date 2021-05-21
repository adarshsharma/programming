package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 28/07/18.
 */
public class MaxSumOf3SubArrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n-k+1];
        for(int i=0;i<k;i++) {
            sum[0]+=nums[i];
        }

        for(int i=1;i<=n-k;i++) {
            sum[i] = sum[i-1] - nums[i-1] + nums[i+k-1];
        }

        int[][] maxSumIdx = new int[3][n-k+1];
        int[][] maxSum = new int[3][n-k+1];
        for(int i=0;i<3;i++) {
            for(int j=0;j<n-k+1;j++) {
                maxSumIdx[i][j] = -1;
            }
        }
        maxSumIdx[0][n-k] = n-k;
        maxSum[0][n-k] = sum[n-k];
        for(int i=n-k-1;i>=0;i--) {
            if(sum[i] >= sum[maxSumIdx[0][i+1]]) {
                maxSumIdx[0][i] = i;
                maxSum[0][i] = sum[i];
            } else {
                maxSumIdx[0][i] = maxSumIdx[0][i+1];
                maxSum[0][i] = sum[maxSumIdx[0][i+1]];
            }
        }

        for(int i=1;i<3;i++) {
            maxSumIdx[i][n-i*k-k] = n-i*k-k;
            maxSum[i][n-i*k-k] = sum[n-i*k-k] + maxSum[i-1][n-i*k];
            for(int j=n-i*k-k-1;j>=0;j--) {
                if(sum[j] + maxSum[i-1][j+k] >= maxSum[i][j+1]) {
                    maxSumIdx[i][j] = j;
                    maxSum[i][j] = sum[j] + maxSum[i-1][j+k];
                } else {
                    maxSumIdx[i][j] = maxSumIdx[i][j+1];
                    maxSum[i][j] = maxSum[i][j+1];
                }
            }
        }

        int[] res = new int[3];
        res[0] = maxSumIdx[2][0];
        res[1] = maxSumIdx[1][res[0]+k];
        res[2] = maxSumIdx[0][res[1]+k];

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,6,7,5,1};
        int[] ints = new MaxSumOf3SubArrays().maxSumOfThreeSubarrays(nums, 2);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
