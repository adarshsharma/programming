package interviewBit;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 23/07/18.
 */
public class PalindromePartitioning {
    public ArrayList<ArrayList<String>> partition(String a) {
        if(a==null) {
            return new ArrayList<>();
        }

        int n = a.length();
        boolean[][] pal = new boolean[n][n];
        for(int i=0;i<n;i++) {
            pal[i][i] = true;
            if(i!=n-1) {
                pal[i][i+1] = a.charAt(i) == a.charAt(i+1);
            }
        }

        for(int l=3;l<=n;l++) {
            for(int i=0;i<=n-l;i++) {
                pal[i][i+l-1] = (a.charAt(i) == a.charAt(i+l-1)) && pal[i+1][i+l-2];
            }
        }

        ArrayList<ArrayList<String>> res = new ArrayList<>();

        populate(res, pal, a, 0, new ArrayList<>());

        return res;
    }

    private void populate(ArrayList<ArrayList<String>> res, boolean[][] pal,
                          String a, int len, ArrayList<String> r) {
        if(len == a.length()) {
            ArrayList<String> nr = new ArrayList<>();
            nr.addAll(r);
            res.add(nr);
            return;
        }

        int i = len;
        for(int j=i;j<a.length();j++) {
            if(pal[i][j]) {
                r.add(a.substring(i, j+1));
                populate(res, pal, a, len+j-i+1, r);
                r.remove(r.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().partition("efe"));
    }
}
