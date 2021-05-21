package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by adarsh.sharma on 20/07/18.
 */
public class FourSum {

    public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> A, int target) {
        Collections.sort(A);
        Integer[] num = new Integer[A.size()];
        num = A.toArray(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i].equals(num[i - 1])) {
                continue;
            }
            for (int j = i + 1; j < num.length; j++) {
                if (j > i + 1 && num[j].equals(num[j - 1])) {
                    continue;
                }
                int k = j + 1;
                int l = num.length - 1;

                while (k < l) {
                    int sum = num[i] + num[j] + num[k] + num[l];

                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else if (sum == target) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[k]);
                        temp.add(num[l]);

                        result.add(temp);
                        int prev = num[k];
                        while (k < num.length && num[k].equals(prev)) {
                            k++;
                        }
                        prev = num[l];
                        while (l > k && num[l].equals(prev)) {
                            l--;
                        }
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> r = new ArrayList<>(Collections.nCopies(4, 0));
        int iPrev = -1;
        for (int i = 0; i <= n - 4; i++) {
            if (iPrev == -1 || nums[i] != nums[iPrev]) {
                iPrev = i;
                int jPrev = -1;
                r.set(0, nums[i]);
                for (int j = i + 1; j <= n - 3; j++) {
                    if (jPrev == -1 || nums[j] != nums[jPrev]) {
                        jPrev = j;
                        r.set(1, nums[j]);
                        int s = target - nums[i] - nums[j];
                        int left = j + 1;
                        int right = n - 1;
                        while (left < right) {
                            if (nums[left] + nums[right] == s) {
                                r.set(2, nums[left++]);
                                r.set(3, nums[right--]);
                                result.add(new ArrayList<>(r));
                                while (left < right && nums[left] == nums[left - 1]) {
                                    left++;
                                }
                                while (left < right && nums[left] == nums[left - 1]) {
                                    right--;
                                }
                            } else if (nums[left] + nums[right] < s) {
                                left++;
                            } else {
                                right--;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.addAll(Arrays.asList(-1,-5,-5,-3,2,5,0,4));
        System.out.println(new FourSum().fourSum(A, -7));
        System.out.println(new FourSum().fourSum(new int[]{-1, -5, -5, -3, 2, 5, 0, 4}, -7));
    }
}
