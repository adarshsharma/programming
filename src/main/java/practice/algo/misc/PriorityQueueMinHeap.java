package practice.algo.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 15/07/18.
 */
public class PriorityQueueMinHeap<L, V extends Comparable<V>> {
    ArrayList<Node> arr;
    Map<L, Integer> labelMap;

    class Node {
        L label;
        V value;

        public Node(L label, V value) {
            this.label = label;
            this.value = value;
        }
    }

    public PriorityQueueMinHeap() {
        this.arr = new ArrayList<>();
        this.labelMap = new HashMap<>();
    }

    public V poll() {
        if (arr.size() == 0) {
            return null;
        }

        Node node = arr.get(0);
        arr.set(0, arr.get(arr.size() - 1));
        labelMap.put(arr.get(0).label, 0);
        arr.remove(arr.size() - 1);
        minHeapify(0);
        labelMap.remove(node.label);
        return node.value;
    }

    public void addOrUpdate(L label, V value) {
        if (!labelMap.containsKey(label)) {
            Node node = new Node(label, value);
            arr.add(node);
            labelMap.put(label, arr.size() - 1);
            heapifyBottomUp(arr.size() - 1);
        } else {
            Node node = arr.get(labelMap.get(label));
            if (node.value.compareTo(value) < 0) {
                node.value = value;
                minHeapify(labelMap.get(label));
            } else if (node.value.compareTo(value) > 0) {
                node.value = value;
                heapifyBottomUp(labelMap.get(label));
            }
        }
    }

    private void heapifyBottomUp(int i) {
        while (i > 0) {
            int parent = parent(i);
            if (arr.get(i).value.compareTo(arr.get(parent).value) < 0) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void minHeapify(int i) {
        while (true) {
            int l = left(i);
            int r = right(i);
            int smallest = i;
            if (l < arr.size() && arr.get(l).value.compareTo(arr.get(i).value) < 0) {
                smallest = l;
            }
            if (r < arr.size() && arr.get(r).value.compareTo(arr.get(smallest).value) < 0) {
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
        L iLabel = arr.get(i).label;
        L jLabel = arr.get(j).label;

        Node temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);

        labelMap.put(iLabel, j);
        labelMap.put(jLabel, i);
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
        Integer[] a = {5, 2, 7, 8, 4, 9, 3};
        PriorityQueueMinHeap<Integer, Integer> minHeap = new PriorityQueueMinHeap<>();
        for (int i = 0; i < a.length; i++) {
            minHeap.addOrUpdate(i, a[i]);
        }
        minHeap.addOrUpdate(4, 1);
        while(minHeap.arr.size() > 0) {
            System.out.println(minHeap.poll());
        }
    }
}
