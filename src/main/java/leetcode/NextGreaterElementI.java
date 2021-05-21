package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 21/08/18.
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> idxMp = new HashMap<>();
        for(int i=0;i<nums1.length;i++) {
            idxMp.put(nums1[i], i);
        }

        int[] result = new int[nums1.length];

        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=nums2.length-1;i>=0;i--) {
            while(!stack.isEmpty() && nums2[i] > stack.peekLast()) {
                stack.removeLast();
            }

            if(idxMp.containsKey(nums2[i])) {
                if(!stack.isEmpty()) {
                    result[idxMp.get(nums2[i])] = stack.peekLast();
                } else {
                    result[idxMp.get(nums2[i])] = -1;
                }
            }
            stack.addLast(nums2[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElementI next = new NextGreaterElementI();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] ints = next.nextGreaterElement(nums1, nums2);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
