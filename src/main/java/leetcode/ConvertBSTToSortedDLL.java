package leetcode;

/**
 * Created by adarsh.sharma on 09/09/18.
 */
public class ConvertBSTToSortedDLL {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        Node dummyHead = new Node(0, null, null);
        Node prev = dummyHead;

        while(root != null) {
            while(root.left != null) {
                root = rotateRight(root);
            }
            prev.right = root;
            root.left = prev;
            prev = prev.right;
            root = root.right;
        }

        Node head = dummyHead.right;
        if(head != null) {
            head.left = prev;
            prev.right = head;
        }
        return head;
    }

    private Node rotateRight(Node node) {
        if(node.left == null) {
            return node;
        }
        Node newRoot = node.left;
        Node tRight = node.left.right;
        node.left.right = node;
        node.left = tRight;
        return newRoot;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.right = new Node(5);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        Node head = new ConvertBSTToSortedDLL().treeToDoublyList(root);
        System.out.println("test");
    }
}
