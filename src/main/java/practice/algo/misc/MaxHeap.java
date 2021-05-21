package practice.algo.misc;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 15/07/18.
 */
public class MaxHeap {
    Integer[] arr;
    int heapSize;

    public MaxHeap(Integer[] arr) {
        this.arr = arr;
        this.heapSize = arr.length;
        buildMaxHeap();
    }

    private void buildMaxHeap() {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    private void maxHeapify(int i) {
        while (true) {
            int l = left(i);
            int r = right(i);
            int largest = i;
            if (l < heapSize && arr[l] > arr[i]) {
                largest = l;
            }
            if (r < heapSize && arr[r] > arr[largest]) {
                largest = r;
            }

            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    private void sort() {
        for (int i = arr.length - 1; i > 0; i--) {
            swap(i, 0);
            heapSize--;
            maxHeapify(0);
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        MaxHeap maxHeap = new MaxHeap(a);
//        maxHeap.sort();
        System.out.println(Arrays.asList(maxHeap.arr));

    }
}
