package practice.algo.trees;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by adarsh.sharma on 13/06/18.
 */
//Created BIT of a given array and gives functionality to get the sum of first k elements
public class BinaryIndexedTree {
    int[] bit_arr;

    public BinaryIndexedTree(int[] input) {
        this.bit_arr = new int[input.length + 1];
        for (int i = 0; i < input.length; i++) {
            add(i + 1, input[i]);
        }
    }

    //adds val to idx th element
    void add(int idx, int val) {
        while (idx < bit_arr.length) {
            bit_arr[idx] += val;
            idx += idx & (-idx);
        }
    }

    //Computes sum of first idx elements
    int sum(int idx) {
        int result = 0;

        while (idx > 0) {
            result += bit_arr[idx];
            idx -= idx & (-idx);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 7, 3, 0, 7, 8, 3, 2, 6, 2, 1, 1, 4, 5};
        BinaryIndexedTree bit = new BinaryIndexedTree(input);
        System.out.println(IntStream.of(bit.bit_arr).boxed().collect(Collectors.toList()));
        System.out.println(bit.sum(13));
        System.out.println(bit.sum(5));
        System.out.println(bit.sum(4));
        bit.add(5, 2);
        System.out.println(bit.sum(4));
        System.out.println(bit.sum(5));
        System.out.println(bit.sum(13));
    }
}
