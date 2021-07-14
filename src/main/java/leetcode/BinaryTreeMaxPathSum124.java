package leetcode;


import common.TreeNode;

public class BinaryTreeMaxPathSum124 {


    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    // public int maxPathSum(TreeNode root) {
    //     return sum(root)[0];
    // }
    //
    // private int[] sum(TreeNode root) {
    //     if (root == null) {
    //         return new int[]{0, 0};
    //     }
    //
    //     int[] l = sum(root.left);
    //     int[] r = sum(root.right);
    //
    //     int maxThroughThis = root.val;
    //     if (root.left != null && l[1] > 0) {
    //         maxThroughThis += l[1];
    //     }
    //     if (root.right != null && r[1] > 0) {
    //         maxThroughThis += r[1];
    //     }
    //     if (root.left != null) {
    //         maxThroughThis = Math.max(maxThroughThis, l[0]);
    //     }
    //     if (root.right != null) {
    //         maxThroughThis = Math.max(maxThroughThis, r[0]);
    //     }
    //
    //     int maxPathThroughThis = root.val;
    //     if (root.left != null) {
    //         maxPathThroughThis = Math.max(maxPathThroughThis, root.val + l[1]);
    //     }
    //     if (root.right != null) {
    //         maxPathThroughThis = Math.max(maxPathThroughThis, root.val + r[1]);
    //     }
    //
    //     return new int[]{maxThroughThis, maxPathThroughThis};
    // }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(5);
        r.left = new TreeNode(4);
        r.left.left = new TreeNode(11);
        r.left.left.left = new TreeNode(7);
        r.left.left.right = new TreeNode(2);
        r.right = new TreeNode(8);
        r.right.left = new TreeNode(13);
        r.right.right = new TreeNode(4);
        r.right.right.right = new TreeNode(1);
        System.out.println(new BinaryTreeMaxPathSum124().maxPathSum(r));
    }

}
