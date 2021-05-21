package interviewBit;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 05/07/18.
 */
public class PaintersPartitionProblem {
    public int paint(int A, int B, List<Integer> C) {
        int L = 10000003;
        long end = 0;
        long start = Integer.MIN_VALUE;
        for(Integer i: C) {
            end+=i;
            if(i > start) {
                start = i;
            }
        }

        long min = 0;
        while(start <= end) {
            long mid = (start + end) / 2;
            boolean possible = dist(C, A, mid);
            if(possible) {
                min = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        int t = (int)((min*B)%L);

        return t;
    }

    private boolean dist(List<Integer> C, int A, long mid) {
        int painter = 0;
        int sum = 0;
        for(int i=0;i<C.size();i++) {
            if(sum + C.get(i) <= mid) {
                sum+=C.get(i);
            } else {
                sum = C.get(i);
                painter++;
                if(painter>A) {
                    return false;
                }
            }
        }

        if(sum>0) {
            painter++;
            if(painter>A) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int A = 1;
        int B = 1000000;
        List<Integer> C = Arrays.asList(1000000, 1000000);
        System.out.println(new PaintersPartitionProblem().paint(A, B, C));
    }
}
