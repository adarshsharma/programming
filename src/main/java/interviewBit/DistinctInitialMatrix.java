package interviewBit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 16/09/18.
 */
public class DistinctInitialMatrix {
    Map<Integer, Long> factMap = new HashMap<>();

    public int cntMatrix(int[] A) {
        long mod = 1000000007L;
        long result = 0;
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        factMap.put(0, 1L);
        factMap.put(1, 1L);

        for (int rows = 1; rows <= n; rows++) {
            if (n % rows == 0) {
                int cols = n / rows;

                boolean sorted = true;
                for (int c = 0; c < cols && sorted; c++) {
                    int r = 1;
                    while (r < rows && sorted) {
                        if (A[c * rows + r] <= A[c * rows + r - 1]) {
                            sorted = false;
                        } else {
                            r++;
                        }
                    }
                }
                if (sorted) {
                    long cur = 1;
                    long fact = getFact(rows, mod);
                    while (cols > 0) {
                        cur = (cur * fact) % mod;
                        cols--;
                    }
                    result = (result + cur) % mod;
                }
            }
        }


        return (int) result;
    }

    private long getFact(int n, long mod) {
        int k = 1;
        while(k<=n) {
            if(!factMap.containsKey(k)) {
                factMap.put(k, (k*factMap.get(k-1))%mod);
            }
            k++;
        }

        return factMap.get(n);
    }

    public static void main(String[] args) {
        System.out.println(new DistinctInitialMatrix().cntMatrix(new int[]{1, 3, 2, 4}));
    }
}
