package practice.algo.trees;

/**
 * Created by adarsh.sharma on 12/06/18.
 */
/*
This function creates a segment tree for the given array and give functionalities to get minimum of a range and modify a value
 */
public class SegmentTree {
    int[] segArray;
    int n;

    public SegmentTree(int[] input) {
        n = input.length;
        this.segArray = new int[2 * n];
        System.arraycopy(input, 0, segArray, n, n);

        for (int i = n - 1; i > 0; i--) {
            segArray[i] = Math.min(segArray[2 * i], segArray[2 * i + 1]);
        }
    }

    //returns minimum element in range [left, right)
    int getMin(int left, int right) {
        int min = Integer.MAX_VALUE;
        left = left + n;
        right = right + n;

        while (left < right) {
            if (left % 2 == 1) {
                min = Math.min(min, segArray[left]);
                left++;
            }
            if (right % 2 == 1) {
                right--;
                min = Math.min(min, segArray[right]);
            }
            left = left / 2;
            right = right / 2;
        }

        return min;
    }

    //updates pos with new value val
    void update(int pos, int val) {
        segArray[pos + n] = val;
        int p = (pos + n) / 2;
        while (p > 0) {
            segArray[p] = Math.min(segArray[2 * p], segArray[2 * p + 1]);
            p = p / 2;
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 5, 3, 7, 3, 6, 5, 7};
        SegmentTree segmentTree = new SegmentTree(input);
        System.out.println(segmentTree.getMin(3, 8));
        segmentTree.update(5, 2);
        System.out.println(segmentTree.getMin(1, 7));

//        int[] input = {3, 5, 1, 7, 4, 6};
//        int[] input = {2, 5, 6, 9, 8};
//        SegmentTree segmentTree = new SegmentTree(input);
//        System.out.println(segmentTree.getMin(0, 4));
//        segmentTree.update(2, 4);
//        System.out.println(segmentTree.getMin(0, 5));
    }
}
