package leetcode;

public class SubStringWithLargestVariance2272 {

  public int largestVariance(String s) {
    int largest = 0;
    int n = s.length();
    int[][] prev = new int[n][26];
    int[][] next = new int[n][26];

    for (int len = 1; len <= n; len++) {
      for (int i = len - 1; i < n; i++) {
        next[i][s.charAt(i) - 'a']++;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int c = 0; c < 26; c++) {
          if (i > 0) {
            next[i][c] += prev[i - 1][c];
          }
          if(next[i][c] > 0) {
            min = Math.min(min, next[i][c]);
            max = Math.max(max, next[i][c]);
          }
        }
        largest = Math.max(largest, max - min);
      }
      prev = next;
      next = new int[n][26];
    }

    return largest;
  }

  public static void main(String[] args) {
    String s = "icexiahccknibwuwgi";
    System.out.println(new SubStringWithLargestVariance2272().largestVariance(s));
  }
}
