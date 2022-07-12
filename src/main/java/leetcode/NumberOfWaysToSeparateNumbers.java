package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NumberOfWaysToSeparateNumbers {

  static class Key {

    int a, b, c;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Key)) {
        return false;
      }
      Key key = (Key) o;
      return a == key.a && b == key.b && c == key.c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(a, b, c);
    }

    public Key(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }

  Map<Key, Long> dMap;
  long mod = 1000000007;

  public int numberOfCombinations(String num) {
    int n = num.length();
    dMap = new HashMap<>();
    int res = nc(num, n, "").intValue();
    return res;
  }

  private Long nc(String num, int length, String prev) {
    if (length == 0) {
      return 1L;
    }
    int end = length + prev.length() - 1;
    Key key = new Key(length - 1, length, end);
    if (dMap.containsKey(key)) {
      return dMap.get(key);
    }
    long val = 0L;
    int mxLength = prev.length() == 0 ? length : Math.min(length, prev.length());
    for (int len = 1; len <= mxLength; len++) {
      char firstChar = num.charAt(length - len);
      if (firstChar != '0') {
        String cur = num.substring(length - len, length);
        if (prev.length() == 0 || compare(cur, prev) <= 0) {
          val = (val + nc(num, length - len, cur)) % mod;
        }
      }
    }
    dMap.put(key, val);
    return val;
  }

  // Long dp[][][];
  // long mod = 1000000007;
  //
  // public int numberOfCombinations(String num) {
  //   int n = num.length();
  //   dp = new Long[n][n + 1][n + 1];
  //   int res = nc(num, n, "").intValue();
  //   return res;
  // }
  //
  // private Long nc(String num, int length, String prev) {
  //   if (length == 0) {
  //     return 1L;
  //   }
  //   int end = length + prev.length() - 1;
  //   if (dp[length - 1][length][end] != null) {
  //     return dp[length - 1][length][end];
  //   }
  //   long val = 0L;
  //   int mxLength = prev.length() == 0 ? length : Math.min(length, prev.length());
  //   for (int len = 1; len <= mxLength; len++) {
  //     char firstChar = num.charAt(length - len);
  //     if (firstChar != '0') {
  //       String cur = num.substring(length - len, length);
  //       if (prev.length() == 0 || compare(cur, prev) <= 0) {
  //         val = (val + nc(num, length - len, cur)) % mod;
  //       }
  //     }
  //   }
  //   dp[length - 1][length][end] = val;
  //   return val;
  // }

  private int compare(String first, String second) {
    if (first.length() != second.length()) {
      return first.length() - second.length();
    }
    return first.compareTo(second);
  }

  public static void main(String[] args) {
    System.out.println(new NumberOfWaysToSeparateNumbers().numberOfCombinations("9999999999999"));
  }

}
