package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteRandomDuplicates381 {

    List<Integer> arr;
    Map<Integer, LinkedHashSet<Integer>> mp;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    // public RandomizedCollection() {
    public InsertDeleteRandomDuplicates381() {
        this.arr = new ArrayList<>();
        this.mp = new HashMap<>();
        this.rand = new Random();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the
     * specified element.
     */
    public boolean insert(int val) {
        if (!mp.containsKey(val)) {
            mp.put(val, new LinkedHashSet<>());
        }
        LinkedHashSet<Integer> set = mp.get(val);
        arr.add(val);
        set.add(arr.size() - 1);

        return set.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (mp.containsKey(val)) {
            LinkedHashSet<Integer> set = mp.get(val);
            int idx = set.iterator().next();
            set.remove(idx);
            if (idx != arr.size() - 1) {
                int lastVal = arr.get(arr.size() - 1);
                mp.get(lastVal).remove(arr.size() - 1);
                mp.get(lastVal).add(idx);
                arr.set(idx, lastVal);
            }
            arr.remove(arr.size() - 1);
            if (set.size() == 0) {
                mp.remove(val);
            }
            return true;
        }

        return false;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return arr.get(rand.nextInt(arr.size()));
    }

    public static void main(String[] args) {
        InsertDeleteRandomDuplicates381 obj = new InsertDeleteRandomDuplicates381();
        System.out.println(obj.insert(1));
        System.out.println(obj.insert(1));
    }

}
