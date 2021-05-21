package codejam.jam2018.qualification.cubicUFO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 08/04/18.
 */
public class Solution {
    public static void main(String args[]) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            double A = input.nextDouble();
            System.out.println("Case #" + ks + ":");
            solveUFO(A);
        }
    }

    private static void solveUFO(double a) {
        double start = 0;
        double end = Math.PI / 4;
        double mid = (start + end) / 2;

        double v = Math.cos(mid) + Math.sin(mid);
        double diff = v - a;

        while (Math.abs(diff) > 0.0000000000000001) {
            if (diff > 0) {
                end = mid;
            } else {
                start = mid;
            }
            mid = (start + end) / 2;
            v = Math.cos(mid) + Math.sin(mid);
            diff = v - a;
        }

        double x = 0.5 * Math.cos(mid);
        double y = 0.5 * Math.sin(mid);

        System.out.println(x + ","  + y + "," + 0);
        System.out.println(-y+ ","  + x + "," + 0);
        System.out.println("0, 0, " + 0.5);
    }
}
