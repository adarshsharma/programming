package interviewBit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by adarsh.sharma on 02/07/18.
 */
public class LRUCache {
    Map<Integer, Node> map;
    int c;
    LinkedList<Node> lruList = new LinkedList<>();


    class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.c = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        lruList.remove(node);
        lruList.addFirst(node);
        return node.value;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            lruList.remove(node);
            lruList.addFirst(node);
        } else {
            if (map.size() == c) {
                Node node = lruList.removeLast();
                map.remove(node.key);
            }
            Node n = new Node(key, value);
            n.value = value;
            map.put(key, n);
            lruList.addFirst(n);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.set(2, 1);
        System.out.println(lruCache.get(2));
        lruCache.set(3, 2);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
