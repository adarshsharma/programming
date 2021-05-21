package interviewBit;

/**
 * Created by adarsh.sharma on 17/07/18.
 */
public class DistinctSequences {
    public int numDistinct(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n][m];
        dp[0][0] = A.charAt(0)==B.charAt(0)?1:0;
        for(int i=1;i<n;i++) {
            dp[i][0] = dp[i-1][0] + (A.charAt(i)==B.charAt(0)?1:0);
        }

        for(int i=1;i<n;i++) {
            char a = A.charAt(i);
            for(int j=1;j<m;j++) {
                char b = B.charAt(j);
                if(a!=b) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
            }
        }

        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSequences().numDistinct("aababbababbaabbaaababaaabbbaaabbb", "bbababa"));
    }
}
