package leetcode;

/**
 * Created by adarsh.sharma on 31/08/18.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n1 + n2 != n3) {
            return false;
        }

        boolean[] m = new boolean[n2 + 1];

        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if(i==0 && j==0) {
                    m[j] = true;
                } else if(i==0) {
                    m[j] = m[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if(j==0) {
                    m[j] = m[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    m[j] = (m[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (m[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return m[n2];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(new InterleavingString().isInterleave(s1, s2, s3));
    }
}
