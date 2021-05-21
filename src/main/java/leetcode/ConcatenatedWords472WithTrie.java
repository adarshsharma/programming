package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adarsh.sharma on 02/07/18.
 */
//Some Bug
public class ConcatenatedWords472WithTrie {
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

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        root = new Node();
        for (String word : words) {
            add(word);
        }

        List<String> cWords = new ArrayList<>();
        for (String word : words) {
            Map<Integer, Integer> cMap = new HashMap<>();
            int cWordCount = getCWordCount(word, 0, cMap);
            if (cWordCount > 1) {
                cWords.add(word);
            }
        }

        return cWords;
    }

    private int getCWordCount(String word, int index, Map<Integer, Integer> cMap) {
        if (cMap.containsKey(index)) {
            return cMap.get(index);
        }

        Node cur = root;
        for (int i = index; i < word.length(); i++) {
            Node child = cur.children[word.charAt(i) - 'a'];
            if (child == null) {
                cMap.put(index, -1);
                return -1;
            }
            if (child.ending) {
                int cWordCount = getCWordCount(word, i + 1, cMap);
                if (cWordCount != -1) {
                    cMap.put(i + 1, 1 + cWordCount);
                    return 1 + cWordCount;
                }
            }
            cur = child;
        }

        return 0;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdog", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println(new ConcatenatedWords472WithTrie().findAllConcatenatedWordsInADict(words));
    }
}
