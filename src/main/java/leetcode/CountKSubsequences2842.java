package leetcode;

import java.util.Arrays;

public class CountKSubsequences2842 {

  final long MOD = 1000000007L;

  public int countKSubsequencesWithMaxBeauty(String s, int k) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }
    Arrays.sort(freq);
    for (int i = 0; i < 13; i++) {
      int a = freq[i];
      freq[i] = freq[25 - i];
      freq[25 - i] = a;
    }

    long res = 1L;
    int uniq = 0;
    for (int i = 0; i < 26; i++) {
      if (freq[i] > 0) {
        uniq++;
      }
    }

    if (k > 26 || k > uniq) {
      return 0;
    }

    int last = freq[k - 1];
    int same = 0;
    for (int i = 0; i < 26; i++) {
      if (freq[i] == freq[k - 1]) {
        same++;
      }
    }

    int i = 0;
    for (i = 0; i < k; i++) {
      if (freq[i] != freq[k - 1]) {
        res = (res * freq[i]) % MOD;
      } else {
        break;
      }
    }

    int n = same;
    int r = k - i;

    res = (res * computeCombinations(n, r)) % MOD;

    return (int) res;
  }

  private long computeCombinations(int n, int r) {
    if (r > n / 2) {
      r = n - r;
    }

    long res = 1L;
    for (int i = 0; i < r; i++) {
      res = (res * (res - i)) % MOD;
    }
    while(r > 0) {
      res = (res / r) % MOD;
      r--;
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(new CountKSubsequences2842().countKSubsequencesWithMaxBeauty("abbcd", 4));
  }
}
