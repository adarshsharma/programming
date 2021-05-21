package leetcode;

/**
 * Created by adarsh.sharma on 17/07/18.
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        if(s == null || s.length()<2) {
            return false;
        }

        int[] c = new int[26];
        for(int i=0;i<s.length();i++) {
            int idx = (int)s.charAt(i) - 'a';
            c[idx]++;
        }

        int f = 0;
        for(int i=0;i<26;i++) {
            if(c[i] != 0) {
                if(f==0) {
                    f = c[i];
                } else {
                    f = gcd(f, c[i]);
                }
            }
        }

        int len = 0;
        for(int i=0;i<26;i++) {
            len+=c[i]/f;
        }

        for(int l = len;l<s.length();) {
            int i=l;
            for(;i<s.length();i++) {
                int base = i%l;
                if(s.charAt(base) != s.charAt(i)) {
                    break;
                }
            }

            if(i == s.length()) {
                return true;
            }

            l+=len;
        }

        return false;
    }

    private int gcd(int a, int b) {
        if(a<b) {
            return gcd(b, a);
        }

        while(a%b != 0) {
            int temp = a%b;
            a = b;
            b = temp;
        }

        return b;
    }

    public static void main(String[] args) {
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abaababaab"));
    }
}
