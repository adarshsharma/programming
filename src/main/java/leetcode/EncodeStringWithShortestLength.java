package leetcode;

/**
 * Created by adarsh.sharma on 09/09/18.
 */
public class EncodeStringWithShortestLength {

    public String encode(String s) {
        String[][] dp = new String[s.length()][s.length()];

        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i < s.length() - l; i++) {
                int j = i + l;
                String substr = s.substring(i, j + 1);
                // Checking if string length < 5. In that case, we know that encoding will not help.
                if (j - i < 4) {
                    dp[i][j] = substr;
                } else {
                    dp[i][j] = substr;
                    // Loop for trying all results that we get after dividing the strings into 2 and combine the   results of 2 substrings
                    int min = -1;
                    int len = substr.length();
                    for (int k = i; k < j; k++) {
                        if (dp[i][k].length() + dp[k + 1][j].length() < len) {
                            min = k;
                            len = dp[i][k].length() + dp[k + 1][j].length();
                        }
                    }
                    if(min!=-1) {
                        dp[i][j] = dp[i][min] + dp[min + 1][j];
                    }

                    // Loop for checking if string can itself found some pattern in it which could be repeated.
                    for (int k = 0; k < substr.length(); k++) {
                        String repeatStr = substr.substring(0, k + 1);
                        if (substr.length() % repeatStr.length() == 0
                                && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length() / repeatStr.length() + "[" + dp[i][i + k] + "]";
                            if (ss.length() < dp[i][j].length()) {
                                dp[i][j] = ss;
                            }
                        }
                    }
                }
            }
        }

        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        EncodeStringWithShortestLength en = new EncodeStringWithShortestLength();
//        System.out.println(en.encode("abababaabababababcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefg"));
//        System.out.println(en.encode("abaababaab"));
//        System.out.println(en.encode("abbbabbbcabbbabbbc"));
//        System.out.println(en.encode("mnabcdeabcdeabcdeabcde"));
//        System.out.println(en.encode("ababababac"));
    }
}
