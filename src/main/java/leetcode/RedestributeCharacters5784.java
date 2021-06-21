package leetcode;

public class RedestributeCharacters5784 {

    public boolean makeEqual(String[] words) {
        int[] f = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                f[c - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (f[i] % words.length != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] words = {"abc", "aabc", "bc"};
        System.out.println(new RedestributeCharacters5784().makeEqual(words));
    }

}
