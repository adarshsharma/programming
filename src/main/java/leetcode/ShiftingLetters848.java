package leetcode;

/**
 * Created by adarsh.sharma on 19/06/18.
 */
public class ShiftingLetters848 {
    public String shiftingLetters(String S, int[] shifts) {
        int[] f = new int[S.length()];
        int sum = 0;
        for (int i = shifts.length - 1; i >= 0; i--) {
            sum = (sum + shifts[i] % 26) % 26;
            f[i] = sum;
        }

        char[] res = new char[S.length()];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) + f[i] > 'z') {
                f[i] -= ('z' - S.charAt(i));
                res[i] = (char) ('a' + f[i] - 1);
            } else {
                res[i] = (char) (S.charAt(i) + f[i]);
            }
        }

        return new String(res);
    }

    public String shiftingLettersOld(String S, int[] shifts) {
        int[] f = new int[S.length()];
        for (int i = 0; i < shifts.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                f[j] = (f[j] + shifts[i] % 26) % 26;
            }
        }

        char[] res = new char[S.length()];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) + f[i] > 'z') {
                f[i] -= ('z' - S.charAt(i));
                res[i] = (char) ('a' + f[i] - 1);
            } else {
                res[i] = (char) (S.charAt(i) + f[i]);
            }
        }

        return new String(res);
    }


    public static void main(String[] args) {
        ShiftingLetters848 s = new ShiftingLetters848();
        int[] shifts = {3, 5, 9};
        String s1 = s.shiftingLetters("abc", shifts);
        System.out.println(s1);
    }
}
