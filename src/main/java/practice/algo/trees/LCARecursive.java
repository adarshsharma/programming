package practice.algo.trees;

import common.TreeNode;

/**
 * Created by adarsh.sharma on 09/07/18.
 */
public class LCARecursive {

    public int lca(TreeNode A, int B, int C) {
        if (A == null) {
            return -1;
        }
        boolean left = checkIfExists(A, B);
        if (!left) {
            return -1;
        }
        boolean right = checkIfExists(A, C);
        if (!right) {
            return -1;
        }

        TreeNode n = lcaNode(A, B, C);
        if (n == null) {
            return -1;
        }
        return n.val;
    }

    private TreeNode lcaNode(TreeNode r, int B, int C) {
        if (r == null) {
            return null;
        }
        if (r.val == B || r.val == C) {
            return r;
        }

        TreeNode left = lcaNode(r.left, B, C);
        TreeNode right = lcaNode(r.right, B, C);

        if (left != null && right != null) {
            return r;
        }

        if (left == null && right == null) {
            return null;
        }

        return left != null ? left : right;
    }

    private boolean checkIfExists(TreeNode r, int v) {
        if (r == null) {
            return false;
        }
        if (r.val == v) {
            return true;
        }

        return checkIfExists(r.left, v) || checkIfExists(r.right, v);
    }
}
