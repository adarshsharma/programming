package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinNumberOfDaysToEachOranges1553 {
    Map<Integer, Integer> mp;
    public int minDays(int n) {
        mp = new HashMap<>();
        mp.put(1,1);
        mp.put(2,2);
        mp.put(3,2);
        return minD(n);
    }

    private int minD(int n) {
        if(mp.containsKey(n)) {
            return mp.get(n);
        }

        if(n%2 != 0 && n%3 != 0) {
            int val = 1 + minD(n-1);
            mp.put(n, val);
            return val;
        } else if(n%2 == 0 && n%3 == 0) {
            int val1 = minD(n/2);
            int val2 = minD(n/3);
            mp.put(n, 1 + Math.min(val1, val2));
            return mp.get(n);
        } else if(n%3 == 0) {
            int val = minD(n/3);
            mp.put(n, 1 + val);
            return val;
        } else {
            int val = minD(n/2);
            mp.put(n, 1 + val);
            return val;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinNumberOfDaysToEachOranges1553().minDays(10));
    }

}
