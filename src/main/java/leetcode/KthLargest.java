package leetcode;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 28/07/18.
 */
public class KthLargest {
    class MinHeap {
        ArrayList<Integer> arr;
        int heapSize;

        public MinHeap(int[] a, int k) {
            this.arr = new ArrayList<>();
            int i=0;
            for(;i<k;i++) {
                this.arr.add(a[i]);
            }
            heapSize = k;
            buildMinHeap();

            for(;i<a.length;i++) {
                if(a[i] > arr.get(0)) {
                    arr.set(0, a[i]);
                    minHeapify(0);
                }
            }
        }

        private void buildMinHeap() {
            for (int i = arr.size() / 2 - 1; i >= 0; i--) {
                minHeapify(i);
            }
        }

        public int top() {
            return arr.get(0);
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
            int temp = arr.get(i);
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
    }

    public int findKthLargest(int[] nums, int k) {
        MinHeap minHeap = new MinHeap(nums, k);
        return minHeap.top();
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(new KthLargest().findKthLargest(nums, 4));
    }
}
