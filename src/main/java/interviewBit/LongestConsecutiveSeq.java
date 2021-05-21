package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 20/07/18.
 */
public class LongestConsecutiveSeq {
    public static int longestConsecutive(final List<Integer> A) {
        Set<Integer> set = new HashSet<>();
        int l = 0;
        for(int i=0;i<A.size();i++) {
            set.add(A.get(i));
        }

        while(set.size()>0) {
            Iterator<Integer> it = set.iterator();
            int val = it.next();
            int len = 1;
            while(set.size()>0) {
                if(set.contains(val+len)) {
                    set.remove(val+len);
                    len++;
                } else {
                    break;
                }
            }
            while(set.size()>0) {
                if(set.contains(val-1)) {
                    len++;
                    set.remove(val-1);
                    val--;
                } else {
                    break;
                }
            }
            l = Math.max(l, len);
            set.remove(val);
        }

        return l;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(Arrays.asList(100, 4, 200, 1, 3, 2)));
    }
}
