package interviewBit;

import common.TreeNode;

/**
 * Created by adarsh.sharma on 09/07/18.
 */
public class ValidBST {

//    class MaxMin {
//        Integer max;
//        Integer min;
//
//        public MaxMin(int max, int min) {
//            this.max = max;
//            this.min = min;
//        }
//    }
//    public int isValidBST(TreeNode A) {
//        if(A==null) {
//            return 1;
//        }
//
//        MaxMin lmn = getMaxMin(A.left);
//        MaxMin rmn = getMaxMin(A.right);
//        if(lmn == null || rmn == null) {
//            return 0;
//        }
//        if(A.val > lmn.max && A.val < rmn.min) {
//            return 1;
//        }
//
//        return 0;
//    }
//    private MaxMin getMaxMin(TreeNode t) {
//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//
//        if(t != null) {
//            MaxMin lmn = getMaxMin(t.left);
//            MaxMin rmn = getMaxMin(t.right);
//            if(lmn == null || rmn == null) {
//                return null;
//            }
//
//            if(t.val > lmn.max && t.val < rmn.min) {
//                max = Math.max(rmn.max, t.val);
//                min = Math.min(lmn.min, t.val);
//            } else {
//                return null;
//            }
//        }
//
//        return new MaxMin(max, min);
//    }

    public int isValidBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int isBST(TreeNode root, int min, int max) {
        if (root == null) return 1;
        if (root.val < min || root.val > max) return 0;
        return Math.min(isBST(root.left, min, root.val - 1), isBST(root.right, root.val, max));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(15);
        System.out.println(new ValidBST().isValidBST(root));
    }
}
