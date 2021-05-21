package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 14/07/18.
 */
public class CommutableIslandsPrims {

    public static class NodeValue implements Comparable<NodeValue> {
        Integer label;
        Integer val;

        public NodeValue(Integer label, Integer val) {
            this.label = label;
            this.val = val;
        }

        @Override
        public int compareTo(NodeValue o) {
            return this.val.compareTo(o.val);
        }
    }

    public class MinHeap {
        ArrayList<NodeValue> arr;
        Map<Integer, Integer> mp;

        public MinHeap(Map<Integer, Integer> mp) {
            this.arr = new ArrayList<>();
            this.mp = mp;
        }

        public void heapDecreaseKey(int label, int val) {
            int i = mp.get(label);
            arr.get(i).val = val;
            while (i >= 0) {
                if (arr.get(parent(i)).compareTo(arr.get(i)) > 0) {
                    swap(i, parent(i));
                    i = parent(i);
                } else {
                    break;
                }
            }
        }

        public void addKey(int label, int val) {
            NodeValue node = new NodeValue(label, val);
            arr.add(node);
            mp.put(label, arr.size() - 1);
            heapDecreaseKey(label, val);
        }

        private void minHeapify(int i) {
            while (true) {
                int l = left(i);
                int r = right(i);
                int smallest = i;
                if (l < arr.size() && arr.get(l).compareTo(arr.get(i)) < 0) {
                    smallest = l;
                }
                if (r < arr.size() && arr.get(r).compareTo(arr.get(smallest)) < 0) {
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
            NodeValue temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);

            mp.put(temp.label, j);
            mp.put(arr.get(i).label, i);
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

        public boolean isEmpty() {
            return arr.isEmpty();
        }

        public NodeValue removeTop() {
            swap(0, arr.size() - 1);
            NodeValue remove = arr.remove(arr.size() - 1);
            mp.remove(remove.label);
            minHeapify(0);
            return remove;
        }
    }

    public int solve(int A, List<List<Integer>> B) {
        int minCost = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        MinHeap minHeap = new MinHeap(mp);

        Map<Integer, Map<Integer, Integer>> edges = new HashMap<>();
        Set<Integer> connected = new HashSet<>();

        for (List<Integer> b : B) {
            Map<Integer, Integer> map = edges.computeIfAbsent(b.get(0), k -> new HashMap<>());
            map.put(b.get(1), b.get(2));
            map = edges.computeIfAbsent(b.get(1), k -> new HashMap<>());
            map.put(b.get(0), b.get(2));
        }

        for (int i = 2; i <= A; i++) {
            minHeap.addKey(i, Integer.MAX_VALUE);
        }
        minHeap.addKey(1, 0);

        while (!minHeap.isEmpty()) {
            NodeValue node = minHeap.removeTop();
            connected.add(node.label);
            minCost += node.val;

            Map<Integer, Integer> map = edges.get(node.label);

            for (Integer childLabel : map.keySet()) {
                if (!connected.contains(childLabel)) {
                    minHeap.heapDecreaseKey(childLabel, Math.min(minHeap.arr.get(mp.get(childLabel)).val, map.get(childLabel)));
                }
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        List<List<Integer>> B = new ArrayList<>();
        B.add(Arrays.asList(1, 2, 10));
        B.add(Arrays.asList(2, 3, 5));
        B.add(Arrays.asList(1, 3, 9));

        System.out.println(new CommutableIslandsPrims().solve(3, B));
    }
}
