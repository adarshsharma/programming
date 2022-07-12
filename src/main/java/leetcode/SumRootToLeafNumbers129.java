package leetcode;

import common.TreeNode;

public class SumRootToLeafNumbers129 {

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    private int sum(TreeNode root, int val) {
        if (root.left == null && root.right == null) {
            return val * 10 + root.val;
        }

        int s = 0;
        if (root.left != null) {
            s += sum(root.left, val * 10 + root.val);
        }
        if (root.right != null) {
            s += sum(root.right, val * 10 + root.val);
        }
        return s;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        r.left.left = new TreeNode(3);
        r.left.right = new TreeNode(4);
        r.left.right.right = new TreeNode(5);
        System.out.println(new SumRootToLeafNumbers129().sumNumbers(r));
    }

}
