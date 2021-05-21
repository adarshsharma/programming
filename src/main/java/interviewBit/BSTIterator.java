package interviewBit;

import java.util.Stack;

/**
 * Created by adarsh.sharma on 09/07/18.
 */
public class BSTIterator {
    Stack<TreeNode> stk;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public BSTIterator(TreeNode root) {
        stk = new Stack<>();

        if(root != null) {
            while(root != null) {
                stk.push(root);
                root = root.left;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stk.empty();
    }

    /** @return the next smallest number */
    public int next() {
        if(hasNext()) {
            TreeNode res = stk.pop();
            if(res.right != null) {
                TreeNode cur = res.right;
                while(cur != null) {
                    stk.push(cur);
                    cur = cur.left;
                }
            }

            return res.val;
        }

        throw new IllegalArgumentException("No more elements");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(11);
        root.right.right.left = new TreeNode(10);
        root.right.right.right = new TreeNode(12);
        root.right.right.right.right = new TreeNode(13);
        root.right.left = new TreeNode(8);
        root.right.left.left = new TreeNode(7);
        root.right.left.left.left = new TreeNode(5);
        root.right.left.left.left.right = new TreeNode(6);

        BSTIterator bstIterator = new BSTIterator(root);
        while(bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }
}
