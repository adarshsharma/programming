package leetcode;

public class LCS1143 {

  public static int longestCommonSubsequence(String text1, String text2) {
    if (text1.length() > text2.length()) {
      return longestCommonSubsequence(text2, text1);
    }

    int m = text1.length();
    int n = text2.length();

    int[] prev = new int[m];
    int[] cur = new int[m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (text1.charAt(j) == text2.charAt(i)) {
          cur[j] = 1 + ((i == 0 || j == 0) ? 0 : prev[j - 1]);
        } else {
          cur[j] = Math.max(j == 0 ? 0 : cur[j - 1], i == 0 ? 0 : prev[j]);
        }
      }
      prev = cur;
      cur = new int[m];
    }

    return prev[m - 1];
  }

  public static void main(String[] args) {
    System.out.println(longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
  }
}
