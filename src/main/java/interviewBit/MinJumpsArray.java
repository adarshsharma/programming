package interviewBit;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 16/07/18.
 */
public class MinJumpsArray {
    public int jump(List<Integer> A) {

        if (A == null || (A.size() > 1 && A.get(0) == 0))
            return -1;

        if (A.size() == 1)
            return 0;

        int count = A.get(0);
        int n = A.size();
        int max = 0;
        int steps = 0;

        for (int i = 1; i < n; i++) {
            count--;
            max--;
            max = Math.max(max, A.get(i));

            if (i == n - 1) {
                return steps + 1;
            }

            if (count == 0) {
                if (max < 0) {
                    return -1;
                }

                count = max;
                max = 0;
                steps++;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        System.out.println(new MinJumpsArray().jump(Arrays.asList(2, 2, 2, 0, 1, 4)));
    }
}
