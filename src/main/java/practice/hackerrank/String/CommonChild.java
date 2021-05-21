package practice.hackerrank.String;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 06/12/17.
 */
public class CommonChild {
    static int commonChild(String s1, String s2){
        int n = s1.length();
        int[] p;
        int[] c = new int[n+1];

        for(int i=1;i<=n;i++){
            p = c;
            c = new int[n+1];
            for(int j=1;j<=n;j++){
                if(s1.charAt(j-1) != s2.charAt(i-1)) {
                    c[j] = Math.max(c[j-1], p[j]);
                } else {
                    c[j] = p[j-1] + 1;
                }
            }

        }

        return c[n-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int result = commonChild(s1, s2);
        System.out.println(result);
    }
}
