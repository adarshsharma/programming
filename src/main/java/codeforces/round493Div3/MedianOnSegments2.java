package codeforces.round493Div3;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 10/07/18.
 */
public class MedianOnSegments2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        int m = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        System.out.println(greaterCount(A, n, m) - greaterCount(A, n, m + 1));
    }

    private static long greaterCount(int A[], int n, int m) {
        int s[] = new int[2 * n + 1];
        int sum = n;
        long count = 0;
        s[sum] = 1;
        long add = 0;

        for (int i = 0; i < n; i++) {
            if (A[i] < m) {
                sum--;
                add -= s[sum];
            } else {
                add += s[sum];
                sum++;
            }
            count += add;
            s[sum]++;
        }
        return count;
    }
}
