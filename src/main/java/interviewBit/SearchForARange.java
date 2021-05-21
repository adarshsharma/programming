package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 04/07/18.
 */
public class SearchForARange {
    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        int start = 0;
        int end = a.size() - 1;
        int low = -1;
        int high = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (a.get(mid) == b) {
                low = mid;
                high = mid;
                break;
            } else if (a.get(mid) < b) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (start <= end) {
            int s = start;
            int e = low - 1;
            while (s <= e) {
                int m = (s + e) / 2;
                if (a.get(m) == b) {
                    low = m;
                    e = m - 1;
                } else {
                    s = m + 1;
                }
            }
            s = high + 1;
            e = end;
            while (s <= e) {
                int m = (s + e) / 2;
                if (a.get(m) == b) {
                    high = m;
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(low);
        result.add(high);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3,
                3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
                7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        System.out.println(new SearchForARange().searchRange(a, 10));
    }
}
