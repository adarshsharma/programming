package leetcode;

import common.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeZigZag103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> dq = new ArrayDeque<>();
        boolean order = true;
        dq.addLast(root);
        dq.addLast(new TreeNode(1000));    //marker
        res.add(new ArrayList<>());

        while (!dq.isEmpty()) {
            TreeNode tn = order ? dq.pollFirst() : dq.pollLast();
            if (tn.val == 1000) {
                if (!dq.isEmpty()) {
                    if (order) {
                        dq.addFirst(tn);
                    } else {
                        dq.addLast(tn);
                    }
                    order = !order;
                    res.add(new ArrayList<>());
                }
            } else {
                res.get(res.size() - 1).add(tn.val);
                if (order) {
                    if (tn.left != null) {
                        dq.addLast(tn.left);
                    }
                    if (tn.right != null) {
                        dq.addLast(tn.right);
                    }
                } else {
                    if (tn.right != null) {
                        dq.addFirst(tn.right);
                    }
                    if (tn.left != null) {
                        dq.addFirst(tn.left);
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(3);
        r.left = new TreeNode(9);
        r.right = new TreeNode(20);
        r.right.left = new TreeNode(15);
        r.right.right = new TreeNode(7);
        System.out.println(new BinaryTreeZigZag103().zigzagLevelOrder(r));

    }

}
