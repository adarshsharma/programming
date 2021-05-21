package codejam.jam2018.oneC.awholenewworld;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by adarsh.sharma on 14/04/18.
 */
public class Solution {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner input = new Scanner(new BufferedReader(new FileReader("/Users/adarsh.sharma/code/test/src/main/resources/oneC1.csv")));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            System.out.print("Case #" + ks + ":");
            String word = solve(input);
            System.out.println(word);
        }
    }

    private static String solve(Scanner input) {
        int n = input.nextInt();
        int l = input.nextInt();

        Set<String> existingWords = new HashSet<>();

        for (int i = 0; i < n; i++) {
            existingWords.add(input.next());
        }

        return "-";
    }
}
