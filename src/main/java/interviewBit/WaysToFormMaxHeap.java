package interviewBit;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 20/07/18.
 */
public class WaysToFormMaxHeap {
    long[] dp = new long[101];

    public int solve(int A) {
        dp[1] = 1L;
        dp[2] = 1L;
        long mod = 1000000007L;

        if (A < 0) {
            return 0;
        }

        for (int i = 3; i <= A; i++) {
            int lastRowExtra = getLastRowExtra(i);
            int rem = i - 1;
            int right = (rem - lastRowExtra) / 2;
            int left = (rem + lastRowExtra) / 2;
            ArrayList<Long> den = new ArrayList<>();
            ArrayList<Long> countList = new ArrayList<>();
            for (long j = 2; j <= right; j++) {
                den.add(j);
            }
            for (long j = rem; j > rem - right; j--) {
                countList.add(j);
            }

            divide(den, countList);

            long count = 1;
            for (int j = 0; j < countList.size(); j++) {
                count = (count * countList.get(j)) % mod;
            }

            count = (count * dp[right]) % mod;
            count = (count * dp[left]) % mod;
            dp[i] = count;
        }

        return (int) dp[A];
    }

    private void divide(ArrayList<Long> den, ArrayList<Long> countList) {
        boolean changed = true;
        while (changed && den.size() > 0) {
            for (int j = 0; j < countList.size(); j++) {
                if (countList.get(j) % den.get(0) == 0) {
                    countList.set(j, countList.get(j) / den.get(0));
                    den.remove(0);
                    changed = true;
                    break;
                } else {
                    changed = false;
                }
            }
        }

        if (den.size() > 0) {
            long d = den.get(0);
            for (long j = 2; j <= d && d > 1; j++) {
                if (d % j == 0) {
                    for (int i = 0; i < countList.size(); i++) {
                        if (countList.get(i) % j == 0) {
                            countList.set(i, countList.get(i) / j);
                            break;
                        }
                    }
                    if (d / j == 1) {
                        den.remove(0);
                    } else {
                        den.set(0, d / j);
                    }
                    break;
                }
            }
            den.remove(0);
        }
    }

    private int getLastRowExtra(int i) {
        int used = 1;
        while (used < i) {
            used = 2 * used + 1;
        }

        if (i == used) {
            return 0;
        }

        int req = (used + 1) / 2;
        int less = used - i;
        if (less <= req / 2) {
            return less;
        } else {
            return req - less;
        }
    }

    public static void main(String[] args) {
        System.out.println(new WaysToFormMaxHeap().solve(15));
    }
}
