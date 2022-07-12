package leetcode;

public class MaximumNumberOfWords1935 {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int res = words.length;
        boolean[] broken = new boolean[26];
        for(char c: brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }

        for(String word: words) {
            for(char c: word.toCharArray()) {
                if(broken[c - 'a']) {
                    res--;
                    break;
                }
            }
        }

        return res;
    }

}
