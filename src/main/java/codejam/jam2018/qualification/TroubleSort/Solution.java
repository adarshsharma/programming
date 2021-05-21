package codejam.jam2018.qualification.TroubleSort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 08/04/18.
 */
public class Solution {
    public static void main(String args[]) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = input.nextInt();
        for (int ks = 1; ks <= T; ks++) {
            int L = input.nextInt();
            TroubleSort(L, input, ks);
        }
    }

    private static void TroubleSort(int L, Scanner input, int ks) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int i = 0; i < L / 2; i++) {
            odd.add(input.nextInt());
            even.add(input.nextInt());
        }
        if (L % 2 == 1) {
            odd.add(input.nextInt());
        }

        System.out.print("Case #" + ks + ": ");

        java.util.Collections.sort(odd);
        java.util.Collections.sort(even);

        List<Integer> total = new ArrayList<>();
        for (int i = 0; i < L / 2; i++) {
            total.add(odd.get(i));
            total.add(even.get(i));
        }
        if (L % 2 == 1) {
            total.add(odd.get(L/2));
        }

        for (int i = 0; i < L-1; i++) {
            if (total.get(i) > total.get(i+1)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("OK");
    }
}
