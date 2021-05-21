package practice.algo.stringmatching;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by adarsh.sharma on 16/12/17.
 */
public class KMP {
    public Collection<Integer> getMatches(String s, String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (s == null || pattern == null || s.length() < pattern.length()) {
            return matches;
        }

        Integer[] lps = computePrefixFunction(pattern);
        System.out.println(StringUtils.join(lps, ','));

        int length = s.length();
        int k = 0;
        for (int i = 0; i < length; i++) {
            while (k > 0 && s.charAt(i) != pattern.charAt(k)) {
                k = lps[k - 1];
            }
            if (s.charAt(i) == pattern.charAt(k)) {
                k++;
            }

            if (k == pattern.length()) {
                matches.add(i - k + 1);
                k = lps[k-1];
            }
        }

        return matches;
    }

    public Integer[] computePrefixFunction(String pattern) {
        int length = pattern.length();
        Integer[] lps = new Integer[length];

        int k = 0;
        lps[0] = 0;

        for (int i = 1; i < length; i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = lps[k - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(k)) {
                k++;
            }
            lps[i] = k;
        }

        return lps;
    }

    public static void main(String[] args) {
        String s = "ABABDABACDABABCABAB";
        String p = "ABABCABAB";
        System.out.println(StringUtils.join(new KMP().getMatches(s, p), ','));

    }
}
