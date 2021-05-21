package codejam.jam2020.round1C.problem1;

import java.io.*;
import java.util.Scanner;

public class Solution {

  public static void main(String args[]) {
    Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int T = input.nextInt();
    for (int ks = 1; ks <= T; ks++) {
      System.out.println(
          "Case #" + ks + ": " + overexcited(input.nextInt(), input.nextInt(), input.next()));
    }
  }

  private static String overexcited(int x, int y, String path) {
    // if (path.length() < x) {
    //   return "IMPOSSIBLE";
    // }
    //
    // Integer minutes = 0;
    // for (; minutes < x; minutes++) {
    //   y = y + ((path.charAt(minutes) == 'N') ? 1 : -1);
    // }
    //
    // int loc = 0;
    //
    // while (y != loc && minutes < path.length()) {
    //   y = y + ((path.charAt(minutes) == 'N') ? 1 : -1);
    //   if (y > loc) {
    //     loc++;
    //   } else if (y < loc) {
    //     loc--;
    //   }
    //   minutes++;
    // }
    //
    // if (y == loc) {
    //   return minutes.toString();
    // }

    if (x == 0 && y == 0) {
      return "0";
    }

    for (int i = 0; i < path.length(); i++) {
      switch (path.charAt(i)) {
        case 'N':
          y++;
          break;
        case 'S':
          y--;
          break;
        case 'E':
          x++;
          break;
        case 'W':
          x--;
          break;
      }

      if (Math.abs(x) + Math.abs(y) <= i + 1) {
        return String.format("%d", i + 1);
      }
    }

    return "IMPOSSIBLE";
  }

}
