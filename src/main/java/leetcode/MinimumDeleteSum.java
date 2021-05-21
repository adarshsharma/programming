package leetcode;

/**
 * Created by adarsh.sharma on 30/08/18.
 */
public class MinimumDeleteSum {
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        int[] dp = new int[n2+1];
        dp[0] = 0;

        for(int j=0;j<n2;j++) {
            dp[j+1] = dp[j] + s2.codePointAt(j);
        }

        for(int i=1;i<=n1;i++) {
            int[] newDP = new int[n2+1];
            newDP[0]= dp[0] + s1.codePointAt(i-1);
            for(int j=1;j<=n2;j++) {
                if(s1.charAt(i-1) == s2.codePointAt(j-1)) {
                    newDP[j] = dp[j-1];
                } else {
                    newDP[j] = Math.min(s1.codePointAt(i-1) + dp[j], s2.codePointAt(j-1) + newDP[j-1]);
                }
            }
            dp = newDP;
        }

        return dp[n2];
    }

    public static void main(String[] args) {
        MinimumDeleteSum minimumDeleteSum = new MinimumDeleteSum();
        System.out.println(minimumDeleteSum.minimumDeleteSum("eet", "ete"));
    }
}
