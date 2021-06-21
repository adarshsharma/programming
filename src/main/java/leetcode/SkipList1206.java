package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SkipList1206 {

    static class Node {

        int val;
        Node left, right, up, down;

        public Node(int val) {
            this.val = val;
        }
    }

    static class DLL {

        Node head;

        public DLL() {
            this.head = new Node(Integer.MIN_VALUE);
        }

        public void addAfter(
            Node first, Node second) {
            if (first.right != null) {
                second.right = first.right;
                first.right = second;
            } else {
                throw new RuntimeException("should never reach here");
            }
        }
    }

    List<DLL> lists;
    public SkipList1206() {
        lists = new ArrayList<>();
    }

    public boolean search(int target) {
        return false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {
        return false;
    }

    public static void main(String[] args) {

    }

}
