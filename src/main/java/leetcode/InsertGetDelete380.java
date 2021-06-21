package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertGetDelete380 {

    List<Integer> arr;
    Map<Integer, Integer> mp;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    // public RandomizedSet() {
    public InsertGetDelete380() {
        this.arr = new ArrayList<>();
        this.mp = new HashMap<>();
        this.rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified
     * element.
     */
    public boolean insert(int val) {
        if (mp.containsKey(val)) {
            return false;
        }
        arr.add(val);
        mp.put(val, arr.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (mp.containsKey(val)) {
            int idx = mp.get(val);
            if (idx != arr.size() - 1) {
                int lastVal = arr.get(arr.size() - 1);
                mp.put(lastVal, idx);
                arr.set(idx, lastVal);
            }
            arr.remove(arr.size() - 1);
            mp.remove(val);
            return true;
        }

        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return arr.get(rand.nextInt(arr.size()));
    }

}
