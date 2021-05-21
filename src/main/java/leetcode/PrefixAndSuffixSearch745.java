package leetcode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by adarsh.sharma on 01/07/18.
 */
public class PrefixAndSuffixSearch745 {
    String[] words;
    Node root;
    Node reverseRoot;

    private class Node {
        Node[] children = new Node[26];
        LinkedList<Integer> prefixes;

        public Node() {
            prefixes = new LinkedList<>();
        }
    }

    public void add(String word, int index) {
        Node cur = root;
        int i = 0;
        while (i < word.length()) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                cur.children[word.charAt(i) - 'a'] = new Node();
            }
            cur.prefixes.addFirst(index);
            cur = cur.children[word.charAt(i) - 'a'];
            i++;
        }
        cur.prefixes.addFirst(index);
    }

    public void addReverse(String word, int index) {
        Node cur = reverseRoot;
        int i = word.length() - 1;
        while (i >= 0) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                cur.children[word.charAt(i) - 'a'] = new Node();
            }
            cur.prefixes.addFirst(index);
            cur = cur.children[word.charAt(i) - 'a'];
            i--;
        }
        cur.prefixes.addFirst(index);
    }

    public PrefixAndSuffixSearch745(String[] words) {
        this.words = words;
        root = new Node();
        reverseRoot = new Node();
        for (int i = 0; i < words.length; i++) {
            add(words[i], i);
            addReverse(words[i], i);
        }
    }

    public LinkedList<Integer> findPrefix(String word) {
        Node cur = root;
        int i = 0;
        while (i < word.length()) {
            Node child = cur.children[word.charAt(i) - 'a'];
            if (child == null) {
                return new LinkedList<>();
            }
            cur = child;
            i++;
        }

        return cur.prefixes;
    }

    public LinkedList<Integer> findSuffix(String word) {
        Node cur = reverseRoot;
        int i = word.length() - 1;
        while (i >= 0) {
            Node child = cur.children[word.charAt(i) - 'a'];
            if (child == null) {
                return new LinkedList<>();
            }
            cur = child;
            i--;
        }

        return cur.prefixes;
    }

    public int f(String prefix, String suffix) {
        LinkedList<Integer> pw = findPrefix(prefix);
        LinkedList<Integer> sw = findSuffix(suffix);
        Iterator<Integer> i1 = pw.iterator();
        Iterator<Integer> i2 = sw.iterator();

        if (!i1.hasNext() || !i2.hasNext()) {
            return -1;
        }

        Integer first = i1.next();
        Integer second = i2.next();
        while (true) {
            if (first.equals(second)) {
                return first;
            }

            if (first > second) {
                if(i1.hasNext()) {
                    first = i1.next();
                } else {
                    return -1;
                }
            } else {
                if(i2.hasNext()) {
                    second = i2.next();
                } else {
                    return -1;
                }
            }
        }
    }

    public static void main(String[] args) {
//        String[] words = {"pop"};
        String[] words = {"apple", "applicable", "temple"};
        PrefixAndSuffixSearch745 wordFilter = new PrefixAndSuffixSearch745(words);
//        System.out.println(wordFilter.f("", "op"));
        System.out.println(wordFilter.f("te", "le"));
    }
}
