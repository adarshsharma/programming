package leetcode;

public class PartitionArrayIntoDisjointIntervals915 {

    // public int partitionDisjoint(int[] nums) {
    //     int n = nums.length;
    //     int[] min = new int[n];
    //     min[n-1] = nums[n-1];
    //     for(int i=n-2;i>=0;i--){
    //         min[i] = Math.min(min[i+1], nums[i]);
    //     }
    //
    //     int mx = nums[0];
    //     for(int i=1;i<n;i++) {
    //         if(mx <= min[i]) {
    //             return i;
    //         }
    //         mx = Math.max(mx, nums[i]);
    //     }
    //     return n;   //should never reach here.
    // }

    public int partitionDisjoint(int[] nums) {
        int localMax = nums[0], partitionIdx = 0, max = localMax;
        for (int i = 1; i < nums.length; i++) {
            if (localMax > nums[i]) {
                localMax = max;
                partitionIdx = i;
            } else {
                max = Math.max(max, nums[i]);
            }
        }
        return partitionIdx + 1;
    }


    public static void main(String[] args) {
        int[] nums = {32, 57, 24, 19, 0, 24, 49, 67, 87, 87};
        System.out.println(new PartitionArrayIntoDisjointIntervals915().partitionDisjoint(nums));
    }

}
