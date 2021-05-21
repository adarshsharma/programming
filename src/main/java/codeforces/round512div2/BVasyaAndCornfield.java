package codeforces.round512div2;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 26/09/18.
 */
public class BVasyaAndCornfield {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();

        int k = scanner.nextInt();
        while (k > 0) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            if (inside(x1, y1, n, d)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            k--;
        }
    }

    private static boolean inside(int x1, int y1, int n, int d) {
        int p1 = x1 + y1 - d;
        int p2 = x1 + y1 - 2 * n + d;

        int p3 = x1 - y1 + d;
        int p4 = x1 - y1 - d;

        return p1 * p2 <= 0 && p3 * p4 <= 0;
    }

}
