package leetcode;

import java.util.TreeSet;

/**
 * Created by adarsh.sharma on 19/08/18.
 */
public class KEmptySlots {
    public int kEmptySlots(int[] flowers, int k) {
        if (k > flowers.length - 2) {
            return -1;
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < flowers.length; i++) {
            int pos = flowers[i];
            Integer ceil = treeSet.ceiling(pos);
            Integer floor = treeSet.floor(pos);
            if ((ceil != null && (ceil - pos) == k + 1) ||
                    (floor != null && (pos - floor) == k + 1)) {
                return i + 1;
            }

            treeSet.add(flowers[i]);
        }

        return -1;
    }

    public static void main(String[] args) {
        KEmptySlots kEmptySlots = new KEmptySlots();
        int k = 2;
        int[] flowers = {6, 5, 8, 9, 7, 1, 10, 2, 3, 4};
        System.out.println(kEmptySlots.kEmptySlots(flowers, k));
    }
}
