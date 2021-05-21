package codejam.jam2018.qualification.gopher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 08/04/18.
 */
public class Solution {
    public static void solve(Scanner input, int A) {
        int len = A / 3;
        if (A % 3 != 0) {
            len += 1;
        }

        len = Math.max(len, 3);

        int[][] cell = new int[1000][1000];

        int i = 1;
        int j = 1;

        // check first 2 column
        while (emptyColumn(1, cell) || emptyColumn(2, cell)) {
            System.out.print(2 + " " + 2);
            i = input.nextInt();
            j = input.nextInt();
            cell[i - 1][j - 1] = 1;
            if (i <= 0 && j <= 0) {
                break;
            }
        }

        for (int c = 2; c < len && i > 0 && j > 0; c++) {
            while (emptyColumn(c + 1, cell) && i > 0 && j > 0) {
                System.out.print(2 + " " + c);
                i = input.nextInt();
                j = input.nextInt();
                cell[i - 1][j - 1] = 1;
            }
        }
    }

    private static boolean emptyColumn(int c, int[][] cell) {
        return cell[0][c - 1] + cell[1][c - 1] + cell[2][c - 1] < 3;
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            int A = input.nextInt();
            solve(input, A);
        }
    }
}
