package leetcode;

public class LengthOfLongestAlphabeticalSubstring2414 {

  public int longestContinuousSubstring(String s) {
    int max = Math.min(1, s.length());
    int cur = 0;
    int prev = 'a' - 1;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == prev + 1) {
        cur++;
        max = Math.max(cur, max);
      } else {
        prev = 'a' - 1;
        cur = 1;
      }
      prev = s.charAt(i);
    }

    return max;
  }

  public static void main(String[] args) {
    String s = "yrpjofyzubfsiypfre";
    System.out.println(
        new LengthOfLongestAlphabeticalSubstring2414().longestContinuousSubstring(s));
  }

}
