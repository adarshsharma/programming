package codejam.jam2013;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 30/05/18.
 */
public class FallingDiamonds {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner input = new Scanner(new BufferedReader(new FileReader("/Users/adarsh.sharma/code/programming/src/main/resources/fallingDiamonds.in")));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            int c = input.nextInt();
            int x = input.nextInt();
            int y = input.nextInt();
            System.out.println("Case #" + ks + ": " + solve(c, x, y));
        }

    }

    private static double solve(int count, int x, int y) {
        double prob = 0.0;
        int s = Math.abs(x) + Math.abs(y);
        if (s % 2 != 0) {
            return 0.0;
        }

        int row = s / 2 + 1;
        int c = (s - Math.abs(x)) + 1;

        int sumTillLastRow = (row - 1) * (1 + (row - 2) * 2);
        int min = c + sumTillLastRow;
        int max = sumTillLastRow + s + c;

        if (count < min) {
            return 0.0;
        } else if (count >= max) {
            return 1.0;
        }

        int n = count - sumTillLastRow;



        return prob;
    }
}
