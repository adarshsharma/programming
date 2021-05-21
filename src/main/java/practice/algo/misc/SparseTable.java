package practice.algo.misc;

/**
 * Created by adarsh.sharma on 20/06/18.
 */
//Creates SparseTable for a given array and gives functionality to get min or range [l, r)
public class SparseTable {
    int[] logTable;
    int[][] st;

    public SparseTable(int[] input) {
        logTable = new int[input.length + 1];
        for (int i = 2; i < logTable.length; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        st = new int[logTable[logTable.length - 1] + 1][input.length];
        st[0] = input;

        for (int r = 1; r < st.length; r++) {
            for (int i = 0; i + (1 << r) <= input.length; i++) {
                st[r][i] = Math.min(st[r - 1][i], st[r - 1][i + (1 << r - 1)]);
            }
        }
    }

    //returns minimum value in range [l, r)
    public int getMin(int l, int r) {
        int log = logTable[r - l];
        return Math.min(st[log][l], st[log][r - (1 << log)]);
    }

    public static void main(String[] args) {
        int[] input = {5, 2, 4, 7, 6, 3, 2, 1};
        SparseTable sparseTable = new SparseTable(input);
        System.out.println(sparseTable.getMin(0, 1));
        System.out.println(sparseTable.getMin(0, 2));
        System.out.println(sparseTable.getMin(0, 7));
        System.out.println(sparseTable.getMin(3, 6));
        System.out.println(sparseTable.getMin(0, 8));
    }
}
