package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh.sharma on 02/07/18.
 */
public class WordSearchII212 {
    Node root;
    String[] words;

    public class Node {
        int ending;
        Node[] children = new Node[26];
    }

    public void add(int index) {
        String word = words[index];
        Node cur = root;
        int i = 0;
        while (i < word.length()) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                cur.children[word.charAt(i) - 'a'] = new Node();
            }
            cur = cur.children[word.charAt(i) - 'a'];
            i++;
        }
        cur.ending = index;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.words = words;
        List<String> foundWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            add(i);
        }



        return foundWords;
    }

    public static void main(String[] args) {
        String[] words = {"oath", "pea", "eat", "rain"};
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        System.out.println(new WordSearchII212().findWords(board, words));
    }
}
