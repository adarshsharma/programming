package leetcode;

public class SingleNumberII137 {

    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            ans |= sum << i;

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -2, 1, 1, 4, 1, 4, 4, -4, -2};
        System.out.println(new SingleNumberII137().singleNumber(nums));
    }

}
