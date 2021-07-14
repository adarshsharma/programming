package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        comb(res, p, n, k, 1);
        return res;
    }

    private void comb(List<List<Integer>> res, List<Integer> p, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(p));
            return;
        }

        for (int i = start; i <= (n - k + 1); i++) {
            p.add(i);
            comb(res, p, n, k - 1, i + 1);
            p.remove(p.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> combs = new Combinations77().combine(5, 3);
        combs.forEach(System.out::println);
    }

}
