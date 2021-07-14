package leetcode;

import java.util.Arrays;

public class FindFirstAndLastPosition34 {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int first = findFirst(nums, target);
        if (first < 0) {
            res[0] = -1;
            res[1] = -1;
        } else {
            res[0] = first;
            res[1] = findLast(nums, target);
        }

        return res;
    }

    private int findFirst(int[] nums, int num) {
        int s = 0;
        int e = nums.length;
        int first = Arrays.binarySearch(nums, s, e, num);
        int found = first;
        e = found;
        while (found >= 0) {
            found = Arrays.binarySearch(nums, s, e, num);
            if (found >= 0) {
                first = found;
                e = found;
            }
        }
        return first;
    }

    private int findLast(int[] nums, int num) {
        int s = 0;
        int e = nums.length;
        int last = Arrays.binarySearch(nums, s, e, num);
        int found = last;
        s = found + 1;
        while (found >= 0) {
            found = Arrays.binarySearch(nums, s, e, num);
            if (found >= 0) {
                last = found;
                s = found + 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] res = new FindFirstAndLastPosition34().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        Arrays.stream(res).forEach(System.out::println);
    }

}
