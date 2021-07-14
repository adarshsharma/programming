package leetcode;


import common.TreeNode;

public class BalancedBinaryTree110 {

    public boolean isBalanced(TreeNode root) {
        return check(root)[0] == 1;
    }

    private int[] check(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};
        }

        int[] l = check(root.left);
        int[] r = check(root.right);
        int[] res = new int[2];
        res[0] = l[0] * r[0] * (Math.abs(l[1] - r[1]) <= 1 ? 1 : 0);
        res[1] = 1 + Math.max(l[1], r[1]);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.right.right.right = new TreeNode(4);
        System.out.println(new BalancedBinaryTree110().isBalanced(root));
    }

}
