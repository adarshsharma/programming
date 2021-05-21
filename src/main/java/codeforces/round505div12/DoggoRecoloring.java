package codeforces.round505div12;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by adarsh.sharma on 20/08/18.
 */
public class DoggoRecoloring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String input = sc.next();
        if (input.length() > 26) {
            System.out.println("Yes");
            return;
        }
        Set<Character> set = new HashSet<>();
        for (Character c : input.toCharArray()) {
            if (set.contains(c)) {
                System.out.println("Yes");
                return;
            }
            set.add(c);
        }

        if (set.size() == 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
