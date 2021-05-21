package codeforces.round516Div2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 14/10/18.
 */
public class MakeATriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] sides = new int[3];
        sides[0] = sc.nextInt();
        sides[1] = sc.nextInt();
        sides[2] = sc.nextInt();

        Arrays.sort(sides);
        if (sides[2] < sides[0] + sides[1]) {
            System.out.println("0");
        } else {
            System.out.println(sides[2] - sides[0] - sides[1] + 1);
        }
    }
}
