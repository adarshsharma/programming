package interviewBit;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by adarsh.sharma on 20/07/18.
 */
public class WindowString {
    public String minWindow(String S, String T) {
        if (T.length() == 0) {
            return "";
        }
        int minStart = -1;
        int minL = Integer.MAX_VALUE;
        Deque<Integer> q = new LinkedList<>();
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> smap = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            tmap.merge(T.charAt(i), 1, (a, b) -> a + b);
            smap.put(T.charAt(i), 0);
        }
        int rlength = T.length();
        int len = 0;

        for (int i = 0; i < S.length(); i++) {
            Character c = S.charAt(i);
            if (tmap.containsKey(c)) {
                if (tmap.get(c) > smap.get(c)) {
                    q.addLast(i);
                    smap.merge(c, 1, (a, b) -> a + b);
                    len++;

                    if (len == rlength) {
                        int l = i - q.peekFirst() + 1;
                        if (l < minL) {
                            minStart = q.peekFirst();
                            minL = l;
                        }
                        Integer idx = q.pollFirst();
                        len--;
                        smap.merge(S.charAt(idx), -1, (a, b) -> a + b);
                        removeExtra(S, q, tmap, smap);
                    }
                } else {
                    q.addLast(i);
                    smap.merge(S.charAt(i), 1, (a, b) -> a + b);
                    removeExtra(S, q, tmap, smap);
                }
            }
        }

        if (minStart == -1) {
            return "";
        }

        return S.substring(minStart, minStart + minL);
    }

    private void removeExtra(String S, Deque<Integer> q, Map<Character, Integer> tmap, Map<Character, Integer> smap) {
        while (!q.isEmpty()) {
            Integer idx = q.peekFirst();
            char r = S.charAt(idx);
            if (smap.get(r) > tmap.get(r)) {
                q.pollFirst();
                smap.merge(r, -1, (a, b) -> a + b);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new WindowString().minWindow("qvsZNaUdHrD4sszuivVRCHMs7X8HuZOyvheLBuNBfhq5jHquZt3XRdVlogE5Ir1LRPFCqS8k4N2i1fNqsJKAi43s89vRJZ6U9zYXulUHLitEmvUlub3EyZBWEuV74tlXmD93ecMpVADjYQMJUaXYU7H9oxNM4qvg10iBqx3d2wI8lzUM8TnTKJpl4Rg5CEHcsvWyvfBMumgnKL5brqJjDEv2wad5pQFTOJZc8vygwxjdFU2OBVGNjTHThapWcIIXsXuhScb2d8yb6HrDEmASPzskd4BvnFLlWYBBtvWSUBXaDFtcB1Ja1oHIsSoMV4M79rMQcuga2lSrClEvGeVImaTxYs86uz4Zf4HgTs2tZNgSYl9X67vijJ765F0twMLtDUEbIdvpnyaLWPz04TvSp8YTmQG8AsZ6ghl9Gtu4hBx8V6hyRuCh7qHFl1ehOaGXkwwR2RoNgKqhqQKUKbKVekwQoAn613IK7cy0KIoG2qlrgjmTxvW4PwKapinDwQymNCCZQzl3xuC2LewGCh0PRhZDp0TkcRF16lekZDjn3CCHD8ZFqye3n6XtYXnVa2GsmlrpLr2AJGWTykR7OlS2MqY5MhtKEzTaV4ZXVwgX9vOjNkpKENtVJL7A8XzVq2KhsANMQbJQ7G45Sb5qODlCUMNpSbo4zNxs4JBiqFY2D5IeOt515aVh1XG94NpFEd", "B4f"));
//        System.out.println(new WindowString().minWindow("4sszuivVRCHMs7X8HuZOyvheLBuNBfmvUlub3EyZBWTKJpl4Rg5CEHcsvWyvfBMumgnK", "B4f"));
    }
}
