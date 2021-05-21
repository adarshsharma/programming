package interviewBit;

/**
 * Created by adarsh.sharma on 12/07/18.
 */
public class RegularExpressionMatch2 {
    public int isMatch(final String A, final String B) {
        if (A == null && B == null) {
            return 1;
        }

        if (A == null || B == null) {
            return 0;
        }

        boolean[] dpOld = new boolean[B.length() + 1];
        boolean[] dpNew = new boolean[B.length() + 1];
        dpOld[0] = true;
        dpOld[1] = false;
        for (int j = 2; j <= B.length(); j++) {
            dpOld[j] = dpOld[j-2] && B.charAt(j-1)=='*';
        }

        for (int i = 1; i <= A.length(); i++) {
            dpNew[0] = false;
            for (int j = 1; j <= B.length(); j++) {
                dpNew[j] = match(A, B, dpOld, dpNew, i, j);
            }
            dpOld = dpNew;
            dpNew = new boolean[B.length() + 1];
        }

        return dpOld[B.length()] ? 1 : 0;
    }

    private boolean match(String A, String B, boolean[] dpOld, boolean[] dpNew, int i, int j) {
        char b = B.charAt(j - 1);
        char a = A.charAt(i - 1);

        if (b == '*') {
            if(j==1) {
                return false;
            } else {
                char last = B.charAt(j-2);
                return dpNew[j-2] || (dpOld[j] && (a==last || last=='.'));
            }
        } else if (b == '.' || b == a) {
            return dpOld[j - 1];
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatch2().isMatch("ab", ".*"));
    }
}
