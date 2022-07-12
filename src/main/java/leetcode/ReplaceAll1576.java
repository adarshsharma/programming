package leetcode;

public class ReplaceAll1576 {

    public String modifyString(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '?') {
                int l = 7;
                int r = 7;
                if (i != 0) {
                    l ^= 1 << (arr[i - 1] - 'a');
                }
                if (i != arr.length - 1) {
                    r ^= 1 << (arr[i + 1] - 'a');
                }

                int f = l & r & 7;
                int a = 1;
                int b = 2;
                if ((f & a) > 0) {
                    arr[i] = 'a';
                } else if ((f & b) > 0) {
                    arr[i] = 'b';
                } else {
                    arr[i] = 'c';
                }
            }
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        System.out.println(new ReplaceAll1576().modifyString("?sz"));
        // int a = 1;
        // int b = 2;
        // System.out.println((a & b));
    }

}
