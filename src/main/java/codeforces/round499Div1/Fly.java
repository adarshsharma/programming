package codeforces.round499Div1;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 27/07/18.
 */
public class Fly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double m = sc.nextDouble();
        double[] takeOff = new double[n];
        double[] land = new double[n];

        double w = m;
        for (int i = 0; i < n; i++) {
            takeOff[i] = sc.nextDouble();
            if (takeOff[i] <= 1) {
                System.out.println(-1);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            land[i] = sc.nextDouble();
            if (land[i] <= 1) {
                System.out.println(-1);
                return;
            }
        }

        w = w * (land[0] / (land[0] - 1));
        for (int i = 1; i < n; i++) {
            w = w * (takeOff[i] / (takeOff[i] - 1));
            w = w * (land[i] / (land[i] - 1));
        }

        for (int i = n - 2; i > 0; i--) {
            w = w * (takeOff[i] / (takeOff[i] - 1));
            w = w * (land[i] / (land[i] - 1));
        }

        w = w * (takeOff[0] / (takeOff[0] - 1));

        System.out.println(w - m);
    }
}
