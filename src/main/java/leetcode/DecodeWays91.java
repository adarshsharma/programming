package leetcode;

public class DecodeWays91 {

  public static int numDecodings(String s) {
    if (s.length() == 0) {
      return 1;
    }
    if (s.charAt(0) == '0') {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }
    int first = 1;
    int second = 1;

    for (int i = 1; i < s.length(); i++) {
      int cur = 0;
      if (s.charAt(i) != '0') {
        cur += second;
      }

      if (s.charAt(i - 1) != '0' && Integer.parseInt(s.substring(i - 1, i + 1)) <= 26) {
        cur += first;
      }

      first = second;
      second = cur;

    }

    return second;
  }

  public static void main(String[] args) {
    System.out.println(numDecodings("12"));
  }
}
