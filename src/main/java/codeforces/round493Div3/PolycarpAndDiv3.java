package codeforces.round493Div3;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 10/07/18.
 */
public class PolycarpAndDiv3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int count = 0;
        int[] rem = {0, 0, 0};
//        int rem = 0;
        int i = 0;
        while (i < s.length()) {
            int d = s.charAt(i) - '0';
            if (d % 3 == 0) {
                count++;
                reset(rem);
            } else {
                int req = 3 - d % 3;
                if (rem[req] == 1) {
                    count++;
                    reset(rem);
                } else {
                    int mod = d % 3;
                    if (rem[mod] == 1) {
                        rem[req] = 1;
                    } else {
                        rem[mod] = 1;
                    }
                }
            }
            i++;
        }

        System.out.println(count);
    }

    private static void reset(int[] rem) {
        rem[1] = 0;
        rem[2] = 0;
    }
}
