package practice;

public class BinaryTreeWithNextNode {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    public TreeNode(int v) {
      this.val = v;
    }


  }

  public void populateNext(TreeNode root) {

    if (root == null) {
      return;
    }

    TreeNode ptr = root;
    TreeNode nextLevelPtr = null;

    while (ptr != null || nextLevelPtr != null) {

      if (nextLevelPtr == null) {
        if (ptr.left != null) {
          nextLevelPtr = ptr.left;
        } else if (ptr.right != null) {
          nextLevelPtr = ptr.right;
        }
      }
      if (ptr.left != null) {
        ptr.left.next = ptr;

      }
      if (ptr.right != null) {
        ptr.right.next = ptr;

      }

      TreeNode parent = ptr.next;

      if (parent != null) {

        if (parent.right != ptr && parent.right != null) {
          ptr.next = parent.right;
        } else {

          TreeNode parentLevelPtr = parent.next;
          while (parentLevelPtr != null) {
            if (parentLevelPtr.left != null) {
              ptr.next = parentLevelPtr.left;
              break;
            } else if (parentLevelPtr.right != null) {
              ptr.next = parentLevelPtr.right;
              break;
            }
            parentLevelPtr = parentLevelPtr.next;
          }
        }
      }

      ptr = ptr.next;
      if (ptr == null) {
        ptr = nextLevelPtr;
        nextLevelPtr = null;
      }
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);
    new BinaryTreeWithNextNode().populateNext(root);
    System.out.println("test");
  }

}
