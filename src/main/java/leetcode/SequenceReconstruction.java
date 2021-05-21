package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 24/08/18.
 */
public class SequenceReconstruction {
//    class Node {
//        int val;
//        Set<Integer> predSet;
//
//        public Node(int val) {
//            this.val = val;
//            this.predSet = new HashSet<>();
//        }
//    }

//    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
//        if (seqs.size() == 0) {
//            return org.length == 0;
//        }
//
//        Map<Integer, Node> mp = new HashMap<>();
//
//        for (List<Integer> seq : seqs) {
//            if (seq.size() > 0) {
//                int val = seq.get(0);
//                Node prev = mp.computeIfAbsent(seq.get(0), k -> new Node(val));
//                for (int i = 1; i < seq.size(); i++) {
//                    int curVal = seq.get(i);
//                    Node cur = mp.computeIfAbsent(seq.get(i), k -> new Node(curVal));
//                    cur.predSet.add(prev.val);
//                    prev = cur;
//                }
//            }
//        }
//
//        if (mp.size() != org.length) {
//            return false;
//        }
//
//        Set<Node> set = new HashSet<>(mp.values());
//
//        int i = 0;
//        Set<Integer> eligibleSet = new HashSet<>();
//        while (!set.isEmpty()) {
//            Set<Integer> newEligibleSet = new HashSet<>();
//            for (Node node : set) {
//                node.predSet.removeAll(eligibleSet);
//                if (node.predSet.size() == 0) {
//                    newEligibleSet.add(node.val);
//                }
//            }
//
//            if (newEligibleSet.size() != 1) {
//                return false;
//            }
//            Node n = mp.get(newEligibleSet.iterator().next());
//            set.remove(n);
//            eligibleSet = newEligibleSet;
//
//            if (org[i] != n.val) {
//                return false;
//            }
//            i++;
//        }
//
//        return true;
//    }

    class Node {
        int val;
        Set<Integer> predSet;
        Set<Integer> succSet;

        public Node(int val) {
            this.val = val;
            this.predSet = new HashSet<>();
            this.succSet = new HashSet<>();
        }
    }

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (seqs.size() == 0) {
            return org.length == 0;
        }

        Map<Integer, Node> mp = new HashMap<>();

        for (List<Integer> seq : seqs) {
            if (seq.size() > 0) {
                int val = seq.get(0);
                Node prev = mp.computeIfAbsent(seq.get(0), k -> new Node(val));
                for (int i = 1; i < seq.size(); i++) {
                    int curVal = seq.get(i);
                    Node cur = mp.computeIfAbsent(seq.get(i), k -> new Node(curVal));
                    cur.predSet.add(prev.val);
                    prev.succSet.add(cur.val);
                    prev = cur;
                }
            }
        }

        if (mp.size() != org.length) {
            return false;
        }

        // Set<Node> set = new HashSet<>(mp.values());
        Map<Integer, Set<Integer>> predCountMap = new HashMap<>();
        for (Node node : mp.values()) {
            predCountMap.computeIfAbsent(node.predSet.size(), k -> new HashSet<>()).add(node.val);
        }

        int i = 0;
        while (i < org.length) {
            Set<Integer> eligibleSet = predCountMap.get(0);
            if (eligibleSet == null || eligibleSet.size() != 1) {
                return false;
            }

            Node node = mp.get(eligibleSet.iterator().next());
            if (org[i++] != node.val) {
                return false;
            }

            predCountMap.remove(0);
            for (Integer succ : node.succSet) {
                Node succNode = mp.get(succ);
                predCountMap.get(succNode.predSet.size()).remove(succNode.val);
                succNode.predSet.remove(node.val);
                predCountMap.computeIfAbsent(succNode.predSet.size(), k -> new HashSet<>()).add(succNode.val);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SequenceReconstruction seq = new SequenceReconstruction();
        int[] org = {4, 1, 5, 2, 6, 3};
        List<List<Integer>> seqs = new ArrayList<>();
        seqs.add(Arrays.asList(5, 2, 6, 3));
        seqs.add(Arrays.asList(4, 1, 5, 2));
        System.out.println(seq.sequenceReconstruction(org, seqs));
    }
}
