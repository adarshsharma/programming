package leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by adarsh.sharma on 17/09/18.
 */
public class NextClosestTime {
    public String nextClosestTime(String time) {
        Set<Integer> allowed = new HashSet();
        for (char c : time.toCharArray())
            if (c != ':') {
                allowed.add(c - '0');
            }

        TreeSet<Integer> mtst = new TreeSet<>();
        TreeSet<Integer> htst = new TreeSet<>();

        for (int d1 : allowed) {
            for (int d2 : allowed) {
                int val = d1 * 10 + d2;
                if (val < 24) {
                    htst.add(val);
                }
                if (val < 60) {
                    mtst.add(val);
                }
            }
        }
        Integer min = Integer.parseInt(time.substring(3));
        Integer hour = Integer.parseInt(time.substring(0, 2));

        Integer bmin = mtst.higher(min);
        Integer bhour = htst.higher(hour);
        if (bmin != null && bmin < 60) {
            return String.format("%02d:%02d", hour, bmin);
        } else if (bhour != null && bhour < 24) {
            return String.format("%02d:%02d", bhour, mtst.first());
        } else {
            return String.format("%02d:%02d", htst.first(), mtst.first());
        }
    }

    public static void main(String[] args) {

    }
}
