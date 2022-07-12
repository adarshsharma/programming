package interviewBit;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 05/07/18.
 */
public class SingleNumberII {

    public int singleNumber(final List<Integer> A) {
        int result = 0;
        for (int i = 0; i < 31; i++) {
            int count = 0;
            int num = 1 << i;
            for (int j = 0; j < A.size(); j++) {
                if ((A.get(j) & num) > 0) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                result += num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumberII().singleNumber(Arrays.asList(2, 3, 3, 2, 1, 2, 3)));

    }
}
