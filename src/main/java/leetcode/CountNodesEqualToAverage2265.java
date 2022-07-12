package leetcode;

public class CountNodesEqualToAverage2265 {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public int averageOfSubtree(TreeNode root) {
    int[] res = average(root);
    return res[0];
  }

  private int[] average(TreeNode node) {
    int[] res = new int[3];

    if (node != null) {
      int[] left = average(node.left);
      int[] right = average(node.right);

      res[1] = left[1] + right[1] + 1;
      res[2] = left[2] + right[2] + node.val;
      int avg = res[2] / res[1];
      res[0] = left[0] + right[0] + (avg == node.val ? 1 : 0);
    }

    return res;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(6);
    root.left = new TreeNode(8);
    root.left.left = new TreeNode(0);
    root.left.right = new TreeNode(1);
    System.out.println(new CountNodesEqualToAverage2265().averageOfSubtree(root));
  }
}
