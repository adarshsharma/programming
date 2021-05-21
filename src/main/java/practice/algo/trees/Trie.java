package practice.algo.trees;

/**
 * Created by adarsh.sharma on 29/06/18.
 */
public class Trie {
    Node root;

    public static class Node {
        boolean ending;
        Node[] children = new Node[26];
    }

    public Trie() {
        root = new Node();
    }

    public boolean isPresent(String word) {
        Node cur = root;
        int i = 0;
        while (i < word.length()) {
            if (cur == null) {
                return false;
            }
            Node child = cur.children[word.charAt(i) - 'a'];
            if (child == null) {
                return false;
            }
            cur = child;
            i++;
        }
        return cur.ending;
    }

    public void add(String word) {
        Node cur = root;
        int i = 0;
        while (i < word.length()) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                cur.children[word.charAt(i) - 'a'] = new Node();
            }
            cur = cur.children[word.charAt(i) - 'a'];
            i++;
        }
        cur.ending = true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("ab");
        System.out.println(trie.isPresent("a"));
    }
}
