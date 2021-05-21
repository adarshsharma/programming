package codejam.jam2018.oneA.EdgyBaking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 14/04/18.
 */
public class Solution {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner input = new Scanner(new BufferedReader(new FileReader("/Users/adarsh.sharma/code/test/src/main/resources/edgybaking.csv")));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            System.out.print("Case #" + ks + ":");
            double solve = solve(input);
            System.out.println(solve);
        }
    }

    private static double solve(Scanner input) {
        int n = input.nextInt();
        long p = input.nextLong();
        double minP = 0.0;
        double maxP = 0.0;
        List<Integer> lst = new ArrayList<>();

        int[] w = new int[n];
        int[] h = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = input.nextInt();
            h[i] = input.nextInt();
            minP += 2 * (w[i] + h[i]);

            if (h[i] < w[i]) {
                lst.add(2 * h[i]);
            } else {
                lst.add(2 * w[i]);
            }

            maxP += 2 * (w[i] + h[i]) + 2 * Math.sqrt(w[i] * w[i] + h[i] * h[i]);
        }

        if (p >= maxP) {
            return maxP;
        } else {
            double min;
            if (h[0] < w[0]) {
                min = 2 * h[0];
            } else {
                min = 2 * w[0];
            }
            double max = 2 * Math.sqrt(w[0] * w[0] + h[0] * h[0]);

            double rem = p - minP;

            for (int i = 0; i < n; i++) {
                if (rem == 0) {
                    return p;
                }

                
            }
        }
        return 0.0;

    }
}
