package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 02/07/18.
 */
public class PalindromePairs336 {
    String[] words;
    Node root;
    Node reverseRoot;
    Map<Pair, Boolean> palMap = new HashMap<>();
    Map<Pair, Boolean> palMapReverse = new HashMap<>();

    class Pair {
        int first;

        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

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

    public List<List<Integer>> palindromePairs(String[] words) {
        this.words = words;
        root = new Node();
        reverseRoot = new Node();
        for (int i = 0; i < words.length; i++) {
            add(words[i], i);
            addReverse(words[i], i);
        }

        List<List<Integer>> pWords = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            List<Integer> pws = getPWords(i);
            for (Integer pw : pws) {
                pWords.add(Arrays.asList(i, pw));
            }
        }

        for (int i = 0; i < words.length; i++) {
            List<Integer> pws = getPWordsReverse(i);
            for (Integer pw : pws) {
                if (words[pw].length() != words[i].length()) {
                    pWords.add(Arrays.asList(pw, i));
                }
            }
        }

        return pWords;
    }


    private List<Integer> getPWordsReverse(int index) {
        String word = words[index];
        List<Integer> pws = new ArrayList<>();

        //check for words whose length >= current word length
        Node cur = root;
        int i = word.length() - 1;
        for (; i >= 0; i--) {
            Node child = cur.children[word.charAt(i) - 'a'];
            if (child != null) {
                cur = child;
            } else {
                break;
            }
        }

        if (i == -1) {
            for (Integer prefix : cur.prefixes) {
                if (prefix != index) {
                    Pair key = new Pair(prefix, word.length());
                    if (palMapReverse.containsKey(key)) {
                        if (palMapReverse.get(key)) {
                            pws.add(prefix);
                        }
                    } else {
                        if (computePalMapReverse(prefix, word.length())) {
                            pws.add(prefix);
                        }
                    }
                }
            }
        }

        return pws;
    }

    private List<Integer> getPWords(int index) {
        String word = words[index];
        List<Integer> pws = new ArrayList<>();

        //check for words whose length >= current word length
        Node cur = reverseRoot;
        int i = 0;
        for (; i < word.length(); i++) {
            Node child = cur.children[word.charAt(i) - 'a'];
            if (child != null) {
                cur = child;
            } else {
                break;
            }
        }

        if (i == word.length()) {
            for (Integer prefix : cur.prefixes) {
                if (prefix != index) {
                    Pair key = new Pair(prefix, word.length());
                    if (palMap.containsKey(key)) {
                        if (palMap.get(key)) {
                            pws.add(prefix);
                        }
                    } else {
                        if (computePalMap(prefix, word.length())) {
                            pws.add(prefix);
                        }
                    }
                }
            }
        }

        return pws;
    }

    private boolean computePalMapReverse(Integer index, int start) {
        Pair p = new Pair(index, start);
        String word = words[index];
        int end = word.length() - 1;
        while (start < end && word.charAt(start) == word.charAt(end)) {
            start++;
            end--;
        }

        boolean isPalindrome = start >= end;
        palMapReverse.put(p, isPalindrome);
        return isPalindrome;
    }

    private boolean computePalMap(Integer index, int start) {
        Pair p = new Pair(index, start);
        String word = words[index];
        int end = word.length() - 1 - start;
        start = 0;
        while (start < end && word.charAt(start) == word.charAt(end)) {
            start++;
            end--;
        }

        boolean isPalindrome = start >= end;
        palMap.put(p, isPalindrome);
        return isPalindrome;
    }

    public static void main(String[] args) {
//        String[] words = {"bat", "tab", "cat"};
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
//        String[] words = {"bab", "abc", "cba"};
        System.out.println(new PalindromePairs336().palindromePairs(words));
    }
}
