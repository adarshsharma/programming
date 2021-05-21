package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 18/07/18.
 */
public class SlidingWindowMaximum {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int w) {
        ArrayList<Integer> B = new ArrayList<>();
        if(w>A.size()) {
            B.add(A.stream().reduce(Integer.MIN_VALUE, (a, b) -> Math.max(a, b)));
        } else {
            MaxHeap maxHeap = new MaxHeap(A, w);
            B.add(A.get(maxHeap.getTop()));
            for(int i=w;i<A.size();i++) {
                maxHeap.replace(i-w, i, A);
                B.add(A.get(maxHeap.getTop()));
            }
        }

        return B;
    }

    public class MaxHeap {
        int[] arr;
        int heapSize;
        Map<Integer, Integer> mp = new HashMap<>();

        public MaxHeap(List<Integer> A, int w) {
            this.arr = new int[w];
            for(int i=0;i<w;i++) {
                this.arr[i] = i;
                mp.put(i, i);
            }
            this.heapSize = w;
            buildMaxHeap(A);
        }

        private void buildMaxHeap(List<Integer> A) {
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                maxHeapify(i, A);
            }
        }

        public int getTop() {
            return arr[0];
        }

        public void replace(int old, int newIdx, List<Integer> A) {
            int i = mp.get(old);
            arr[i] = newIdx;
            mp.put(newIdx, i);
            mp.remove(old);

            if(A.get(old) > A.get(newIdx)) {
                maxHeapify(i, A);
            } else if(A.get(old) < A.get(newIdx)) {
                maxHeapifyBottomUp(i, A);
            }
        }

        private void maxHeapifyBottomUp(int i, List<Integer> A) {
            while(i>0) {
                int parent = parent(i);
                if(A.get(arr[i]) > A.get(arr[parent])) {
                    swap(i, parent);
                }
                i = parent;
            }
        }

        private void maxHeapify(int i, List<Integer> A) {
            while (true) {
                int l = left(i);
                int r = right(i);
                int largest = i;
                if (l < heapSize && A.get(arr[l]) > A.get(arr[i])) {
                    largest = l;
                }
                if (r < heapSize && A.get(arr[r]) > A.get(arr[largest])) {
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

        private void swap(int i, int j) {
            mp.put(arr[i], j);
            mp.put(arr[j], i);
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
    }

    public static void main(String[] args) {
//        List<Integer> a = Arrays.asList(648, 614, 490, 138, 657, 544, 745, 582, 738, 229, 775);
        List<Integer> a = Arrays.asList(1,3,-1,-3,5,3,6,7);
        System.out.println(new SlidingWindowMaximum().slidingMaximum(a, 3));
    }
}
