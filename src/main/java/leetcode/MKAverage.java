package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class MKAverage {

    Deque<Integer> dq;
    TreeMap<Integer, Integer> mq;
    TreeMap<Integer, Integer> sq;
    TreeMap<Integer, Integer> bq;
    int m, k;
    int sum = 0;
    int sc, mc, bc = 0;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        dq = new ArrayDeque<>();
        mq = new TreeMap<>();
        sq = new TreeMap<>();
        bq = new TreeMap<>();
    }

    public void addElement(int num) {
        if (dq.size() == m) {
            int rem = dq.pollFirst();
            if (rem <= sq.lastKey()) {
                sq.computeIfPresent(rem, removeIfLast());

                sq.merge(mq.firstKey(), 1, Integer::sum);
                sum -= mq.firstKey();
                mq.computeIfPresent(mq.firstKey(), removeIfLast());

                mq.merge(bq.firstKey(), 1, Integer::sum);
                sum += bq.firstKey();
                bq.computeIfPresent(bq.firstKey(), removeIfLast());
            } else if (rem <= mq.lastKey()) {
                sum -= rem;
                mq.computeIfPresent(rem, removeIfLast());

                mq.merge(bq.firstKey(), 1, Integer::sum);
                sum += bq.firstKey();
                bq.computeIfPresent(bq.firstKey(), removeIfLast());
            } else {
                bq.computeIfPresent(rem, removeIfLast());
            }
            bc--;
        }

        dq.addLast(num);
        sq.merge(num, 1, Integer::sum);
        sc++;

        if (sc <= k) {
            return;
        }
        sc--;
        num = sq.lastKey();
        sq.computeIfPresent(sq.lastKey(), removeIfLast());

        mq.merge(num, 1, Integer::sum);
        sum += num;
        mc++;

        if (mc <= (m - 2 * k)) {
            return;
        }

        mc--;
        num = mq.lastKey();
        sum -= num;
        mq.computeIfPresent(mq.lastKey(), removeIfLast());

        bq.merge(num, 1, Integer::sum);
        bc++;
    }

    public int calculateMKAverage() {
        if (dq.size() < m) {
            return -1;
        }
        return sum / (m - 2 * k);
    }


    private static BiFunction<Integer, Integer, Integer> removeIfLast() {
        return (a, b) -> b == 1 ? null : b - 1;
    }

    public static void main(String[] args) {
        MKAverage mk = new MKAverage(3, 1);
        mk.addElement(17612);
        mk.addElement(74607);
        mk.addElement(8272);
        mk.addElement(33433);
        mk.addElement(15456);
        mk.addElement(64938);
        System.out.println(mk.calculateMKAverage());

    }

}
