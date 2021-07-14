package leetcode.contest.weekly243;

public class Problem1 {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return (getVal(firstWord) + getVal(secondWord)) == getVal(targetWord);
    }

    private Long getVal(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) - 'a');
        }

        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(new Problem1().isSumEqual("acb", "cba", "cdb"));
    }
}
