package practice.algo.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by adarsh.sharma on 09/07/18.
 */
public class LCAEulerTour {
    private List<Integer> eulerTour;
    private Map<Integer, Integer> newToOldMapping;
    private Map<Integer, Integer> oldToNewMapping;
    private Map<Integer, Integer> firstOcc;
    private SparseTable sparseTable;

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

    public class SparseTable {
        int[] logTable;
        int[][] st;

        public SparseTable(List<Integer> input) {
            logTable = new int[input.size() + 1];
            for (int i = 2; i < logTable.length; i++) {
                logTable[i] = logTable[i / 2] + 1;
            }

            st = new int[logTable[logTable.length - 1] + 1][input.size()];
            for (int j = 0; j < input.size(); j++) {
                st[0][j] = input.get(j);
            }

            for (int r = 1; r < st.length; r++) {
                for (int i = 0; i + (1 << r) <= input.size(); i++) {
                    st[r][i] = Math.min(st[r - 1][i], st[r - 1][i + (1 << r - 1)]);
                }
            }
        }

        public int getMin(int l, int r) {
            int log = logTable[r - l];
            return Math.min(st[log][l], st[log][r - (1 << log)]);
        }
    }

    public LCAEulerTour(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        this.eulerTour = new ArrayList<>();
        this.newToOldMapping = new HashMap<>();
        this.oldToNewMapping = new HashMap<>();
        this.firstOcc = new HashMap<>();
        populateEulerTour(root);
        this.sparseTable = new SparseTable(eulerTour);
        this.newToOldMapping = oldToNewMapping.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public int lca(int l, int r) {
        if(!oldToNewMapping.containsKey(l) || !oldToNewMapping.containsKey(r)) {
            return -1;
        }

        return newToOldMapping.get(sparseTable.getMin(firstOcc.get(oldToNewMapping.get(l)), firstOcc.get(oldToNewMapping.get(r)) + 1));
    }

    private void populateEulerTour(TreeNode root) {
        Integer newValue = oldToNewMapping.computeIfAbsent(root.val, k -> oldToNewMapping.size());
        firstOcc.computeIfAbsent(newValue, k -> eulerTour.size());
        eulerTour.add(newValue);
        if (root.left != null) {
            populateEulerTour(root.left);
            eulerTour.add(newValue);
        }

        if (root.right != null) {
            populateEulerTour(root.right);
            eulerTour.add(newValue);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(11);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(7);
        LCAEulerTour lcaEulerTour = new LCAEulerTour(root);
        System.out.println(lcaEulerTour.lca(2, 5));
        System.out.println(lcaEulerTour.lca(9, 13));
        System.out.println(lcaEulerTour.lca(8, 7));
        System.out.println(lcaEulerTour.lca(8, 20));
    }
}
