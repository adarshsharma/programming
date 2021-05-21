package hackerrank;

public class HighestValuePalindrome {

    public static String highestValuePalindrome(String s, int n, int k) {
        // Write your code here
        int mismatch = 0;
        char[] res = s.toCharArray();

        for (int i = 0; i < n / 2; i++) {
            if (res[i] != res[n - i - 1]) {
                mismatch++;
            }
        }

        if (mismatch > k) {
            return "-1";
        }

        int st = 0;

        while (k > 0 && st < n / 2) {
            if (res[st] != res[n - st - 1]) {
                if ((res[st] == '9' || res[n - st - 1] == '9')) {
                    res[st] = '9';
                    res[n - st - 1] = '9';
                } else {
                    if (k > mismatch) {
                        res[st] = '9';
                        res[n - st - 1] = '9';
                        k--;
                    } else {
                        if (res[st] > res[n - st - 1]) {
                            res[n - st - 1] = res[st];
                        } else {
                            res[st] = res[n - st - 1];
                        }
                    }
                }
                k--;
                mismatch--;
            } else if (k > mismatch + 1 && res[st] != '9') {
                res[st] = '9';
                res[n - st - 1] = '9';
                k -= 2;
            }
            st++;
        }

        if (k > 0 && n % 2 == 1) {
            res[n / 2] = '9';
        }

        return new String(res);
    }

    public static void main(String[] args) {
        String s = "0221";
        System.out.println(highestValuePalindrome(s, s.length(), 2));
    }

}
