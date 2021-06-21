package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOrderOneDataStructure432 {

    static class Node {

        int f;
        Set<String> val;
        Node prev;
        Node next;

        public Node(int f) {
            this.f = f;
            val = new HashSet<>();
        }
    }

    static class DLL {

        Node head;
        Node tail;

        public DLL() {
            this.head = new Node(0);
            this.tail = new Node(-1);
            this.head.next = tail;
            this.tail.prev = head;
        }

        public void addAfter(Node first, Node second) {
            if (first.next != null) {
                second.next = first.next;
                first.next.prev = second;
                first.next = second;
                second.prev = first;
            } else {
                throw new RuntimeException("should never reach here");
            }
        }

        public void addBefore(Node first, Node second) {
            if (first.prev != null) {
                second.prev = first.prev;
                first.prev.next = second;
                first.prev = second;
                second.next = first;
            } else {
                throw new RuntimeException("should never reach here");
            }
        }

        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private DLL dll;
    Map<String, Integer> stringToCountMap;
    Map<Integer, Node> freqToNodeMap;


    /**
     * Initialize your data structure here.
     */
    public AllOrderOneDataStructure432() {
        this.dll = new DLL();
        stringToCountMap = new HashMap<>();
        freqToNodeMap = new HashMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (stringToCountMap.containsKey(key)) {
            int count = stringToCountMap.get(key);
            Node oldNode = freqToNodeMap.get(count);
            oldNode.val.remove(key);
            count++;

            if (!freqToNodeMap.containsKey(count)) {
                Node newNode = new Node(count);
                dll.addAfter(oldNode, newNode);
                freqToNodeMap.put(count, newNode);
            }

            Node newNode = freqToNodeMap.get(count);
            newNode.val.add(key);
            stringToCountMap.put(key, count);

            // Remove the node if this is the only key with this freq
            if (oldNode.val.size() == 0) {
                freqToNodeMap.remove(count - 1);
                dll.removeNode(oldNode);
            }

        } else {
            if (!freqToNodeMap.containsKey(1)) {
                Node node = new Node(1);
                dll.addAfter(dll.head, node);
                freqToNodeMap.put(1, node);
            }

            Node node = freqToNodeMap.get(1);
            node.val.add(key);
            stringToCountMap.put(key, 1);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        int count = stringToCountMap.get(key);
        Node oldNode = freqToNodeMap.get(count);
        oldNode.val.remove(key);
        count--;

        if (count > 0) {
            if (!freqToNodeMap.containsKey(count)) {
                Node newNode = new Node(count);
                dll.addBefore(oldNode, newNode);
                freqToNodeMap.put(count, newNode);
            }

            Node newNode = freqToNodeMap.get(count);
            newNode.val.add(key);
            stringToCountMap.put(key, count);
        } else {
            stringToCountMap.remove(key);
        }
        // Remove the node if this is the only key with this freq
        if (oldNode.val.size() == 0) {
            freqToNodeMap.remove(count + 1);
            dll.removeNode(oldNode);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return getKey(dll.tail.prev);
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return getKey(dll.head.next);
    }

    private String getKey(Node node) {
        if (node.f > 0) {
            return node.val.iterator().next();
        }
        return "";
    }

    public static void main(String[] args) {
        AllOrderOneDataStructure432 allOOne = new AllOrderOneDataStructure432();
        System.out.println(allOOne.getMaxKey());
        System.out.println(allOOne.getMinKey());
    }
}
