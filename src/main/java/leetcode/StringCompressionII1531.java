package leetcode;

public class StringCompressionII1531 {

    // int[] dp;
    // char[] lastChar;
    // int[] lastCount;
    //
    // public int getLengthOfOptimalCompression(String s, int k) {
    //     int n = s.length();
    //     dp = new int[n];
    //     lastChar = new char[n];
    //     lastCount = new int[n];
    //
    //     char prevCh = ' ';
    //     int pCount = 0;
    //     int prevL = 0;
    //     for (int i = 0; i < n; i++) {
    //         char ch = s.charAt(i);
    //         if (ch == prevCh || i == 0) {
    //             pCount++;
    //         } else {
    //             pCount = 1;
    //             prevL = dp[i - 1];
    //         }
    //         prevCh = ch;
    //         lastChar[i] = ch;
    //         lastCount[i] = pCount;
    //         dp[i] = prevL + 1 + getCount(pCount);
    //     }
    //     int minLength = dp[n - 1];
    //
    //     for (int rem = 1; rem <= k; rem++) {
    //         int[] ndp = new int[n];
    //         char[] nLastChar = new char[n];
    //         int[] nLastCount = new int[n];
    //
    //         for (int i = 0; i < n; i++) {
    //             ndp[i] = i == 0 ? 0 : dp[i - 1];
    //             nLastChar[i] = i == 0 ? ' ' : lastChar[i - 1];
    //             nLastCount[i] = i == 0 ? 0 : lastCount[i - 1];
    //             int j = i - 1;
    //             while (j >= 0 && s.charAt(j) == s.charAt(i)) {
    //                 j--;
    //             }
    //             if (j >= 0) {
    //                 nLastChar[i] = s.charAt(i);
    //                 int notRemoval;
    //                 if (nLastChar[j] == s.charAt(i)) {
    //                     notRemoval =
    //                         ndp[j] - getCount(nLastCount[j]) + getCount(nLastCount[j] + i - j);
    //                     if (notRemoval < ndp[i]) {
    //                         nLastCount[i] = nLastCount[j] + i - j;
    //                         ndp[i] = notRemoval;
    //                     }
    //                 } else {
    //                     notRemoval = ndp[j] + 1 + getCount(i - j);
    //                     if (notRemoval < ndp[i]) {
    //                         nLastCount[i] = i - j;
    //                         ndp[i] = notRemoval;
    //                     }
    //                 }
    //
    //             }
    //         }
    //
    //         dp = ndp;
    //         lastChar = nLastChar;
    //         lastCount = nLastCount;
    //         minLength = Math.min(minLength, dp[n - 1]);
    //     }
    //
    //     return minLength;
    // }
    //
    // private int getCount(int c) {
    //     return c == 1 ? 0 : Integer.toString(c).length();
    // }
    //
    // public static void main(String[] args) {
    //     // String s = "aaaaaaaaaaa";
    //     String s = "aabbaa";
    //     int k = 2;
    //     System.out.println(new StringCompressionII1531().getLengthOfOptimalCompression(s, k));
    // }


}
