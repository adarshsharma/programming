package leetcode;

import java.util.HashMap;
import java.util.Map;

public class CountGoodStrings1876 {

    public int countGoodSubstrings(String s) {
        if (s.length() < 3) {
            return 0;
        }

        Map<Character, Integer> mp = new HashMap<>();
        int count = 0;
        mp.merge(s.charAt(0), 1, Integer::sum);
        mp.merge(s.charAt(1), 1, Integer::sum);
        mp.merge(s.charAt(2), 1, Integer::sum);
        if (mp.size() == 3) {
            count++;
        }

        for (int i = 3; i < s.length(); i++) {
            mp.merge(s.charAt(i), 1, Integer::sum);
            mp.computeIfPresent(s.charAt(i - 3), (a, b) -> b == 1 ? null : b - 1);
            if (mp.size() == 3) {
                count++;
            }
        }

        return count;

    }

}
