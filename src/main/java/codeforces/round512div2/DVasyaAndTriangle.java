package codeforces.round512div2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 26/09/18.
 */
public class DVasyaAndTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        long m = scanner.nextInt();
        long k = scanner.nextInt();

        long gcd = BigInteger.valueOf(n * m).gcd(BigInteger.valueOf(k)).longValue();
        long den = k / gcd;
        if (den > 2) {
            System.out.println("NO");
            return;
        }

        long maxArea = n * m;
        long reqArea = n * m * 2 / k;

        if (reqArea > maxArea) {
            System.out.println("NO");
            return;
        }

        for (long b = n; b >= 1; b--) {
            if (reqArea % b == 0 && reqArea / b <= m) {
                System.out.println("0 0");
                System.out.println(b + " 0");
                System.out.println(b / 2 + " " + reqArea / b);
                return;
            }
        }

        System.out.println("NO");

    }
}
