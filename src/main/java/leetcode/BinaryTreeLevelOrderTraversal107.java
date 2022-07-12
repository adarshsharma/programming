package leetcode;

import common.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.addLast(root);
        dq.addLast(new TreeNode(2000));    //marker
        res.add(new ArrayList<>());

        while (!dq.isEmpty()) {
            TreeNode tn = dq.pollFirst();
            if (tn.val == 2000) {
                if (!dq.isEmpty()) {
                    dq.addLast(tn);
                    res.add(new ArrayList<>());
                }
            } else {
                res.get(res.size() - 1).add(tn.val);
                if (tn.left != null) {
                    dq.addLast(tn.left);
                }
                if (tn.right != null) {
                    dq.addLast(tn.right);
                }
            }
        }

        Collections.reverse(res);
        return res;
    }
}
