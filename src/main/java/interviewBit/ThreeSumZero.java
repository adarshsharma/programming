package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by adarsh.sharma on 23/07/18.
 */
public class ThreeSumZero {
    public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (a == null || a.size() < 3) {
            return result;
        }

        Collections.sort(a);
        for (int i = 0; i < a.size() - 2; i++) {
            if (i == 0 || !a.get(i).equals(a.get(i - 1))) {
                findSum(a, i + 1, result, -a.get(i));
            }
        }
        return result;
    }

    private void findSum(ArrayList<Integer> a, int low, ArrayList<ArrayList<Integer>> result, int sum) {
        int first = a.get(low - 1);
        int start = low;
        int high = a.size() - 1;
        while (low < high) {
            if (a.get(low) + a.get(high) == sum) {
                add(result, first, a.get(low), a.get(high));
                low++;
                high--;
                while (low < a.size() && a.get(low) == a.get(low - 1)) {
                    low++;
                }
                while (high > start && a.get(high) == a.get(high + 1)) {
                    high--;
                }
            } else if (a.get(low) + a.get(high) < sum) {
                low++;
            } else {
                high--;
            }
        }
    }

    private void add(ArrayList<ArrayList<Integer>> res, int a, int b, int c) {
        ArrayList<Integer> r = new ArrayList<>();
        r.add(a);
        r.add(b);
        r.add(c);
        res.add(r);
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(-31013930, -31013930, 9784175, 21229755));
        System.out.println(new ThreeSumZero().threeSum(a));
    }
}
