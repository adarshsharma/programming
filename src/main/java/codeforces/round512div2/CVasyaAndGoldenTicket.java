package codeforces.round512div2;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 26/09/18.
 */
public class CVasyaAndGoldenTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] d = new int[n];
        String str = scanner.next();
        for (int i = 0; i < n; i++) {
            d[i] = str.charAt(i) - '0';
        }
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += d[i];
            int curSum = 0;
            int count = 0;
            int j = i + 1;
            for (; j < n; j++) {
//                if(d[j] == 0) {
                if (curSum + d[j] == sum) {
                    count++;
                    curSum = 0;
                } else if (curSum + d[j] < sum) {
                    curSum += d[j];
                } else {
                    break;
                }
            }
            if (j == n && curSum == 0 && count > 0) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
