package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by adarsh.sharma on 29/06/18.
 */
public class LongestWordInDictionary720 {
    Node root;

    private class Node {
        boolean ending;
        Node[] children = new Node[26];
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

    public String longestWordOld(String[] words) {
        root = new Node();
        for (String word : words) {
            add(word);
        }

        Queue<Node> queue = new LinkedList<>();
        Queue<String> stringQueue = new LinkedList<>();
        String longest = "";

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].ending) {
                queue.add(root.children[i]);
                Character c = (char) (i + 'a');
                stringQueue.add(c.toString());
            }
        }

        while (!queue.isEmpty()) {
            Node pop = queue.poll();
            String cur = stringQueue.poll();

            if (cur.length() > longest.length()) {
                longest = cur;
            }

            for (int i = 0; i < 26; i++) {
                if (pop.children[i] != null && pop.children[i].ending) {
                    queue.add(pop.children[i]);
                    char c = (char) (i + 'a');
                    stringQueue.add(cur + c);
                }
            }

        }

        return longest;
    }

    public String longestWord(String[] words) {
        root = new Node();
        for (String word : words) {
            add(word);
        }

        String longest = "";
        for (String word : words) {
            if (validWord(word)) {
                if (word.length() > longest.length() ||
                        (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }


        return longest;
    }

    private boolean validWord(String word) {
        int i = 0;
        Node cur = root;
        while (i < word.length()) {
            Node child = cur.children[word.charAt(i) - 'a'];
            if(!child.ending) {
                return false;
            }
            cur = child;
            i++;
        }

        return true;
    }

    public static void main(String[] args) {
        LongestWordInDictionary720 longestWordInDictionary720 = new LongestWordInDictionary720();
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple", "appleab"};
//        String[] words = {"w", "wo", "wor", "worl", "world"};
        System.out.println(longestWordInDictionary720.longestWord(words));
    }
}
