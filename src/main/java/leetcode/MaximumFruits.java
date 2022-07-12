package leetcode;

public class MaximumFruits {

  public int maxTotalFruits(int[][] fruits, int startPos, int k) {
    int startIdx = 0;
    while (startIdx < fruits.length && startPos > fruits[startIdx][0]) {
      startIdx++;
    }

    int output = 0;
    if (fruits[startIdx][0] == startPos) {
      output += fruits[startIdx][1];
    }

    int next = startIdx + ((fruits[startIdx][0] == startPos) ? 1 : 0);
    int prev = startIdx - 1;
    int p = 0;
    for (int i = next; i < fruits.length; i++) {
      fruits[i][1] = p + fruits[i][1];
      p = fruits[i][1];
    }

    p = 0;
    for (int i = prev; i >= 0; i--) {
      fruits[i][1] = p + fruits[i][1];
      p = fruits[i][1];
    }
    int max = getRight(fruits, startPos, k, next);
    for (int i = prev; i >= 0 && k >= startPos - fruits[i][0]; i--) {
      int mx = Integer.MIN_VALUE;
      int first = fruits[i][1];
      int steps = k - 2 * (startPos - fruits[i][0]);
      if (steps > 0) {
        mx = first + getRight(fruits, startPos, steps, next);
      }

      int second = fruits[i][1];
      steps = (k - (startPos - fruits[i][0])) / 2;
      if (steps > 0) {
        mx = Math.max(mx, second + getRight(fruits, startPos, steps, next));
      }
      max = Integer.max(max, mx);

    }

    return output + max;

  }

  int getRight(int[][] fruits, int startPos, int steps, int next) {
    int start = next;
    int end = fruits.length - 1;
    int mid = start + (end - start) / 2;
    while (start < end) {
      if (fruits[mid][0] - startPos > steps) {
        end = mid - 1;
      } else if (fruits[mid][0] - startPos == steps) {
        break;
      } else {
        start = mid + 1;
      }
      mid = start + (end - start) / 2;
    }
    if(mid > fruits.length -1) {
      mid = mid -1;
    }
    if (fruits[mid][0] - startPos <= steps) {
      return fruits[mid][1];
    } else {
      return mid - 1 >= next ? fruits[mid - 1][1] : 0;
    }
  }

  public static void main(String[] args) {
    // int[][] fruits = {{2, 8}, {6, 3}, {8, 6}};
    // int startPos = 5;
    // int k = 4;
    // int[][] fruits = {{0, 9}, {4, 1}, {5, 7}, {6, 2}, {7, 4}, {10, 9}};
    // int startPos = 5;
    // int k = 4;
    int[][] fruits = {{200000, 10000}};
    int startPos = 200000;
    int k = 200000;
    System.out.println(new MaximumFruits().maxTotalFruits(fruits, startPos, k));
  }

}
