package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 19/07/18.
 */
public class RodCutting {
    int[][] cost;
    int[][] bk;
    ArrayList<Integer> A;
    int L;

    private int getL(int idx) {
        return idx == 0 ? 0 : (idx == A.size() + 1 ? L : A.get(idx - 1));
    }

    public ArrayList<Integer> rodCut(int length, ArrayList<Integer> a) {
        ArrayList<Integer> res = new ArrayList<>();
        this.A = a;
        this.L = length;
        if (L == 0 || A == null) {
            return res;
        }

        if (A.size() == 1) {
            res.add(A.get(0));
            return res;
        }
        int n = A.size();
        cost = new int[n + 2][n + 2];
        bk = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            cost[i][i] = 0;
            if (i <= n) {
                cost[i][i + 1] = 0;
                if (i <= n - 1) {
                    cost[i][i + 2] = getL(i + 2) - getL(i);
                    bk[i][i + 2] = i + 1;
                }
            }
        }

        for (int l = 3; l <= n + 1; l++) {
            for (int i = 0; i <= n + 1 - l; i++) {
                int c = Integer.MAX_VALUE;
                for (int j = i + 1; j < i + l; j++) {
                    if (cost[i][j] + cost[j][i + l] < c) {
                        c = cost[i][j] + cost[j][i + l];
                        bk[i][i + l] = j;
                    }
                }

                cost[i][i + l] = c + getL(i + l) - getL(i);
            }
        }

        populateResult(res, 0, n + 1);

        return res;
    }

    private void populateResult(ArrayList<Integer> res, int s, int e) {
        if (bk[s][e] != 0) {
            res.add(getL(bk[s][e]));

            populateResult(res, s, bk[s][e]);
            populateResult(res, bk[s][e], e);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        Random ran = new Random();
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<10;i++) {
            set.add(ran.nextInt(48) + 1);
        }
//        A.addAll(Arrays.asList(2, 4, 5, 8));
        A.addAll(set);
        Collections.sort(A);
        System.out.println(A);
        System.out.println(new RodCutting().rodCut(50, A));
    }

}
