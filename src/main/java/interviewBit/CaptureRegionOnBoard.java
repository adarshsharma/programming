package interviewBit;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 21/07/18.
 */
public class CaptureRegionOnBoard {
    int m;
    int n;

    public void solve(ArrayList<ArrayList<Character>> a) {
        if (a == null || a.size() == 0 || a.get(0).size() == 0) {
            return;
        }

        m = a.size();
        n = a.get(0).size();

        for (int i = 0; i < m; i += Math.max(1, (m - 1))) {
            ArrayList<Character> row = a.get(i);
            for (int j = 0; j < n; j++) {
                if (row.get(j) == 'O') {
                    traverse(a, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            ArrayList<Character> row = a.get(i);
            for (int j = 0; j < n; j += Math.max(1, (n - 1))) {
                if (row.get(j) == 'O') {
                    traverse(a, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a.get(i).get(j) == '.') {
                    a.get(i).set(j, 'O');
                } else if (a.get(i).get(j) == 'O') {
                    a.get(i).set(j, 'X');
                }
            }
        }
    }

    private void traverse(ArrayList<ArrayList<Character>> a, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (a.get(i).get(j) != 'O') {
            return;
        }

        a.get(i).set(j, '.');

        traverse(a, i, j - 1);
        traverse(a, i - 1, j);
        traverse(a, i, j + 1);
        traverse(a, i + 1, j);
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> a = new ArrayList<>();
        ArrayList<Character> r = new ArrayList<>();
        r.add('O');
        a.add(r);
        new CaptureRegionOnBoard().solve(a);
        System.out.println(a);
    }
}
