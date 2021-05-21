package codeforces.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MediansAndPartitions {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }

    int[][] smaller = new int[n][n];
    int[][] largerOrEqual = new int[n][n];
    boolean[][] validPartition = new boolean[n][n];

    for (int i = 0; i < n; i++) {
      if (a[i] < m) {
        smaller[0][i] = 1;
        validPartition[0][i] = false;
      } else if (a[i] == m) {
        largerOrEqual[0][i] = 1;
        validPartition[0][i] = true;
      }
    }
    for (int l = 2; l <= n; l++) {
      for (int i = 0; i <= n - l; i++) {
        largerOrEqual[l - 1][i] = largerOrEqual[l - 2][i] + largerOrEqual[0][i + l - 1];
        smaller[l - 1][i] = smaller[l - 2][i] + smaller[0][i + l - 1];
        validPartition[l - 1][i] = isMedianGreater(smaller[l - 1][i], largerOrEqual[l - 1][i]);
      }
    }



  }

  private static boolean isMedianGreater(int smaller, int largerOrEqual) {
    return smaller < largerOrEqual;
  }
}
