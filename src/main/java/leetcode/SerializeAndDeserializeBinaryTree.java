package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adarsh.sharma on 28/07/18.
 */
public class SerializeAndDeserializeBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> dq = new LinkedList<>();
        List<String> res = new ArrayList<>();
        List<String> r = new ArrayList<>();
        int nCount = 0;
        dq.addLast(root);
        int count = 1;
        int j = count;

        while (!dq.isEmpty()) {
            TreeNode n = dq.pollFirst();
            j--;
            if (n != null) {
                if (nCount > 0) {
                    r.add(nCount + "|n");
                    nCount = 0;
                }
                r.add(n.val + "");
                dq.add(n.left);
                dq.add(n.right);
            } else {
                nCount++;
                dq.add(null);
                dq.add(null);
            }

            if (j == 0) {
                if (count == nCount) {
                    break;
                }
                count *= 2;
                j = count;
                if (nCount > 0) {
                    r.add(nCount + "|n");
                }
                res.addAll(r);
                r = new ArrayList<>();
                nCount = 0;
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] strs = data.split(",");
        strs[0] = strs[0].replace("[", "");
        strs[strs.length - 1] = strs[strs.length - 1].replace("]", "");

        TreeNode root = new TreeNode(Integer.parseInt(strs[0].trim()));

        Deque<TreeNode> dq = new LinkedList<>();
        dq.addLast(root);
        int i = 1;
        while (!dq.isEmpty() && i < strs.length) {
            TreeNode n = dq.pollFirst();
            if (n == null) {
//                i += 2;
                String str = strs[i].trim();
                int count = Integer.parseInt(str.split("\\|")[0]);
                if(count > 2) {
                    count-=2;
                    strs[i] = count + "|n";
                } else if(count == 2) {
                    i++;
                }
                dq.addLast(null);
                dq.addLast(null);
            } else {
                String str = strs[i].trim();
                if (!str.contains("n")) {
                    n.left = new TreeNode(Integer.parseInt(str));
                    dq.addLast(n.left);
                    i++;
                } else {
                    int count = Integer.parseInt(str.split("\\|")[0]);
                    dq.addLast(null);
                    count--;
                    if (count == 0) {
                        i++;
                    } else {
                        strs[i] = count + "|n";
                    }
                }

                if (i < strs.length) {
                    str = strs[i].trim();
                    if (!str.contains("n")) {
                        n.right = new TreeNode(Integer.parseInt(str));
                        dq.addLast(n.right);
                        i++;
                    } else {
                        int count = Integer.parseInt(str.split("\\|")[0]);
                        dq.addLast(null);
                        count--;
                        if (count == 0) {
                            i++;
                        } else {
                            strs[i] = count + "|n";
                        }
                    }
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        SerializeAndDeserializeBinaryTree s = new SerializeAndDeserializeBinaryTree();
        String serialize = s.serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = s.deserialize(serialize);
        System.out.println("done");
    }
}
