package leetcode;

/**
 * Created by adarsh.sharma on 29/07/18.
 */
public class Shortestpalindrome {
    private static final int R = 256;
    private static final long Q = 101;//1946707727; // Random Prime
    public String shortestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return s;

        long hashLR = 0, hashRL = 0;
        long maxR = 1; // for R^i for LR

        int match = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            hashLR = (hashLR + ch * maxR) % Q;
            hashRL = (hashRL * R + ch) % Q;

            if (hashLR == hashRL) {
                match = i;
            }

            maxR = (maxR * R) % Q;
        }

        if (match == n - 1) return s;
        return new StringBuilder(s.substring(match + 1)).reverse().append(s).toString();
    }

    public String shortestPalindrome2(String s) {
        if(s==null || s.length()<2) {
            return s;
        }
        int l = maxPrefSuff(s+"#"+new StringBuilder().append(s).reverse().toString());
        return new StringBuilder(s.substring(l)).reverse().append(s).toString();
    }

    public int maxPrefSuff(String pattern) {
        int length = pattern.length();
        Integer[] lps = new Integer[length];

        int k = 0;
        lps[0] = 0;

        for (int i = 1; i < length; i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = lps[k - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(k)) {
                k++;
            }
            lps[i] = k;
        }
        return lps[length-1];
    }

    public static void main(String[] args) {
        System.out.println(new Shortestpalindrome().shortestPalindrome("aacecaacecaaa"));
        System.out.println(new Shortestpalindrome().shortestPalindrome2("aacecaacecaaa"));
    }
}
