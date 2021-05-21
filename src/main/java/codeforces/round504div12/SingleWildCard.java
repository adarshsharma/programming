package codeforces.round504div12;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 17/08/18.
 */
public class SingleWildCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        if(m<n-1) {
            System.out.println("NO");
        } else {
            String s = sc.next();
            String t = sc.next();
            int i=0;
            int j = 0;
            boolean found = false;
            while(i<n && j<m) {
                if(s.charAt(i) == '*') {
                    found = true;
                    break;
                } else if(s.charAt(i) != t.charAt(j)) {
                    System.out.println("NO");
                    return;
                }
                i++;j++;
            }

            if(!found) {
                if(i==n && j==m) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
                return;
            } else {
                int ie = n-1;
                int je = m-1;
                while(ie>i && je>=j) {
                    if(s.charAt(ie) != t.charAt(je)) {
                        System.out.println("NO");
                    }
                    ie--;je--;
                }
                if(ie==i) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
