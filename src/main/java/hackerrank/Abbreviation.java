package hackerrank;

/**
 * Created by adarsh.sharma on 25/07/18.
 */
public class Abbreviation {
    static String abbreviation(String a, String b) {
        if(a==null && b==null) {
            return "YES";
        }
        if(a==null || b==null) {
            return "NO";
        }
        int m = a.length();
        int n = b.length();

        boolean[] dp = new boolean[m+1];
        boolean[] dpOld = new boolean[m+1];
        dpOld[0] = true;
        for(int j=1;j<=m;j++) {
            dpOld[j] = dpOld[j-1] && ((a.charAt(j-1) & 1<<5) > 0);
        }

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(i>j) {
                    dp[j] = false;
                } else {
                    dp[j] = (((a.charAt(j-1) & 1<<5) > 0) && dp[j-1]) || (dpOld[j-1] && (equalIgnoreCase(a.charAt(j-1), b.charAt(i-1))));
                }
            }
            dpOld = dp;
            dp = new boolean[m+1];
        }

        return dpOld[m]?"YES":"NO";
    }

    private static boolean equalIgnoreCase(char a, char b) {
        return a==b || ((a & 1<<5) > 0 && ((a ^ 1<<5) == b));
    }

    public static void main(String[] args) {
        System.out.println(Abbreviation.abbreviation("daBcd", "ABC"));
    }
}
