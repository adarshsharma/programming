package codeforces.round512div2;

import java.util.Scanner;

/**
 * Created by adarsh.sharma on 26/09/18.
 */
public class AEasyProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean easy = true;
        while(n>0) {
            if(scanner.nextInt() == 1) {
                easy = false;
                break;
            }
            n--;
        }

        if(easy) {
            System.out.println("EASY");
        } else {
            System.out.println("HARD");
        }
    }
}
