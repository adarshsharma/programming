package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by adarsh.sharma on 11/09/18.
 */
public class StockSpanner {
    Integer[] indexes = new Integer[150000];
    Integer[] values = new Integer[150000];
    int cur;
    int size;

    public StockSpanner() {
        cur = -1;
        size = 0;
    }

    public int next(int price) {
        int idx = Arrays.binarySearch(values, 0, size, price, Comparator.reverseOrder());

        cur++;
        if (idx < 0) {
            idx = -(idx + 1);

        }
        int ret = cur - (idx == 0 ? -1 : indexes[idx-1]);

        values[idx] = price;
        indexes[idx] = cur;

        size = idx + 1;

        return ret;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(76));
        System.out.println(stockSpanner.next(85));
    }
}
