package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 02/07/18.
 */
public class ConcatenatedWords472WithMap {
    Set<String> wordSet;
    Map<String, Integer> subWordMap;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        wordSet = new HashSet<>();
        subWordMap = new HashMap<>();
        for (String word : words) {
            wordSet.add(word);
            subWordMap.put(word, 1);
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

        if (index >= word.length()) {
            return 0;
        }

        String cur = "";
        for (int i = index; i < word.length(); i++) {
            cur += word.charAt(i);
            if (wordSet.contains(cur)) {
                int cWordCount = getCWordCount(word, i + 1, cMap);
                if (cWordCount != -1) {
                    cMap.put(i + 1, 1 + cWordCount);
                    return 1 + cWordCount;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println(new ConcatenatedWords472WithMap().findAllConcatenatedWordsInADict(words));
    }
}
