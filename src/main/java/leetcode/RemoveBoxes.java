package leetcode;

import practice.algo.linkedlist.MyLinkedList;
import practice.algo.linkedlist.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 07/09/18.
 */
public class RemoveBoxes {
//    class ListNode {
//        int val;
//        ListNode prev;
//        ListNode next;
//
//        public ListNode(int val) {
//            this.val = val;
//        }
//    }

    class Data {
        int val;
        int count;

        public Data(Integer val, Integer count) {
            this.val = val;
            this.count = count;
        }
    }

    public int removeBoxes(int[] boxes) {
        MyLinkedList<Data> myLinkedList = new MyLinkedList<>();
        Map<Integer, Integer> colorCountMap = new HashMap<>();

        for (int box : boxes) {
            colorCountMap.merge(box, 1, Integer::sum);
            if (myLinkedList.getLast().val == box) {
                myLinkedList.getLast().val++;
            } else {
                myLinkedList.linkLast(new Data(box, 1));
            }
        }

        int result = 0;

        Node<Data> firstNode = myLinkedList.getFirstNode();
        while (myLinkedList.size() > 0) {
            while (firstNode != null) {
                Data item = firstNode.getItem();
                if (item.count == colorCountMap.get(item.val)) {
                    result += item.count * item.count;
                    colorCountMap.remove(item.val);
                    myLinkedList.unlink(firstNode);
                    break;
                } else {
                    firstNode = firstNode.getNext();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println(new RemoveBoxes().removeBoxes(boxes));
    }
}
