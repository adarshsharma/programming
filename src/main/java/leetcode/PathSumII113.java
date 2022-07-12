package leetcode;

import common.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathSumII113 {

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> res = sum(root, targetSum);
    res.forEach(Collections::reverse);
    return res;
  }

  private List<List<Integer>> sum(TreeNode root, int targetSum) {
    if (targetSum == root.val && root.left == null && root.right == null) {
      ArrayList<List<Integer>> res = new ArrayList<>();
      List<Integer> ls = new ArrayList<>();
      ls.add(root.val);
      res.add(ls);
      return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    if (root.left != null) {
      List<List<Integer>> left = sum(root.left, targetSum - root.val);
      left.forEach(x -> x.add(root.val));
      res.addAll(left);
    }
    if (root.right != null) {
      List<List<Integer>> right = sum(root.right, targetSum - root.val);
      right.forEach(x -> x.add(root.val));
      res.addAll(right);
    }
    return res;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.left.left = new TreeNode(11);
    root.left.left.left = new TreeNode(7);
    root.left.left.right = new TreeNode(2);
    root.right = new TreeNode(8);
    root.right.left = new TreeNode(13);
    root.right.right = new TreeNode(4);
    root.right.right.left = new TreeNode(5);
    root.right.right.right = new TreeNode(1);

    System.out.println(new PathSumII113().pathSum(root, 22));
  }

}
