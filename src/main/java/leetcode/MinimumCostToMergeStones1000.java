package leetcode;

public class MinimumCostToMergeStones1000 {

  public int mergeStones(int[] stones, int k) {
    int n = stones.length;
    if (k > n || ((n - 1) % (k - 1) != 0)) {
      return -1;
    }
    int[] prev = new int[stones.length];
    System.arraycopy(stones, 0, prev, 0, n);

    int result = 0;

    while (prev.length >= k) {
      int min = Integer.MAX_VALUE;
      int sum = 0;
      int idx = 0;
      int[] cur = new int[prev.length - k + 1];
      for (int i = 0; i < prev.length; i++) {
        sum += prev[i];
        if (i >= k) {
          sum -= prev[i - k];
        }
        if (i >= k - 1 && sum < min) {
          min = sum;
          idx = i - k + 1;
        }
      }
      result += min;
      int i = 0;
      int j = 0;
      while (i < prev.length) {
        if (i == idx) {
          cur[j++] = min;
          i += k;
        } else {
          cur[j] = prev[i];
          j++;
          i++;
        }
      }
      prev = cur;
    }

    return result;
  }

  public static void main(String[] args) {
    int k = 2;
    int[] stones = {3, 2, 4, 1};
    System.out.println(new MinimumCostToMergeStones1000().mergeStones(stones, k));
  }

}
