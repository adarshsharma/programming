package leetcode;

/**
 * Created by adarsh.sharma on 04/09/18.
 */
public class ReorderList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = reverse(slow.next);
        slow.next = null;
        ListNode temp = head;

        while (mid != null) {
            ListNode n = mid.next;
            mid.next = temp.next;
            temp.next = mid;
            mid = n;
            temp = temp.next.next;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = head;
        ListNode next = head.next;
        newHead.next = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = newHead;
            newHead = next;
            next = temp;
        }

        return newHead;
    }

    private void print(ListNode n) {
        while(n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reorderList.reorderList(head);
        reorderList.print(head);
    }
}
