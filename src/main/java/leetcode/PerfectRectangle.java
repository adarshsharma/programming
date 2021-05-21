package leetcode;

/**
 * Created by adarsh.sharma on 01/09/18.
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] recs) {
        int[] corners = new int[4];
        corners[0] = Integer.MAX_VALUE;
        corners[1] = Integer.MAX_VALUE;
        corners[2] = Integer.MIN_VALUE;
        corners[3] = Integer.MIN_VALUE;

        long area = 0;

        for(int i=0;i<recs.length;i++) {
            for(int j=0;j<2;j++) {
                corners[j] = Math.min(corners[j], recs[i][j]);
            }
            for(int j=2;j<4;j++) {
                corners[j] = Math.max(corners[j], recs[i][j]);
            }
            area+=((recs[i][3]-recs[i][1]) *(recs[i][2]-recs[i][0]));
        }

        if ((corners[3]-corners[1]) * (corners[2]-corners[0]) != area) {
            return false;
        }

        // Arrays.stream(corners).forEach(System.out::println);

        int m = corners[2]-corners[0];
        int n = corners[3]-corners[1];
        boolean[][] nr = new boolean[m][n];

        for(int r=0;r<recs.length;r++) {
            int[] rec = recs[r];
            for(int i=rec[0]-corners[0];i<rec[2]-corners[0];i++) {
                for(int j=rec[1]-corners[1];j<rec[3]-corners[1];j++) {
                    if(nr[i][j]) {
                        return false;
                    }
                    nr[i][j] = true;
                }
            }
        }


        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(!nr[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PerfectRectangle perfectRectangle = new PerfectRectangle();
        int[][] recs = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
        System.out.println(perfectRectangle.isRectangleCover(recs));
    }
}
