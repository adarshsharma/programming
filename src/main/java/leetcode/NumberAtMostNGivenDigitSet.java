package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 11/09/18.
 */
public class NumberAtMostNGivenDigitSet {
    public int atMostNGivenDigitSet(String[] D, int N) {
        int count = 0;
        int[] darr = new int[D.length];
        for (int i = 0; i < D.length; i++) {
            darr[i] = Integer.parseInt(D[i]);
        }

        int digits = (int) Math.log10(N) + 1;

        for (int i = 1; i < digits; i++) {
            count += Math.pow(darr.length, i);
        }

        while (N > 0) {
            int pow = (int) Math.pow(10, digits - 1);
            int d = N / pow;
            digits--;
            int idx = Arrays.binarySearch(darr, d);
            if (idx < 0) {
                idx = -(idx + 1);
                count += idx * Math.pow(darr.length, digits);
                break;
            } else {
                count += idx * Math.pow(darr.length, digits);
                if (digits == 0) {
                    count++;
                }
                N = N % pow;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NumberAtMostNGivenDigitSet nums = new NumberAtMostNGivenDigitSet();
        int N = 64;
        String[] D = {"3", "4", "5", "6"};
        System.out.println(nums.atMostNGivenDigitSet(D, N));
    }

}
