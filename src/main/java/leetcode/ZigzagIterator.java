package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 30/07/18.
 */
public class ZigzagIterator {
    Deque<Iterator<Integer>> dq;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.dq = new LinkedList<>();
        if(v1!=null) {
            dq.addLast(v1.iterator());
        }
        if(v2!=null) {
            dq.addLast(v2.iterator());
        }
    }

    public Integer next() {
        if(hasNext()) {
            Iterator<Integer> it = dq.pollFirst();
            Integer res = it.next();
            if(it.hasNext()) {
                dq.addLast(it);
            }
            return res;
        }

        return null;
    }

    public boolean hasNext() {
        while(!dq.isEmpty()) {
            if(dq.peekFirst().hasNext()) {
                return true;
            } else {
                dq.removeFirst();
            }
        }

        return false;
    }

    public static void main(String[] args) {
        List<Integer> l1 = Collections.emptyList();
        List<Integer> l2 = Arrays.asList(1);
        ZigzagIterator zigzagIterator = new ZigzagIterator(l1, l2);
        while(zigzagIterator.hasNext()) {
            System.out.println(zigzagIterator.next());
        }
    }
}

