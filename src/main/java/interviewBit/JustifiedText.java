package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 05/07/18.
 */
public class JustifiedText {

    public ArrayList<String> fullJustify(List<String> words, int maxWidth) {
        ArrayList<String> result = new ArrayList<>();

        if (words == null || words.size() == 0) {
            return result;
        }


        int count = 0;
        int last = 0;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            count = count + words.get(i).length();

            if (count + i - last > maxWidth) {
                int wordsLen = count - words.get(i).length();
                int spaceLen = maxWidth - wordsLen;
                int eachLen = 1;
                int extraLen = 0;

                if (i - last - 1 > 0) {
                    eachLen = spaceLen / (i - last - 1);
                    extraLen = spaceLen % (i - last - 1);
                }

                StringBuilder sb = new StringBuilder();

                for (int k = last; k < i - 1; k++) {
                    sb.append(words.get(k));

                    int ce = 0;
                    while (ce < eachLen) {
                        sb.append(" ");
                        ce++;
                    }

                    if (extraLen > 0) {
                        sb.append(" ");
                        extraLen--;
                    }
                }

                sb.append(words.get(i - 1));//last words in the line
                //if only one word in this line, need to fill left with space
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }

                result.add(sb.toString());

                last = i;
                count = words.get(i).length();
            }
        }

        int lastLen = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = last; i < words.size() - 1; i++) {
            count = count + words.get(i).length();
            sb.append(words.get(i) + " ");
        }

        sb.append(words.get(words.size() - 1));
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        result.add(sb.toString());

        return result;
    }

    public ArrayList<String> myFullJustify(List<String> A, int L) {
        ArrayList<String> result = new ArrayList<>();
        if (A.size() == 0) {
            result.add("");
            return result;
        }

        int low = 0;
        int high = 0;
        int l = A.get(0).length();
        int emptyCount = 0;

        for (int i = 1; i < A.size(); i++) {
            String cur = A.get(i);
            if (cur.length() > 0) {
                if (l + 1 + cur.length() <= L) {
                    high++;
                    l = l + cur.length() + 1;
                } else {
                    int spaceCount = high - low - emptyCount;
                    int spaces = L - l + spaceCount;
                    int perSpaceChars = 0;
                    int rem = spaces;
                    if(spaceCount != 0) {
                        perSpaceChars = spaces / spaceCount;
                        rem = spaces - perSpaceChars * spaceCount;
                    }
                    String row = "";
                    row += A.get(low);
                    low++;
                    while (low <= high) {
                        String str = A.get(low);
                        if (str.length() > 0) {
                            row += getSpace(perSpaceChars);
                            if (rem > 0) {
                                row += " ";
                                rem--;
                            }
                            row += str;
                        }
                        low++;
                    }
                    result.add(row);

                    l = cur.length();
                    low = i;
                    high = i;
                    emptyCount = 0;
                }
            } else {
                emptyCount++;
            }
        }

        String row = A.get(low);
        low++;
        while (low <= high) {
            if (A.get(low).length() > 0) {
                row += " " + A.get(low);
            }
            low++;
        }
        row+=getSpace(L-row.length());
        result.add(row);

        return result;
    }

    private String getSpace(int count) {
        String space = "";
        while (count > 0) {
            space += " ";
            count--;
        }
        return space;
    }

    public static void main(String[] args) {
        List<String> A = Arrays.asList("");
        List<String> x = new JustifiedText().fullJustify(A, 10);
        System.out.println("start");
        x.forEach(a -> System.out.println("<" + a + ">"));
        System.out.println("end");

        x = new JustifiedText().myFullJustify(A, 10);
        System.out.println("start");
        x.forEach(a -> System.out.println("<" + a + ">"));
        System.out.println("end");
    }
}
