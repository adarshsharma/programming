package leetcode;

public class RemoveAllOccurrencesOfSubstring1910 {

    public String removeOccurrences(String s, String part) {
        while (true) {
            s = s.replaceAll(part, "");
            if (!s.contains(part)) {
                break;
            }
        }

        return s;
    }

    public static void main(String[] args) {
        // anser is "dab"
        System.out.println(
            new RemoveAllOccurrencesOfSubstring1910().removeOccurrences("daabcbaabcbc", "abc"));
    }
}
