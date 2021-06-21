package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FreedomTrail514 {

    public int findRotateSteps(String ring, String key) {
        if (key.length() == 0) {
            return 0;
        }

        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            int ch = ring.charAt(i) - 'a';
            if (!pos.containsKey(ch)) {
                pos.put(ch, new ArrayList<>());
            }
            pos.get(ch).add(i);
        }

        TreeMap<Integer, Integer> prev = new TreeMap<>();
        prev.put(0, 0);
        int i = 0;
        char pch = ring.charAt(0);
        if (key.charAt(0) == ring.charAt(0)) {
            i++;
        }

        for (; i < key.length(); i++) {
            if (key.charAt(i) == pch) {
                continue;
            }
            pch = key.charAt(i);
            int ch = pch - 'a';

            TreeMap<Integer, Integer> next = new TreeMap<>();
            List<Integer> cur = pos.get(ch);

            for (int j : cur) {
                int val1;
                Map.Entry<Integer, Integer> left = prev.lowerEntry(j);
                if (left == null) {
                    left = prev.lastEntry();
                    val1 = j + ring.length() - left.getKey() + left.getValue();
                } else {
                    val1 = j - left.getKey() + left.getValue();
                }

                int val2;
                Map.Entry<Integer, Integer> right = prev.higherEntry(j);
                if (right == null) {
                    right = prev.firstEntry();
                    val2 = right.getKey() + ring.length() - j + right.getValue();
                } else {
                    val2 = right.getKey() - j + right.getValue();
                }

                next.put(j, Math.min(val1, val2));
            }

            prev = next;
        }

        return key.length() + Collections.min(prev.values());

    }

    public static void main(String[] args) {
        System.out.println(new FreedomTrail514().findRotateSteps("godding", "godding"));
    }

}
