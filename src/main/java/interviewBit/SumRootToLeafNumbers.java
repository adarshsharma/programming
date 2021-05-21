package interviewBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh.sharma on 20/07/18.
 */
public class SumRootToLeafNumbers {
    long mod = 1003L;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    class Pair {
        List<Long> key;
        Long value;

        public Pair(List<Long> key, Long value) {
            this.key = key;
            this.value = value;
        }
    }

    public int sumNumbers(TreeNode A) {
        Pair p = getPairSum(A);
        return p.value.intValue();
    }

    private Pair getPairSum(TreeNode n) {
        if (n == null) {
            List<Long> d = new ArrayList<>();
            return new Pair(d, 0L);
        }

        Pair left = getPairSum(n.left);
        Pair right = getPairSum(n.right);

        List<Long> list = new ArrayList<>();

        long sum = 0L;
        sum = (sum + left.value) % mod;
        sum = (sum + right.value) % mod;

        list.addAll(left.key);
        list.addAll(right.key);

        if(list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                long count = list.get(i);
                long s = n.val + 0L;
                while (count > 0) {
                    s = (s * 10L) % mod;
                    count--;
                }

                sum = (sum + s) % mod;
                list.set(i, list.get(i) + 1);
            }
            return new Pair(list, sum);
        } else {
            list.add(1L);
            return new Pair(list, (long)n.val);
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new SumRootToLeafNumbers().sumNumbers(root));
    }
}
