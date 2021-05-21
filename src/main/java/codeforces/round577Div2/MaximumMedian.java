package codeforces.round577Div2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 06/02/20.
 */
public class MaximumMedian {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int k = scanner.nextInt();
    List<Integer> A = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      A.add(scanner.nextInt());
    }

    Collections.sort(A);
    long middleElement = A.get(A.size() / 2);
    long med = middleElement;
    long start = 0;
    long end = k;

    while (start <= end) {
      long mid = (start + end) / 2;
      long req = mid;
      for (int i = n / 2 + 1; i < n; i++) {
        if (A.get(i) < (middleElement + mid)) {
          req += (middleElement + mid - A.get(i));
        }
      }
      if (req <= k) {
        med = Math.max(med, middleElement + mid);
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    System.out.println(med);
  }
}
