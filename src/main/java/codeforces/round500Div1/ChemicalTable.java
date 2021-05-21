package codeforces.round500Div1;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 30/07/18.
 */
/*Not working*/
public class ChemicalTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        boolean[][] p = new boolean[n][m];

        for(int i=0;i<q;i++) {
            p[sc.nextInt()-1][sc.nextInt()-1] = true;
        }

        int row = 0;
        int col = 0;
        for(int i=0;i<n;i++) {
            int j=0;
            for(;j<m;j++) {
                if(p[i][j]) {
                    break;
                }
            }

            if(j==m) {
               row++;
            }
        }
        for(int j=0;j<m;j++) {
            int i=0;
            for(;i<n;i++) {
                if(p[i][j]) {
                    break;
                }
            }

            if(i==n) {
               col++;
            }
        }

        System.out.println(Math.max(row, col));
    }
}
