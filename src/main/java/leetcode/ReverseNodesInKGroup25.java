package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReverseNodesInKGroup25 {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        ListNode tHead = new ListNode();

        ListNode pTail = tHead;
        ListNode nHead = head;

        while (nHead != null) {
            ListNode[] result = reverseK(nHead, k);
            pTail.next = result[0];
            pTail = result[1];
            nHead = result[2];
        }

        return tHead.next;
    }

    private ListNode[] reverseK(ListNode head, int k) {
        int i = 1;
        ListNode last = head;
        while (i < k && last != null) {
            last = last.next;
            i++;
        }
        ListNode[] res = new ListNode[3];
        if (last == null) {
            res[0] = head;
        } else {
            res[2] = last.next;
            res[1] = head;
            res[0] = last;

            ListNode first = head;
            ListNode second = head.next;
            ListNode prev = null;

            while (first != res[2]) {
                first.next = prev;
                prev = first;
                first = second;
                if (second != null) {
                    second = second.next;
                }
            }
        }

        return res;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);

        System.out.println(printL(head));
        System.out.println(printL(new ReverseNodesInKGroup25().reverseKGroup(head, 2)));
    }

    private static List<Integer> printL(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }

}
