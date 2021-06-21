package leetcode;

public class StringCompression443 {

    public int compress(char[] chars) {
        int write = 0;
        int read = 1;
        int count = 1;
        int prev = 0;
        while (read < chars.length) {
            if (chars[read] == chars[prev]) {
                count++;
            } else {
                write = writeToArray(chars, write, prev, count);
                prev = read;
                count = 1;
            }
            read++;
        }

        if (count > 0) {
            write = writeToArray(chars, write, prev, count);
        }

        return write;
    }

    private int writeToArray(char[] chars, int widx, int ridx, int count) {
        chars[widx++] = chars[ridx];
        String num = (Integer.valueOf(count)).toString();
        if (count > 1) {
            for (int i = 0; i < num.length(); i++) {
                chars[widx++] = num.charAt(i);
            }
        }

        return widx;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'c', 'c', 'c'};
        System.out.println(new StringCompression443().compress(chars));
    }


}
