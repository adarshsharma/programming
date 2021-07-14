package leetcode;


import common.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeDistanceKInBinaryTree863 {

  static boolean LEFT = false;
  static boolean RIGHT = true;

  static public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    List<Boolean> path = pathToNode(root, target);
    Collections.reverse(path);

    List<Integer> results = new ArrayList<>();

    TreeNode node = root;
    for (int i = 0; i < path.size(); i++) {
      int len = K - path.size() + i;
      if(len == 0) {
        results.add(node.val);
      }
      if (path.get(i)) {
        results.addAll(getNodes(node.left, len - 1));
        node = node.right;
      } else {
        results.addAll(getNodes(node.right, len - 1));
        node = node.left;
      }
    }
    results.addAll(getNodes(node, K));

    return results;
  }

  static List<Integer> getNodes(TreeNode node, int depth) {
    if (node == null || depth < 0) {
      return new ArrayList<>();
    }

    if (depth == 0) {
      return Collections.singletonList(node.val);
    }

    List<Integer> res = new ArrayList<>();
    res.addAll(getNodes(node.left, depth - 1));
    res.addAll(getNodes(node.right, depth - 1));
    return res;
  }


  static List<Boolean> pathToNode(TreeNode root, TreeNode target) {
    if (root == null) {
      return null;
    }
    if (root.equals(target)) {
      return new ArrayList<>();
    }

    List<Boolean> leftPath = pathToNode(root.left, target);
    if (leftPath != null) {
      leftPath.add(LEFT);
      return leftPath;
    }

    List<Boolean> rightPath = pathToNode(root.right, target);
    if (rightPath != null) {
      rightPath.add(RIGHT);
      return rightPath;
    }
    return null;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    root.right = new TreeNode(1);
    root.right.right = new TreeNode(2);
    root.right.right.right = new TreeNode(3);
    root.right.right.right.left = new TreeNode(4);

    System.out.println(distanceK(root, root.right.right, 2));

    // root.left.left = new TreeNode(6);
    // root.left.left.left = new TreeNode(9);
    // root.left.left.left.left = new TreeNode(10);
    // root.left.right = new TreeNode(2);
    // root.left.right.left = new TreeNode(7);
    // root.left.right.right = new TreeNode(4);
    // root.right = new TreeNode(1);
    // root.right.left = new TreeNode(0);
    // root.right.right = new TreeNode(8);
    // System.out.println(distanceK(root, root.left.right, 4));

   //    0
   //  1
   // 3  2
  }
}
