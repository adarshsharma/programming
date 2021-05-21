package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by adarsh.sharma on 01/07/18.
 */
public class TopKFrequentWords692 {

    public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
        if(!first.getValue().equals(second.getValue())) {
            return second.getValue() - first.getValue();
        }

        return first.getKey().compareTo(second.getKey());
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (countMap.containsKey(word)) {
                countMap.put(word, countMap.get(word) + 1);
            } else {
                countMap.put(word, 1);
            }
        }
        List<String> topKFrequent = new ArrayList<>();
        List<Map.Entry<String, Integer>> sortedList = countMap.entrySet().stream().sorted(this::compare).collect(Collectors.toList());

        for (int i = 0; i < k; i++) {
            topKFrequent.add(sortedList.get(i).getKey());
        }

        return topKFrequent;
    }

    public static void main(String[] args) {
        int k = 2;
        String[] words = {"i", "love", "leetcode", "i", "love", "coding", "ab", "ab"};
        System.out.println(new TopKFrequentWords692().topKFrequent(words, k));
    }
}
