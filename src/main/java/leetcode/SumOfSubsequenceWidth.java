package leetcode;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by adarsh.sharma on 23/08/18.
 */
public class SumOfSubsequenceWidth {
    public int sumSubseqWidths(int[] A) {
        BigInteger p = new BigInteger("1000000007");
        BigInteger two = new BigInteger("2");
        Arrays.sort(A);
        BigInteger result = new BigInteger("0");
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int diff = A[j] - A[i];
                BigInteger r = new BigInteger(String.valueOf(diff))
                        .multiply(two.modPow(new BigInteger(String.valueOf(j - i - 1)), p));
                r = r.add(result);
                result = r.mod(p);
            }
        }

        return result.intValue();
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 3};
        System.out.println(new SumOfSubsequenceWidth().sumSubseqWidths(A));
    }
}
