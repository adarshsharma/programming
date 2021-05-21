package interviewBit;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 04/07/18.
 */
public class AllocateBooks {
    public int books(List<Integer> A, int B) {
        if(B > A.size()) {
            return -1;
        }

        long end = 0;
        long start = Integer.MIN_VALUE;
        for(Integer i: A) {
            end+=i;
            if(i > start) {
                start = i;
            }
        }
        long min = 0;
        while(start <= end) {
            long mid = (start + end) / 2;
            boolean possible = dist(A, B, mid);
            if(possible) {
                min = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return (int)min;
    }

    private boolean dist(List<Integer> A, int B, long mid) {
        int student = 0;
        int sum = 0;
        for(int i=0;i<A.size();i++) {
            if(sum + A.get(i) <= mid) {
                sum+=A.get(i);
            } else {
                sum = A.get(i);
                student++;
                if(student>B) {
                    return false;
                }
            }
        }

        if(sum>0) {
            student++;
            if(student>B) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(12, 34, 67, 90);
        int B = 2;
        System.out.println(new AllocateBooks().books(A, B));
    }
}
