package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 02/09/18.
 */
public class NumberOfArithmeticSlicesII {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        Map<Long, Map<Integer, Integer>>[] mapArr = new Map[A.length];

        for (int i = 0; i < A.length; i++) {
            mapArr[i] = new HashMap<>();
            for (int j = i - 1; j >= 0; j--) {
                long diff = (long) A[i] - (long) A[j];
                Map<Integer, Integer> prev = mapArr[j].getOrDefault(diff, new HashMap<>());
                Map<Integer, Integer> cur = mapArr[i].computeIfAbsent(diff, k -> new HashMap<>());

                cur.merge(2, 1, Integer::sum);

                for (Integer len : prev.keySet()) {
                    cur.merge(len + 1, prev.get(len), Integer::sum);
                    count += prev.get(len);
                }
            }
        }
//        Arrays.stream(mapArr).forEach(System.out::println);
        return count;
    }

    public int numberOfArithmeticSlicesSol(int[] A) {
        int n = A.length;
        long ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - (long) A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = cnt[j].getOrDefault(diff, 0);
                int origin = cnt[i].getOrDefault(diff, 0);
                cnt[i].put(diff, origin + sum + 1);
                ans += sum;
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        NumberOfArithmeticSlicesII nums = new NumberOfArithmeticSlicesII();
        System.out.println(nums.numberOfArithmeticSlices(new int[]{1, 1, 1, 1, 1}));
    }

    }
