package leetcode;

/**
 * Created by adarsh.sharma on 19/08/18.
 */
public class RemoveKDigits {
//    public String removeKdigits(String num, int k) {
//        return remove(num, k, true);
//    }
//
//    private String remove(String num, int k, boolean removeZero) {
//        int n = num.length();
//        if (k >= n) {
//            return "0";
//        }
//        if (k == 0) {
//            return num;
//        }
//
//        int zeros = 0;
//        int nonZeros = 0;
//        int lastZero = -1;
//        int lastNonZeros = 0;
//        for (int i = 0; i < n && nonZeros <= k; i++) {
//            if (num.charAt(i) == '0') {
//                zeros++;
//                lastZero = i;
//                lastNonZeros = nonZeros;
//            } else {
//                nonZeros++;
//            }
//        }
//
//        if (removeZero && zeros > 0) {
//            return remove(num.substring(lastZero + 1), k - lastNonZeros, true);
//        } else {
//            int min = -1;
//            for (int i = 0; i <= k; i++) {
//                if (min == -1 || num.charAt(i) < num.charAt(min)) {
//                    min = i;
//                }
//            }
//            if (k - min == num.length() - min - 1) {
//                return "" + num.charAt(min);
//            }
//            return num.charAt(min) + remove(num.substring(min + 1), k - min, false);
//        }
//    }

    public String removeKdigits(String num, int k) {
        int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while (top > 0 && stk[top-1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stk[top++] = c;
        }
        // find the index of first non-zero digit
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits? "0": new String(stk, idx, digits - idx);
    }

    public static void main(String[] args) {
        System.out.println(new RemoveKDigits().removeKdigits("102300", 2));
    }
}
