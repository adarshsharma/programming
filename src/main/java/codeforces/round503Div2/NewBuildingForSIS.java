package codeforces.round503Div2;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 11/08/18.
 */
public class NewBuildingForSIS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int h = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int k = scanner.nextInt();

        while(k > 0) {
            int ta = scanner.nextInt();
            int fa = scanner.nextInt();
            int tb = scanner.nextInt();
            int fb = scanner.nextInt();

            if(ta==tb) {
                System.out.println(Math.abs(fa-fb));
            } else {
                int s = Math.abs(ta - tb);

                if(fb < a) {
                    s+=Math.abs(fa-a);
                    s+=Math.abs(fb-a);
                } else if(fb > b) {
                    s+=Math.abs(fa-b);
                    s+=Math.abs(fb-b);
                } else {
                    s += Math.abs(fb - fa);
                }

                System.out.println(s);
            }

            k--;
        }
    }
}
