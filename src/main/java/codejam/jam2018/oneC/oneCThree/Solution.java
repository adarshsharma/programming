package codejam.jam2018.oneC.oneCThree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner input = new Scanner(new BufferedReader(new FileReader("/Users/adarsh.sharma/code/test/src/main/resources/oneC3.csv")));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            System.out.print("Case #" + ks + ": ");
            long word = solve(input);
            System.out.println(word);
        }
    }


    private static int solve(Scanner input) {
        int n = input.nextInt();

        long[] w = new long[n];

        for (int i = 0; i < n; i++) {
            w[i] = input.nextInt();
        }

        int[] msh = new int[n];
        long[] sum = new long[n];

        msh[0] = 1;
        sum[0] = w[0];
        int totalMax = 1;

        for (int i = 1; i < n; i++) {
            int maxj = -1;
            int maxh = 0;
            for (int j = 0; j < i; j++) {
                if (sum[j] <= 6 * w[i]) {
                    if (maxj == -1 || msh[j] > msh[maxj] || (msh[j] == msh[maxj] && sum[j] < sum[maxj])) {
                        maxh = msh[j];
                        maxj = j;
                    }
                }
            }

            if (maxj == -1) {
                msh[i] = 1;
                sum[i] = w[i];
            } else {
                msh[i] = maxh + 1;
                sum[i] = sum[maxj] + w[i];
            }

            if (totalMax < msh[i]) {
                totalMax = msh[i];
            }
        }

        return totalMax;
    }
}