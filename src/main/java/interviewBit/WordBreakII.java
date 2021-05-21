package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 18/07/18.
 */
public class WordBreakII {
    public class Node {
        int idx;
        Node[] children = new Node[26];

        public Node() {
            idx = -1;
        }
    }

    public class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public Node getRoot() {
            return root;
        }

        public void add(int idx, String word) {
            Node cur = root;
            int i = 0;
            while (i < word.length()) {
                if (cur.children[word.charAt(i) - 'a'] == null) {
                    cur.children[word.charAt(i) - 'a'] = new Node();
                }
                cur = cur.children[word.charAt(i) - 'a'];
                i++;
            }
            cur.idx = idx;
        }
    }

    public ArrayList<String> wordBreak(String A, List<String> B) {
        ArrayList<String> result = new ArrayList<>();
        if (A == null || A.length() == 0) {
            return result;
        }

        if (B == null || B.size() == 0) {
            return result;
        }

        Trie trie = new Trie();
        for (int i = 0; i < B.size(); i++) {
            trie.add(i, B.get(i));
        }

        int n = A.length();
        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            r.add(new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            Node cur = trie.getRoot();
            int j = i;
            while (j < n) {
                Node child = cur.children[A.charAt(j) - 'a'];
                if (child == null) {
                    break;
                }

                if (child.idx != -1 && (r.get(j + 1).size() > 0 || j + 1 == n)) {
                    r.get(i).add(child.idx);
                }
                cur = child;
                j++;
            }
        }

        List<Integer> paths = new ArrayList<>();
        populate(0, result, r, paths, B, n);
        return result;
    }

    private void populate(int idx, List<String> res, List<List<Integer>> r,
                          List<Integer> paths, List<String> B, int n) {
        List<Integer> lst = r.get(idx);
        if (lst.size() == 0) {
            if (idx == n) {
                String row = "";
                for (Integer i : paths) {
                    row += B.get(i) + " ";
                }
                res.add(row.substring(0, row.length() - 1));
            }
            return;
        }

        for (Integer i : lst) {
            paths.add(i);
            populate(idx + B.get(i).length(), res, r, paths, B, n);
            paths.remove(paths.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> B = Arrays.asList("bababbbb", "bbbabaa", "abbb", "a", "aabbaab", "b", "babaabbbb", "aa", "bb");
        String A = "aabbbabaaabbbabaabaab";
        System.out.println(new WordBreakII().wordBreak(A, B));
    }
}
