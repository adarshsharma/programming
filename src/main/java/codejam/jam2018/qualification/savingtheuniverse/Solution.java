package codejam.jam2018.qualification.savingtheuniverse;

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
            int D = input.nextInt();
            String P = input.next();
            System.out.print("Case #" + ks + ": ");
            solve(D, P.toCharArray());
        }
    }

    private static void solve(int D, char[] P) {
        int count = 0;
        int[] damage = new int[P.length];
        int d = 0;
        int charge = 1;
        for (int i = 0; i < P.length; i++) {
            if (P[i] == 'S') {
                damage[i] = charge;
                d += charge;
            } else {
                charge *= 2;
                damage[i] = 0;
            }
        }

        int start = P.length - 1;
        boolean swapped = true;

        while (swapped && d > D) {
            swapped = false;
            int swap = start;
            while (swap >= 0 && P[swap] == 'C') {
                swap--;
            }
            start = swap;

            while (swap >= 0 && P[swap] == 'S') {
                swap--;
            }

            if (swap >= 0) {
                P[swap] = 'S';
                P[swap + 1] = 'C';
                damage[swap] = damage[swap + 1] / 2;
                damage[swap + 1] = 0;
                d -= damage[swap];
                count++;
                swapped = true;
            }
        }
        if (d > D) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(count);
        }
    }
}
