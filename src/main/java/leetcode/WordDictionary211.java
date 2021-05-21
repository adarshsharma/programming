package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 09/08/18.
 */
public class WordDictionary211 {
    Node root;
    class Node {
        boolean end;
        Map<Character, Node> children = new HashMap<>();
    }

    /** Initialize your data structure here. */
    public WordDictionary211() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word==null) {
            return;
        }

        if(word.length() == 0) {
            root.end = true;
            return;
        }

        Node cur = root;
        for(int i=0;i<word.length();i++) {
            cur = cur.children.computeIfAbsent(word.charAt(i), k -> new Node());
        }
        cur.end = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word==null) {
            return false;
        }
        if(word.length() == 0) {
            return root.end;
        }

        return search(root, word, 0);
    }

    private boolean search(Node root, String word, int idx) {
        if(idx == word.length()) {
            return root.end;
        }

        Node temp = root;
        Node cur = root;
        for(int i=idx;i<word.length();i++) {
            if(word.charAt(i) == '.') {
                for(Node node: cur.children.values()) {
                    if(search(node, word, i+1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if(cur.children.containsKey(word.charAt(i))) {
                    cur = cur.children.get(word.charAt(i));
                } else {
                    return false;
                }
            }
        }

        return cur.end;

    }

    public static void main(String[] args) {
        WordDictionary211 wordDictionary211 = new WordDictionary211();
        wordDictionary211.addWord("bad");
        wordDictionary211.addWord("dad");
        wordDictionary211.addWord("mad");
        System.out.println(wordDictionary211.search("pad"));
        System.out.println(wordDictionary211.search("bad"));
        System.out.println(wordDictionary211.search(".ad"));
        System.out.println(wordDictionary211.search("b.."));
    }
}
