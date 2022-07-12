package leetcode;

public class MaxPointsOnALine149 {

    public int maxPoints(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }
        int max = 2;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int y = points[j][0] - points[i][0];
                int x = points[j][1] - points[i][1];
                int c = points[i][1] * points[j][0] - points[j][1] * points[i][0];
                int count = 2;
                for (int p = j + 1; p < points.length; p++) {
                    if (y * points[p][1] == (x * points[p][0] + c)) {
                        count++;
                    }
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(new MaxPointsOnALine149().maxPoints(points));
    }

}
