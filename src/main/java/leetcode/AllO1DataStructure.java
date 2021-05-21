package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by adarsh.sharma on 06/08/18.
 */
public class AllO1DataStructure {
//    class Node {
//        String key;
//        int val;
//
//        public Node(String key, int val) {
//            this.key = key;
//            this.val = val;
//        }
//    }
//
//    Map<String, Integer> locMap;
//    Map<Integer, Integer> firstMap;
//    Map<Integer, Integer> lastMap;
//    List<Node> list;
//
//    /**
//     * Initialize your data structure here.
//     */
//
//    public AllO1DataStructure() {
//        locMap = new HashMap<>();
//        firstMap = new HashMap<>();
//        lastMap = new HashMap<>();
//        list = new ArrayList<>();
//    }
//
//    /**
//     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
//     */
//    public void inc(String key) {
//        if (!locMap.containsKey(key)) {
//            Node node = new Node(key, 1);
//            list.add(node);
//            locMap.put(key, list.size() - 1);
//            lastMap.put(1, list.size() - 1);
//            if (!firstMap.containsKey(1)) {
//                firstMap.put(1, list.size() - 1);
//            }
//        } else {
//            int val = list.get(locMap.get(key)).val;
//            int newPos = firstMap.get(val);
//            int oldPos = locMap.get(key);
//            swap(oldPos, newPos);
//            list.get(locMap.get(key)).val++;
//            val++;
//            lastMap.put(val, newPos);
//            if (!firstMap.containsKey(val)) {
//                firstMap.put(val, newPos);
//            }
//
//            if (lastMap.get(val - 1) == oldPos) {
//                lastMap.remove(val - 1);
//                if (oldPos > 0 && list.get(oldPos).val == val - 1) {
//                    lastMap.put(val - 1, oldPos);
//                }
//            }
//
//            firstMap.remove(val - 1);
//            if ((newPos + 1) < list.size() && list.get(newPos + 1).val == val - 1) {
//                firstMap.put(val - 1, newPos + 1);
//            }
//        }
//    }
//
//    private void swap(int i, int j) {
//        Node temp = list.get(i);
//        list.set(i, list.get(j));
//        list.set(j, temp);
//        locMap.put(list.get(i).key, i);
//        locMap.put(list.get(j).key, j);
//    }
//
//    /**
//     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
//     */
//    public void dec(String key) {
//        if (locMap.containsKey(key)) {
//            int oldPos = locMap.get(key);
//            Node node = list.get(oldPos);
//            if (node.val == 1) {
//                int newPos = list.size() - 1;
//                swap(oldPos, newPos);
//                locMap.remove(key);
//                list.remove(list.size() - 1);
//                if (firstMap.get(node.val) == oldPos) {
//                    firstMap.remove(node.val);
//                    if (oldPos < list.size() && list.get(oldPos).val == node.val) {
//                        firstMap.put(node.val, oldPos);
//                    }
//                }
//                lastMap.remove(node.val);
//                if (list.get(list.size() - 1).val == 1) {
//                    lastMap.put(node.val, list.size() - 1);
//                }
//            } else {
//                int newPos = lastMap.get(node.val);
//                swap(oldPos, newPos);
//                node.val--;
//                if (firstMap.get(node.val + 1) == oldPos) {
//                    firstMap.remove(node.val + 1);
//                    if (list.get(oldPos).val == node.val + 1) {
//                        firstMap.put(node.val + 1, oldPos);
//                    } else {
//                        if (oldPos - 1 >= 0 && list.get(oldPos - 1).val == node.val + 1) {
//                            firstMap.put(node.val + 1, oldPos - 1);
//                        }
//                    }
//                }
//
//                lastMap.remove(node.val + 1);
//                if (newPos - 1 >= 0 && list.get(oldPos - 1).val == node.val + 1) {
//                    lastMap.put(node.val + 1, newPos - 1);
//                }
//
//                firstMap.put(node.val, newPos);
//                if (!lastMap.containsKey(node.val)) {
//                    lastMap.put(node.val, newPos);
//                }
//            }
//        }
//    }
//
//    /**
//     * Returns one of the keys with maximal value.
//     */
//    public String getMaxKey() {
//        return list.size() > 0 ? list.get(0).key : "";
//    }
//
//    /**
//     * Returns one of the keys with Minimal value.
//     */
//    public String getMinKey() {
//        return list.size() > 0 ? list.get(list.size() - 1).key : "";
//    }

    public static void main(String[] args) {
        AllO1DataStructure allO1DataStructure = new AllO1DataStructure();
        allO1DataStructure.inc("a");
        allO1DataStructure.inc("b");
        allO1DataStructure.inc("b");
        allO1DataStructure.inc("c");
        allO1DataStructure.inc("c");
        allO1DataStructure.inc("c");

        allO1DataStructure.dec("b");
        allO1DataStructure.dec("b");

        System.out.println(allO1DataStructure.getMinKey());

        allO1DataStructure.dec("a");

        System.out.println(allO1DataStructure.getMinKey());

    }

    Map<String, Node> mp;
    Node head;
    Node tail;

    class Node {
        int count;
        Node next;
        Node prev;
        Set<String> set;

        public Node(int count, String key) {
            this.count = count;
            this.set = new HashSet<>();
            this.set.add(key);
            this.prev = null;
            this.next = null;
        }
    }

    public AllO1DataStructure() {
        this.mp = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public void inc(String key) {
        if (!mp.containsKey(key)) {
            Node n = new Node(1, key);
            if (head == null) {
                head = n;
                tail = head;
                mp.put(key, n);
            } else {
                if (head.count == 1) {
                    head.set.add(key);
                    mp.put(key, head);
                } else {
                    n.next = head;
                    head.prev = n;
                    head = n;
                    mp.put(key, n);
                }
            }
        } else {
            Node node = mp.get(key);
            if (node.next != null) {
                node.set.remove(key);
                if (node.next.count == node.count + 1) {
                    node.next.set.add(key);
                    mp.put(key, node.next);
                    if (node.set.isEmpty()) {
                        node.next.prev = node.prev;
                        if (node.prev == null) {
                            head = node.next;
                        } else {
                            node.prev.next = node.next;
                        }
                    }
                } else {
                    Node n = new Node(node.count + 1, key);
                    mp.put(key, n);

                }
            } else {
                node.set.remove(key);
                Node n = new Node(node.count + 1, key);
                n.prev = tail;
                tail.next = n;
                tail = n;
                mp.put(key, n);
                if (node.set.size() == 0) {
                    tail.prev = node.prev;
                    if(tail.prev == null) {
                        head = tail;
                    } else {
                        tail.prev.next = tail;
                    }
                }
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {

    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return tail == null ? "" : tail.set.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return head == null ? "" : head.set.iterator().next();
    }
}
