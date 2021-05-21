package leetcode;

/**
 * Created by adarsh.sharma on 27/07/18.
 */
public class MinRefuelStops871 {
    public int minRefuelStops(int target, int startFuel, int[][] st) {
        int n = st.length;
        if(target<=startFuel) {
            return 0;
        }

        if(n==0 && target > startFuel) {
            return -1;
        }

        int hop = Integer.MAX_VALUE;

        int[][] M = new int[n][n+1];
        for(int i=0;i<n;i++) {
            for(int h=0;h<n+1;h++) {
                M[i][h] = -1;
            }
        }
        for(int i=0;i<n;i++) {
            M[i][1] = startFuel-st[i][0];
            if(M[i][1] >= 0) {
                if(startFuel+st[i][1] >= target) {
                    return 1;
                }
                M[i][1]+=st[i][1];
            }
        }

        for(int h=2;h<=n;h++) {
            for(int i=h-1;i<n;i++) {
                int max = -1;
                for(int k=0;k<i;k++) {
                    if(M[k][h-1] >= 0) {
                        int e = M[k][h-1] - st[i][0] + st[k][0];
                        max = Math.max(max, e);
                    }
                }
                if(max>=0) {
                    M[i][h] = max + st[i][1];
                    if(M[i][h]+st[i][0] >= target) {
                        return h;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int target = 100;
        int startFuel = 25;
        int[][] st = {{25,25},{50,25},{75,25}};
        System.out.println(new MinRefuelStops871().minRefuelStops(target, startFuel, st));
    }
}
