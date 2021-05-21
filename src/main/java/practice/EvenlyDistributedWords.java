package practice;

import java.util.ArrayList;
import java.util.List;

public class EvenlyDistributedWords {
    private static void distributeWords(char[] arr) {
        int spaceCount = 0;
        List<Integer> oldPos = new ArrayList<>();
        List<Integer> wordLength = new ArrayList<>();

        boolean wordStarted = false;
        int wordLen = 0;
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == ' ') {
                spaceCount++;
                if (wordStarted) {
                    wordLength.add(wordLen);
                    wordLen = 0;
                    wordStarted = false;
                }
            } else {
                if (wordStarted) {
                    wordLen++;
                } else {
                    wordStarted = true;
                    wordLen = 1;
                    oldPos.add(i);
                }
            }
        }
        if (wordStarted) {
            wordLength.add(wordLen);
        }

        int wordCount = oldPos.size();
        if (wordCount == 0) {
            return;
        } else if (wordCount == 1) {
            return;
        }

        int spacesPerBreak = spaceCount / (wordCount - 1);
        int remainingSpaces = spaceCount % (wordCount - 1);

        List<Integer> newPos = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < wordCount; i++) {
            newPos.add(pos);
            pos = pos + wordLength.get(i) + spacesPerBreak;
            if (remainingSpaces > 0) {
                pos += 1;
                remainingSpaces--;
            }
        }

        for (int i = 0; i < wordCount; ) {
            while (i < wordCount && newPos.get(i) - oldPos.get(i) <= 0) {
                leftShift(arr, oldPos.get(i), newPos.get(i), wordLength.get(i));
                i++;
            }

            int end = i;
            while (end < wordCount && newPos.get(end) - oldPos.get(end) >= 0) {
                end++;
            }
            int newi = end;
            end--;
            while (end >= i) {
                rightShift(arr, oldPos.get(end), newPos.get(end), wordLength.get(end));
                end--;
            }
            i = newi;
        }
    }

    private static void rightShift(char[] arr, Integer oldPos, Integer newPos, Integer len) {
        if (oldPos == newPos) {
            return;
        }
        int k = len - 1;
        while (k >= 0) {
            arr[newPos + k] = arr[oldPos + k];
            arr[oldPos + k] = ' ';
            k--;
        }
    }

    private static void leftShift(char[] arr, Integer oldPos, Integer newPos, Integer len) {
        if (oldPos == newPos) {
            return;
        }
        int k = 0;
        while (k < len) {
            arr[newPos + k] = arr[oldPos + k];
            arr[oldPos + k] = ' ';
            k++;
        }
    }

    public static void main(String[] args) {
        char[] arr = " word1 word2      word3  word ".toCharArray();
        distributeWords(arr);
        String replace = (new String(arr)).replace(" ", ".");
        System.out.println(replace);
    }
}
