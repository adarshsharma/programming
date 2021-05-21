package interviewBit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adarsh.sharma on 17/08/18.
 */
public class OrderOfPeopleHeights {
    class H {
        int h;
        int i;

        public H(int h, int i) {
            this.h = h;
            this.i = i;
        }
    }

    public int[] order(int[] A, int[] B) {
        int n = A.length;
        int k = getTwoPower(n);
        int[] segArray = new int[2 * k];
        Arrays.fill(segArray, k, k + n, 1);
        for (int i = k - 1; i > 0; i--) {
            segArray[i] = segArray[2 * i] + segArray[2 * i + 1];
        }

        H[] hArray = new H[n];
        for (int i = 0; i < n; i++) {
            hArray[i] = new H(A[i], B[i]);
        }
        Arrays.sort(hArray, Comparator.comparingInt(o -> o.h));

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[fillIthIndex(segArray, hArray[i].i, k)] = hArray[i].h;
        }

        return result;
    }

    private int getTwoPower(int n) {
        return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(n - 1));
    }

    private int fillIthIndex(int[] segArray, int count, int k) {
        int start = 1;

        while (start < k) {
            if (segArray[2 * start] <= count) {
                count -= segArray[2 * start];
                start = 2 * start + 1;
            } else {
                start = 2 * start;
            }
        }

        segArray[start] = 0;
        int i = start / 2;
        while (i > 0) {
            segArray[i] = segArray[2 * i] + segArray[2 * i + 1];
            i /= 2;
        }

        return start - k;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6};
        int[] B = {3, 2, 1, 2, 0, 0};
//        int[] A = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] B = {3, 2, 1, 2, 0, 0, 1, 0};
        OrderOfPeopleHeights orderOfPeopleHeights = new OrderOfPeopleHeights();
        List<Integer> orderList = Arrays.stream(orderOfPeopleHeights.order(A, B)).boxed().collect(Collectors.toList());
        System.out.println(orderList);
    }
}
