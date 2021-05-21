package leetcode;

/**
 * Created by adarsh.sharma on 11/09/18.
 */
public class RLEIterator {
    int[] it;
    int index;
    int len;

    public RLEIterator(int[] A) {
        it = A.clone();
        index = 0;
        len = it.length;
    }

    public int next(int n) {
        while (n > 0 && index < len) {
            if (n <= it[index]) {
                it[index] -= n;
                int ret = it[index + 1];
                if (it[index] == 0) {
                    index += 2;
                }
                return ret;
            } else {
                n -= it[index];
                it[index] = 0;
                index += 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RLEIterator rleIterator = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});
        System.out.println(rleIterator.next(2));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(2));
    }
}
