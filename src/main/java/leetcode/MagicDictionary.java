package leetcode;

/**
 * Created by adarsh.sharma on 06/08/18.
 */
class MagicDictionary {
    class Node {
        boolean ending;
        Node[] children = new Node[26];
    }

    class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public Node getRoot() {
            return root;
        }

        public boolean isPresent(String word) {
            return isPresent(root, word);
        }

        public boolean isPresent(Node node, String word) {
            Node cur = node;
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
    }

    Trie trie;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        this.trie = new Trie();
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            trie.add(word);
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        Node node = trie.getRoot();
        for (int i = 0; i < word.length() && node != null; i++) {
            for (Node child : node.children) {
                if (child != null) {
                    if (trie.isPresent(child, word.substring(i + 1))) {
                        return true;
                    }
                }
            }

            node = node.children[word.charAt(i) - 'a'];
        }
        return false;
    }
}
