package leetcode;

import java.util.Arrays;

public class CompareStringsByFreq1170 {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {

        int[] fCount = new int[11];

        for (String word : words) {
            int count = getFreq(word);
            fCount[count]++;
        }

        int sum = 0;
        for (int i = 0; i < fCount.length; i++) {
            sum += fCount[i];
            fCount[i] = sum;
        }

        int[] res = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int count = getFreq(queries[i]);
            res[i] = fCount[fCount.length - 1] - fCount[count];
        }

        return res;
    }

    // public int[] numSmallerByFrequency(String[] queries, String[] words) {
    //     int[] freq = new int[words.length];
    //     for (int i = 0; i < words.length; i++) {
    //         String word = words[i];
    //         freq[i] = getFreq(word);
    //     }
    //     Arrays.sort(freq);
    //
    //     Integer[] idx = new Integer[queries.length];
    //     Integer[] qf = new Integer[queries.length];
    //     for (int i = 0; i < queries.length; i++) {
    //         idx[i] = i;
    //         qf[i] = getFreq(queries[i]);
    //     }
    //
    //     Arrays.sort(idx, Comparator.comparingInt(a -> qf[a]));
    //
    //     int[] res = new int[queries.length];
    //
    //     int f = freq.length - 1;
    //     for (int i = queries.length - 1; i >= 0; i--) {
    //         int query = qf[idx[i]];
    //         while (f >= 0 && freq[f] > query) {
    //             f--;
    //         }
    //
    //         res[idx[i]] = freq.length - f - 1;
    //     }
    //
    //     return res;
    // }
    //
    private int getFreq(String word) {
        char c = word.charAt(0);
        int f = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                f++;
            } else if (word.charAt(i) < c) {
                f = 1;
                c = word.charAt(i);
            }
        }

        return f;
    }

    public static void main(String[] args) {
        // ["cbd"]
        // ["zaaaz"]

        String[] queries = {"bbb", "cc"};
        String[] words = {"a", "aa", "aaa", "aaaa"};
        int[] res = new CompareStringsByFreq1170().numSmallerByFrequency(queries, words);
        Arrays.stream(res).forEach(System.out::println);
    }

}
