package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 30/07/18.
 */
//With Duplicates
public class Permutations {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

//    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> A) {
//        Collections.sort(A);
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//        permute(result, A, 0, A.size() - 1);
//        return result;
//    }
//
//    private void permute(ArrayList<ArrayList<Integer>> result,
//                         ArrayList<Integer> A, int left, int right) {
//        if (left == right) {
//            ArrayList<Integer> a = new ArrayList<>(A);
//            result.add(a);
//        }
//        Set<Integer> used = new HashSet<>();
//        for (int i = left; i <= right; i++) {
//            if (!used.contains(A.get(i))) {
//                if (i == left || !A.get(i).equals(A.get(i - 1))) {
//                    used.add(A.get(i));
//                    swap(A, left, i);
//                    permute(result, A, left + 1, right);
//                    swap(A, i, left);
//                }
//            }
//        }
//
//    }

    private void swap(ArrayList<Integer> A, int left, int right) {
        if (left == right) {
            return;
        }
        int temp = A.get(left);
        A.set(left, A.get(right));
        A.set(right, temp);
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 9};
//        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 9));
//        new Permutations().permuteUnique(nums).forEach(System.out::println);
        System.out.println(new Permutations().permuteUnique(nums).size());
    }
}
