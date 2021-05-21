package interviewBit;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 18/07/18.
 */
public class DifferentBitsSumPairWise {
    public int cntBits(List<Integer> A) {
        long res = 0L;
        long M = 1000000007;

        for(int d=0;d<32;d++) {
            int mask = 1 << d;
            int set = 0;
            int unset = 0;
            for(int i=0;i<A.size();i++) {
                if((A.get(i) & mask) > 0) {
                    set++;
                } else {
                    unset++;
                }
            }

            res = (res + 2*set*unset)%M;
        }

        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(new DifferentBitsSumPairWise().cntBits(Arrays.asList(81, 13, 2, 7, 96)));
    }
}
