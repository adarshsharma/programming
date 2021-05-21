package interviewBit;

/**
 * Created by adarsh.sharma on 11/07/18.
 */
public class RegularExpressionMatch {
    public int isMatch(final String A, final String B) {
        if (A == null && B == null) {
            return 1;
        }

        if (A == null || B == null) {
            return 0;
        }

        Boolean[] dpOld = new Boolean[B.length() + 1];
        Boolean[] dpNew = new Boolean[B.length() + 1];
        dpOld[0] = true;
        for (int j = 1; j <= B.length(); j++) {
            dpOld[j] = dpOld[j - 1] && B.charAt(j - 1) == '*';
        }

        for (int i = 1; i <= A.length(); i++) {
            dpNew[0] = false;
            for (int j = 1; j <= B.length(); j++) {
                dpNew[j] = match(A, B, dpOld, dpNew, i, j);
            }
            dpOld = dpNew;
            dpNew = new Boolean[B.length() + 1];
        }

        return dpOld[B.length()] ? 1 : 0;
    }

    private boolean match(String A, String B, Boolean[] dpOld, Boolean[] dpNew, int i, int j) {
        char b = B.charAt(j - 1);
        char a = A.charAt(i - 1);

        if (b == '*') {
            return dpNew[j - 1] || dpOld[j];
        } else if (b == '?' || b == a) {
            return dpOld[j - 1];
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        not working
        System.out.println(new RegularExpressionMatch().isMatch("cab", "c*a*b"));
    }
}
