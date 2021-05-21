package leetcode;

/**
 * Created by adarsh.sharma on 27/08/18.
 */
public class New21Game {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W - 1) return 1;
        double dp[] = new double[N + 1],  Wsum = 1, res = 0;
        dp[0] = 1;
        for (int i = 1; i <= N; ++i) {
            dp[i] = Wsum / W;
            if (i < K) Wsum += dp[i]; else res += dp[i];
            if (i - W >= 0) Wsum -= dp[i - W];
        }
        return res;
    }

    public static void main(String[] args) {
        New21Game new21Game = new New21Game();
        System.out.println(new21Game.new21Game(6, 1, 10));
    }
}
