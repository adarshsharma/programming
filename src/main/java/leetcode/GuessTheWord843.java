package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by adarsh.sharma on 19/08/18.
 */
public class GuessTheWord843 {
    public void findSecretWord(String[] wordlist) {
        Set<String> eligibleSet = Arrays.stream(wordlist).collect(Collectors.toSet());
        int res = 0;
        int count = 0;
        while (!eligibleSet.isEmpty() && res != 6) {
            count++;
            String guess = eligibleSet.iterator().next();
            res = guessWord(guess);
            if (res == 0) {
                eligibleSet = removeMatching(eligibleSet, guess);
            } else if (res < 6) {
                eligibleSet = removeNonMatching(eligibleSet, guess, res);
            }
        }
        System.out.println(count);
    }

    private int guessWord(String guess) {
        String word = "hbaczn";
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private Set<String> removeNonMatching(Set<String> eligibleSet, String guess, int res) {
        Set<String> modSet = new HashSet<>();
        for (String word : eligibleSet) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(i)) {
                    count++;
                }
            }
            if (count == res) {
                modSet.add(word);
            }
        }

        return modSet;
    }

    private Set<String> removeMatching(Set<String> eligibleSet, String guess) {
        Set<String> modSet = new HashSet<>();
        for (String word : eligibleSet) {
            int i = 0;
            for (; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(i)) {
                    break;
                }
            }
            if (i == word.length()) {
                modSet.add(word);
            }
        }

        return modSet;
    }

    public static void main(String[] args) {
        GuessTheWord843 guessTheWord843 = new GuessTheWord843();
        String[] wordlist = {"gaxckt", "trlccr", "jxwhkz", "ycbfps", "peayuf", "yiejjw", "ldzccp", "nqsjoa", "qrjasy", "pcldos", "acrtag", "buyeia", "ubmtpj", "drtclz", "zqderp", "snywek", "caoztp", "ibpghw", "evtkhl", "bhpfla", "ymqhxk", "qkvipb", "tvmued", "rvbass", "axeasm", "qolsjg", "roswcb", "vdjgxx", "bugbyv", "zipjpc", "tamszl", "osdifo", "dvxlxm", "iwmyfb", "wmnwhe", "hslnop", "nkrfwn", "puvgve", "rqsqpq", "jwoswl", "tittgf", "evqsqe", "aishiv", "pmwovj", "sorbte", "hbaczn", "coifed", "hrctvp", "vkytbw", "dizcxz", "arabol", "uywurk", "ppywdo", "resfls", "tmoliy", "etriev", "oanvlx", "wcsnzy", "loufkw", "onnwcy", "novblw", "mtxgwe", "rgrdbt", "ckolob", "kxnflb", "phonmg", "egcdab", "cykndr", "lkzobv", "ifwmwp", "jqmbib", "mypnvf", "lnrgnj", "clijwa", "kiioqr", "syzebr", "rqsmhg", "sczjmz", "hsdjfp", "mjcgvm", "ajotcx", "olgnfv", "mjyjxj", "wzgbmg", "lpcnbj", "yjjlwn", "blrogv", "bdplzs", "oxblph", "twejel", "rupapy", "euwrrz", "apiqzu", "ydcroj", "ldvzgq", "zailgu", "xgqpsr", "wxdyho", "alrplq", "brklfk"};
        guessTheWord843.findSecretWord(wordlist);
    }
}
