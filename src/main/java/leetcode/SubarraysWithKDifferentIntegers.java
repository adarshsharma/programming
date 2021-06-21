package leetcode;

public class SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private int atMostK(int[] arr, int k) {
        int count = 0, n = arr.length;
        int[] freq = new int[n + 1];
        int uniq = 0;
        for (int left = 0, right = 0; right < n; right++) {
            // expand the window
            freq[arr[right]]++;
            // unique element
            if (freq[arr[right]] == 1) {
                uniq++;
            }
            // contract the window
            while (uniq > k) {
                // remove the left most element
                freq[arr[left]]--;
                // update uniq
                if (freq[arr[left]] == 0) {
                    uniq--;
                }
                // move the left pointer ahead
                left++;
            }
            count += (right - left + 1);
        }
        return count;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println(new SubarraysWithKDifferentIntegers().subarraysWithKDistinct(nums, k));
    }

}
