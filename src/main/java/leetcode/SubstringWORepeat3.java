package leetcode;

import java.util.HashMap;

/**
 * Created by adarsh.sharma on 20/06/18.
 */
public class SubstringWORepeat3 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> p = new HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!p.containsKey(s.charAt(i))) {
                p.put(s.charAt(i), i);
                if (p.size() > maxLength) {
                    maxLength = p.size();
                }
            } else {
                int old = p.get(s.charAt(i));
                for (int j = i - p.size(); j <= old; j++) {
                    p.remove(s.charAt(j));
                }
                p.put(s.charAt(i), i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new SubstringWORepeat3().lengthOfLongestSubstring("pwawkew"));
    }
}
