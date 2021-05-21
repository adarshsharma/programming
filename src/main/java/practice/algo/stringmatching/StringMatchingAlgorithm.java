package practice.algo.stringmatching;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by adarsh.sharma on 13/11/17.
 */
public class StringMatchingAlgorithm {
    private static final String test =   "bacbabababbcabcbab";
    public static final String pattern = "ababaca";

    private static final String testr =   "babcbacbbabababcab";
    public static final String patternr = "acababa";

    public static Integer[] computePrefixFunction(String pattern) {
        char[] P = pattern.toCharArray();
        int m = P.length;
        Integer[] Pi = new Integer[m];

        Pi[0] = 0;
        int k = 0;

        for (int q = 1; q < m; q++) {
            while (k > 0 && P[k] != P[q]) {
                k = Pi[k - 1];
            }
            if (P[k] == P[q]) {
                k = k + 1;
            }
            Pi[q] = k;
        }
        return Pi;
    }

    public static Integer KMPMatcher(String test, String pattern) {
        char[] T = test.toCharArray();
        char[] P = pattern.toCharArray();

        Integer n = T.length;
        Integer m = P.length;

        Integer[] Pi = computePrefixFunction(pattern);

        int q = 0;

        for (int i = 0; i < n; i++) {
            while (q > 0 && P[q] != T[i]) {
                q = Pi[q - 1];
            }
            if (P[q] == T[i]) {
                q = q + 1;
            }
            if (q == m) {
                return i + 1 - m;
            }
        }
        return -1;
    }

    private static String reverse(String s) {
        char[] r = s.toCharArray();
        Integer l = r.length;

        for (int i = 0; i < l / 2; i++) {
            char a = r[i];
            r[i] = r[l - i - 1];
            r[l - i - 1] = a;
        }
        return new String(r);
    }

    public static Integer[] zArray(String t, String p) {
        char[] T = (p + "#" + t).toCharArray();
        Integer n = T.length;

        Integer[] Z = new Integer[T.length];

        int L = 0, R = 0;
        Z[0] = 0;
        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && T[R - L] == T[R]) R++;
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && T[R - L] == T[R]) R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }

        Integer[] ret = new Integer[t.length()];
        System.arraycopy(Z, p.length() + 1, ret, 0, t.length());
        return ret;
    }

    public static void matchWithAtMostOneMismatch(String test, String pattern) {
        Integer[] Z1 = zArray(test, pattern);
        int m = pattern.length();
        int n = test.length();

        String matchList = "";

        Integer[] Z2 = zArray(reverse(test), reverse(pattern));
//        System.out.println(StringUtils.join(Z1, " "));
//        System.out.println(StringUtils.join(Z2, " "));

        for (int i = 0; i <= n - m; i++) {
            if (Z1[i] == m || Z1[i] == m - 1 || Z2[n - i - m] >= m - Z1[i] - 1) {
                if (matchList.length() > 0) {
                    matchList = matchList + " " + i;
                } else {
                    matchList = "" + i;
                }
            }
        }

        if (matchList.length() > 0) {
            System.out.println(matchList);
        } else {
            System.out.println("No Match!");
        }
    }

    public static void main(String[] args) {
//        Map<Integer, Integer> mp = new HashMap<>();
//        List<Integer> lst = new ArrayList<>(mp.keySet());
//        List<Integer> val = new ArrayList<>(mp.values());
//        System.out.println(KMPMatcher(test, pattern));
//        matchWithAtMostOneMismatch(test, pattern);
        Integer[] res = StringMatchingAlgorithm.computePrefixFunction("AABAACAABAA");
        System.out.println(StringUtils.join(res));
    }
}
