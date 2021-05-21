package leetcode;

/**
 * Created by adarsh.sharma on 18/06/18.
 */
public class MaximizeDistance849 {
    public int maxDistToClosest(int[] seats) {
        int curMax;
        int prev;
        int i = 0;
        while (seats[i] == 0) {
            i++;
        }
        curMax = i;
        prev = i;
        i++;
        while (i < seats.length) {
            if (seats[i] == 1) {
                if (i - prev > 1) {
                    curMax = Math.max(curMax, (i - prev) / 2);
                }
                prev = i;
            }

            i++;
        }

        curMax = Math.max(curMax, (i-1-prev));

        return curMax;

    }

    public static void main(String[] args) {
        int[] seats = {1,1,1,0,0,1,1,1,1,0};//{1, 0, 0, 0};
        MaximizeDistance849 m = new MaximizeDistance849();
        int i = m.maxDistToClosest(seats);
        System.out.println(i);
    }
}
