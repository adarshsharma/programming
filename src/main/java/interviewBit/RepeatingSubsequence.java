package interviewBit;

/**
 * Created by adarsh.sharma on 19/07/18.
 */
public class RepeatingSubsequence {
    public int anytwo(String A) {
        if(A==null) {
            return 0;
        }
        int n = A.length();
        int[] dp = new int[n+1];

        for(int i=1;i<=n;i++) {
            int[] newdp = new int[n+1];
            for(int j=1;j<=n;j++) {
                if(i!=j && A.charAt(i-1)==A.charAt(j-1)) {
                    newdp[j] = dp[j-1] + 1;
                } else {
                    newdp[j] = Math.max(dp[j], newdp[j-1]);
                }
            }
            dp = newdp;
        }

        if(dp[n] > 1) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new RepeatingSubsequence().anytwo("aaa"));
    }
}
