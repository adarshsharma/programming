package leetcode.contest.weekly243;

public class Problem2 {

    public String maxValue(String n, int x) {
        boolean isNeg = n.charAt(0) == '-';
        boolean inserted = false;
        char[] res = new char[n.length() + 1];
        int i = 0;
        int j = 0;
        if (isNeg) {
            res[0] = '-';
            i++;
            j++;
        }

        while (i < n.length()) {
            if (inserted) {
                res[j++] = n.charAt(i);
                i++;
            } else {
                if ((isNeg && x < (n.charAt(i) - '0')) ||
                        !isNeg && x > (n.charAt(i) - '0')) {
                    res[j++] = (char) (x + '0');
                    inserted = true;
                }
                if (!inserted) {
                    res[j++] = n.charAt(i);
                    i++;
                }
            }
        }

        if(!inserted) {
            res[j] = (char) (x + '0');
        }

        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(new Problem2().maxValue("7951", 4));
    }
}
