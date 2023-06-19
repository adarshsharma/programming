package leetcode;

public class LongestZigZagPathInABinaryTree1337 {

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

  int max = 0;

  // public int longestZigZag(TreeNode root) {
  //   Stack<Character> stack = new Stack<>();
  //   if (root == null) {
  //     return 0;
  //   }
  //   longestZigZag(root, stack, 0);
  //   return max;
  // }
  //
  // private void longestZigZag(TreeNode root, Stack<Character> stack, int cur) {
  //   if (root == null) {
  //     return;
  //   }
  //
  //   if (root.left != null) {
  //     if (stack.isEmpty() || stack.peek() == 'R') {
  //       cur++;
  //       max = Math.max(max, cur);
  //     }
  //     stack.push('L');
  //     longestZigZag(root.left, stack, cur);
  //     Character pop = stack.pop();
  //     if (stack.isEmpty() || !pop.equals(stack.peek())) {
  //       cur--;
  //     }
  //   }
  //   if (root.right != null) {
  //     if (stack.isEmpty() || stack.peek() == 'L') {
  //       cur++;
  //       max = Math.max(max, cur);
  //     }
  //     stack.push('R');
  //     longestZigZag(root.right, stack, cur);
  //     stack.pop();
  //   }
  // }

  public int longestZigZag(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int[] left = zigZag(root, true);
    int[] right = zigZag(root, false);

    return Math.max(left[1], right[1]);

  }

  private int[] zigZag(TreeNode root, boolean comingFromLeft) {
    if (root == null || (root.left == null && root.right == null)) {
      return new int[]{0, 0};
    }

    int[] res = new int[2];
    int[] leftTree = zigZag(root.left, true);
    int[] rightTree = zigZag(root.right, false);

    res[1] = Math.max(rightTree[1], leftTree[1]);
    res[1] = Math.max(res[1], 1 + leftTree[0]);
    res[1] = Math.max(res[1], 1 + rightTree[0]);

    if (comingFromLeft) {
      res[0] = root.right == null ? 0 : rightTree[0] + 1;
    } else {
      res[0] = root.left == null ? 0 : leftTree[0] + 1;
    }

    return res;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.left.right.left = new TreeNode(5);
    root.left.right.right = new TreeNode(6);
    root.left.right.left.left = new TreeNode(7);

    // TreeNode root = new TreeNode(1);
    // root.right = new TreeNode(2);
    // root.right.left = new TreeNode(3);
    // root.right.right = new TreeNode(4);
    // root.right.right.left = new TreeNode(5);
    // root.right.right.right = new TreeNode(6);
    // root.right.right.left.right = new TreeNode(7);
    // root.right.right.left.right.right = new TreeNode(8);

    System.out.println(new LongestZigZagPathInABinaryTree1337().longestZigZag(root));
  }
}
