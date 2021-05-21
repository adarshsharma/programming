package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by adarsh.sharma on 15/09/18.
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums.length==0) {
            return new double[0];
        }

        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minpq = new PriorityQueue<>();

        double[] result = new double[nums.length-k+1];
        for(int i=0;i<nums.length;i++) {
            if(minpq.size()+maxpq.size() == k) {
                if(!maxpq.remove(nums[i-k])) {
                    minpq.remove(nums[i-k]);
                }
            }

            maxpq.add(nums[i]);
            if(maxpq.size() - minpq.size() > 1) {
                minpq.add(maxpq.poll());
            }

            if(i-k+1>=0) {
                if(k%2==0) {
                    result[i-k+1] = ((double)maxpq.peek()+(double)minpq.peek())/2;
                } else {
                    result[i-k+1] = maxpq.peek()*1.0;
                }
            }
        }

        return result;
    }
}
