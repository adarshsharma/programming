package codejam.jam2020.round1B.problem2;

import java.io.*;
import java.util.*;

public class Solution {

  static int ten9 = 1000000000;

  public static void main(String args[]) {
    Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int T = input.nextInt();
    int A = input.nextInt();
    int B = input.nextInt();
    for (int ks = 1; ks <= T; ks++) {
      // System.out.println("Case #" + ks + ":\n");
      blindFoldedBullsEye(input, A, B);
    }
  }

  private static void blindFoldedBullsEye(Scanner input, int a, int b) {
    int xDiff = 5;
    int yDiff = 5;
    int count = 0;

    if (a == ten9 - xDiff && b == ten9 - yDiff) {
      int start = 0;
      int end = 2 * xDiff;
      while (start <= end && count < 300) {
        count++;
        int mid = (start + end) / 2;
        System.out.println((mid - ten9) + " " + 0);
        String response = input.next();
        if ("CENTER".equals(response)) {
          return;
        } else if ("MISS".equals(response)) {

        } else if ("HIT".equals(response)) {

        }
      }
    }
  }

}
