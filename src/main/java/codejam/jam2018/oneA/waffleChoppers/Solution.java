package codejam.jam2018.oneA.waffleChoppers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 14/04/18.
 */

public class Solution {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner input = new Scanner(new BufferedReader(new FileReader("/Users/adarsh.sharma/code/test/src/main/resources/waffle.csv")));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            System.out.print("Case #" + ks + ":");
            int r = input.nextInt();
            int c = input.nextInt();
            int h = input.nextInt();
            int v = input.nextInt();

            int solve;
            if (h == 1 && v == 1) {
                solve = solve(input, r, c, h, v);
            } else {
                solve = solve2(input, r, c, h, v);
            }
            if (solve == 0) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println("POSSIBLE");
            }
        }
    }

    private static int solve(Scanner input, int r, int c, int h, int v) {
        int g[][] = new int[r][c];

        int totalChips = 0;

        for (int i = 0; i < r; i++) {
            char[] ch = input.next().toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if (ch[j] == '.') {
                    g[i][j] = 0;
                } else {
                    g[i][j] = 1;
                    totalChips++;
                }
            }
        }

        int parts = (h + 1) * (v + 1);

        if (totalChips % parts != 0) {
            return 0;
        }

        int equal = totalChips / parts;
        int equalPerRow = totalChips / (h + 1);
        int equalPerCol = totalChips / (v + 1);

        int[] ha = new int[h + 2];
        int[] va = new int[v + 2];

        ha[0] = -1;
        va[0] = -1;
        ha[2] = r - 1;
        va[2] = c - 1;

        for (int a = 0; a < r; a++) {
            ha[1] = a;
            for (int b = 0; b < c; b++) {
                va[1] = b;
                boolean failed = false;
                for (int row = 0; row <= h && !failed; row++) {
                    for (int col = 0; col <= v; col++) {
                        int sum = 0;
                        for (int i = ha[row] + 1; i <= ha[row + 1] && !failed; i++) {
                            for (int j = va[col] + 1; j <= va[col + 1]; j++) {
                                sum += g[i][j];
                            }
                        }
                        if (sum != equal) {
                            failed = true;
                        }
                    }
                }
                if (!failed) {
                    return 1;
                }
            }
        }

        return 0;

    }

    private static int solve2(Scanner input, int r, int c, int h, int v) {
        int g[][] = new int[r][c];

        int totalChips = 0;

        for (int i = 0; i < r; i++) {
            char[] ch = input.next().toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if (ch[j] == '.') {
                    g[i][j] = 0;
                } else {
                    g[i][j] = 1;
                    totalChips++;
                }
            }
        }

        int parts = (h + 1) * (v + 1);

        if (totalChips % parts != 0) {
            return 0;
        }

        int equal = totalChips / parts;
        int equalPerRow = totalChips / (h + 1);
        int equalPerCol = totalChips / (v + 1);

        int[] ha = new int[h + 2];
        int[] va = new int[v + 2];

        int currSum = 0;
        int cut = 1;
        ha[0] = -1;
        for (int i = 0; i < r && cut < h + 2; i++) {
            currSum += getRowSum(g, i, c);
            if (currSum > equalPerRow) {
                return 0;
            } else if (currSum == equalPerRow) {
                ha[cut] = i;
                cut++;
                currSum = 0;
            }
        }

        currSum = 0;
        cut = 1;
        va[0] = -1;
        for (int j = 0; j < c && cut < v + 2; j++) {
            currSum += getColSum(g, j, r);
            if (currSum > equalPerCol) {
                return 0;
            } else if (currSum == equalPerCol) {
                va[cut] = j;
                cut++;
                currSum = 0;
            }
        }

        for (int row = 0; row <= h; row++) {
            for (int col = 0; col <= v; col++) {
                int sum = 0;
                for (int i = ha[row] + 1; i <= ha[row + 1]; i++) {
                    for (int j = va[col] + 1; j <= va[col + 1]; j++) {
                        sum += g[i][j];
                    }
                }
                if (sum != equal) {
                    return 0;
                }
            }
        }

        return 1;

    }

    private static int getColSum(int[][] g, int col, int r) {
        int sum = 0;
        for (int i = 0; i < r; i++) {
            sum += g[i][col];
        }
        return sum;
    }

    private static int getRowSum(int[][] g, int row, int c) {
        int sum = 0;
        for (int j = 0; j < c; j++) {
            sum += g[row][j];
        }
        return sum;
    }
}
