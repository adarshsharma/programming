package leetcode;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ValidNumber65 {

    public boolean isNumber(String s) {
        Pattern p1 = Pattern.compile("[\\+\\-]?[0-9]+[\\.]?[0-9]*");
        if (p1.matcher(s).matches()) {
            return true;
        }
        Pattern p11 = Pattern.compile("[\\+\\-]?[0-9]*[\\.]?[0-9]+");
        if (p11.matcher(s).matches()) {
            return true;
        }

        Pattern p2 = Pattern.compile("[\\+\\-]?[0-9]+");
        if (p2.matcher(s).matches()) {
            return true;
        }

        Pattern p3 = Pattern.compile("[\\+\\-]?[0-9]+[\\.]?[0-9]*[e|E][\\+\\-]?[0-9]+");
        if (p3.matcher(s).matches()) {
            return true;
        }

        Pattern p4 = Pattern.compile("[\\+\\-]?[0-9]*[\\.]?[0-9]+[e|E][\\+\\-]?[0-9]+");
        if (p4.matcher(s).matches()) {
            return true;
        }

        Pattern p5 = Pattern.compile("[\\+\\-]?[0-9]+[e|E][\\+\\-]?[0-9]+");
        if (p5.matcher(s).matches()) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        String[] ins = {"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
            "53.5e93", "-123.456e789"};
        String[] wrongs = {"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"};
        ValidNumber65 vn = new ValidNumber65();
        // System.out.println(vn.isNumber(""));
        // Arrays.stream(ins).forEach(x -> System.out.println(x + ": " + vn.isNumber(x)));
        Arrays.stream(wrongs).forEach(x -> System.out.println(x + ": " + vn.isNumber(x)));
    }

}
