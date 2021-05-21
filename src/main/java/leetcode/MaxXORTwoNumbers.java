package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adarsh.sharma on 13/08/18.
 */
public class MaxXORTwoNumbers {
//    public int findMaximumXOR(int[] nums) {
//        int bits = 32 - 2;
//        int result = 0;
//
//        Set<Integer> numSet = new HashSet<>();
//        for (int num : nums) {
//            numSet.add(num);
//        }
//
//        List<Set<Integer>> divided = getSet(numSet, bits);
//
//        while (bits >= 0 && (divided.get(0).isEmpty() || divided.get(1).isEmpty())) {
//            bits--;
//            divided = getSet(numSet, bits);
//        }
//        if (bits == 0) {
//            return result;
//        }
//
//        List<Set<Integer>> left = new ArrayList<>();
//        left.add(divided.get(0));
//        left.add(new HashSet<>());
//        List<Set<Integer>> right = new ArrayList<>();
//        right.add(new HashSet<>());
//        right.add(divided.get(1));
//
//        while (bits >= 0) {
//
//            Set<Integer> leftSet = new HashSet<>();
//            Set<Integer> rightSet = new HashSet<>();
//
//            if ((!left.get(0).isEmpty() && !right.get(1).isEmpty()) || (!left.get(1).isEmpty() && !right.get(0).isEmpty())) {
//                result += (1 << bits);
//                bits--;
//
//                if (!right.get(0).isEmpty()) {
//                    leftSet.addAll(left.get(1));
//                }
//                if (!right.get(1).isEmpty()) {
//                    leftSet.addAll(left.get(0));
//                }
//
//                if (!left.get(0).isEmpty()) {
//                    rightSet.addAll(right.get(1));
//                }
//                if (!left.get(1).isEmpty()) {
//                    rightSet.addAll(right.get(0));
//                }
//
//                left = getSet(leftSet, bits);
//                right = getSet(rightSet, bits);
//            } else {
//                bits--;
//                if (!right.get(0).isEmpty()) {
//                    leftSet.addAll(left.get(0));
//                }
//                if (!right.get(1).isEmpty()) {
//                    leftSet.addAll(left.get(1));
//                }
//
//                if (!left.get(0).isEmpty()) {
//                    rightSet.addAll(right.get(0));
//                }
//                if (!left.get(1).isEmpty()) {
//                    rightSet.addAll(right.get(1));
//                }
//                left = getSet(leftSet, bits);
//                right = getSet(rightSet, bits);
//            }
//
//        }
//
//        return result;
//    }
//
//    private List<Set<Integer>> getSet(Set<Integer> set, int i) {
//        List<Set<Integer>> divided = new ArrayList<>();
//        divided.add(new HashSet<>());
//        divided.add(new HashSet<>());
//        for (int num : set) {
//            if ((num & (1 << i)) > 0) {
//                divided.get(1).add(num);
//            } else {
//                divided.get(0).add(num);
//            }
//        }
//
//        return divided;
//    }

    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 30; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        Set<Integer>[][] s = new Set[2][3];
        return max;
    }

    public static void main(String[] args) {
        MaxXORTwoNumbers maxXORTwoNumbers = new MaxXORTwoNumbers();
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(maxXORTwoNumbers.findMaximumXOR(nums));
    }

}
