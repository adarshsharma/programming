package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 30/06/18.
 */
public class ReplaceWords648 {
    Node root;
    List<String> words;

    private class Node {
        int ending;
        Node[] children = new Node[26];

        public Node() {
            ending = -1;
        }
    }

    public void add(String word, int index) {
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

    public String replaceWords(List<String> dict, String sentence) {
        root = new Node();
        words = dict;
        for (int i = 0; i < dict.size(); i++) {
            add(dict.get(i), i);
        }

        String[] split = sentence.split(" ");
        List<String> result = new ArrayList<>();
        for (String s : split) {
            result.add(getReplacement(s));
        }

        return String.join(" ", result);
    }

    private String getReplacement(String s) {
        Node cur = root;
        int i = 0;
        while (i < s.length()) {
            Node child = cur.children[s.charAt(i) - 'a'];
            if (child == null) {
                break;
            }
            if (child.ending >= 0) {
                return words.get(child.ending);
            }
            cur = child;
            i++;
        }

        return s;
    }

    public static void main(String[] args) {
        List<String> dict = Arrays.asList("ca", "cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        System.out.println(new ReplaceWords648().replaceWords(dict, sentence));
    }
}
