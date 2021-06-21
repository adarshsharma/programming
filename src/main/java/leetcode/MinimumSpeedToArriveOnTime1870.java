package leetcode;

public class MinimumSpeedToArriveOnTime1870 {

    public int minSpeedOnTime(int[] dist, double hour) {
        int result = Integer.MAX_VALUE;

        int start = 1;
        int end = 10000000;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (isPossible(dist, hour, mid)) {
                result = Math.min(result, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private boolean isPossible(int[] dist, double hour, int speed) {
        double time = dist[dist.length - 1] / (double) speed;
        int i = dist.length - 2;
        while (i >= 0) {
            time += Math.ceil((double)dist[i] / (double)speed);
            i--;
        }

        return (time - hour) <= 0.00000000000001;
    }

    public static void main(String[] args) {
        double hour = 2.01;
        int[] dist = {1, 1, 100000};
        System.out.println(new MinimumSpeedToArriveOnTime1870().minSpeedOnTime(dist, hour));
    }

}
