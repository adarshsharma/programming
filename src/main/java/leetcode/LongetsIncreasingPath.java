package leetcode;

/**
 * Created by adarsh.sharma on 21/08/18.
 */
public class LongetsIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] dp = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j] = true;
            }
        }

        int l=1;
        boolean found = true;

        while(l<=m*n && found) {
            found = false;
            boolean[][] newdp = new boolean[m][n];
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    newdp[i][j] = newdp[i][j] | ifLower(dp, matrix, i, j, i, j-1);
                    newdp[i][j] = newdp[i][j] | ifLower(dp, matrix, i, j, i, j+1);
                    newdp[i][j] = newdp[i][j] | ifLower(dp, matrix, i, j, i-1, j);
                    newdp[i][j] = newdp[i][j] | ifLower(dp, matrix, i, j, i+1, j);
                    if(newdp[i][j]) {
                        found = true;
                    }
                }
            }

            dp = newdp;
            if(found) {
                l++;
            }
        }

        return l;
    }

    private boolean ifLower(boolean[][] dp, int[][] matrix, int i, int j, int r, int c) {
        if(r<0 || r>=matrix.length || c<0 || c>=matrix[0].length) {
            return false;
        }

        return dp[r][c] && (matrix[i][j] > matrix[r][c]);
    }

    public static void main(String[] args) {
        int[][] matrix = {{9,9,4},
                          {6,6,8},
                          {2,1,1}};
        System.out.println(new LongetsIncreasingPath().longestIncreasingPath(matrix));
    }
}
