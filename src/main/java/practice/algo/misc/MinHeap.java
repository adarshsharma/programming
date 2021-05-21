package practice.algo.misc;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 15/07/18.
 */
public class MinHeap<T extends Comparable<T>> {
    ArrayList<T> arr;
    int heapSize;

    public MinHeap() {
        this.arr = new ArrayList<>();
    }

    private void minHeapify(int i) {
        while (true) {
            int l = left(i);
            int r = right(i);
            int smallest = i;
            if (l < heapSize && arr.get(l).compareTo(arr.get(i)) < 0) {
                smallest = l;
            }
            if (r < heapSize && arr.get(r).compareTo(arr.get(smallest)) < 0) {
                smallest = r;
            }

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    public static void main(String[] args) {
        Integer[] a = {5, 2, 7, 2, 4, 9, 3};
        MinHeap<Integer> minHeap = new MinHeap<>();
        System.out.println(minHeap.arr);

    }
}
