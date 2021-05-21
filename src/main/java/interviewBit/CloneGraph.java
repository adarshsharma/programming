package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 13/07/18.
 */
public class CloneGraph {

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        UndirectedGraphNode res = new UndirectedGraphNode(node.label);
        Map<UndirectedGraphNode, UndirectedGraphNode> mp = new HashMap<>();
        Set<UndirectedGraphNode> visited;
        visited = new HashSet<>();
        mp.put(node, res);
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            UndirectedGraphNode cloneCur = mp.get(cur);
            if (!visited.contains(cur)) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    UndirectedGraphNode nc = mp.computeIfAbsent(n, n1 -> new UndirectedGraphNode(n1.label));
                    cloneCur.neighbors.add(nc);
                    if (!visited.contains(n)) {
                        q.add(n);
                    }
                }
                visited.add(cur);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        UndirectedGraphNode one = new UndirectedGraphNode(703);
        UndirectedGraphNode two = new UndirectedGraphNode(279);
        UndirectedGraphNode three = new UndirectedGraphNode(43);
        one.neighbors.add(one);
        one.neighbors.add(two);
        one.neighbors.add(three);
        two.neighbors.add(one);
        two.neighbors.add(two);
        two.neighbors.add(three);
        three.neighbors.add(one);
        three.neighbors.add(two);

        UndirectedGraphNode res = new CloneGraph().cloneGraph(one);
        System.out.println("test");
    }
}
