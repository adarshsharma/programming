package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeOfCoprimes1766 {

  private final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
  private int[] facts;
  private List<Integer> path;
  private Map<Integer, List<Integer>> em;

  public int[] getCoprimes(int[] nums, int[][] edges) {
    path = new ArrayList<>();
    facts = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      facts[i] = getFact(nums[i]);
    }

    populatePathMap(edges);

    int[] result = new int[nums.length];
    result[0] = -1;
    int idx = 0;
    path.add(0);

    fill(nums, result, idx);
    return result;
  }

  private void populatePathMap(int[][] edges) {
    Map<Integer, Set<Integer>> tempMap = new HashMap<>();
    em = new HashMap<>();

    for (int[] edge : edges) {
      if (!tempMap.containsKey(edge[0])) {
        tempMap.put(edge[0], new HashSet<>());
      }
      tempMap.get(edge[0]).add(edge[1]);

      if (!tempMap.containsKey(edge[1])) {
        tempMap.put(edge[1], new HashSet<>());
      }
      tempMap.get(edge[1]).add(edge[0]);

    }

    int node = 0;

    populate(tempMap, node);
  }

  private void populate(Map<Integer, Set<Integer>> tempMap, int node) {
    if (tempMap.containsKey(node)) {
      Set<Integer> children = tempMap.remove(node);
      for (Integer child : children) {
        tempMap.get(child).remove(node);
        if (tempMap.get(child).size() == 0) {
          tempMap.remove(child);
        }

        if (!em.containsKey(node)) {
          em.put(node, new ArrayList<>());
        }
        em.get(node).add(child);
        populate(tempMap, child);
      }
    }
  }

  private void fill(int[] nums, int[] result, int idx) {
    if (em.containsKey(idx)) {
      List<Integer> children = em.get(idx);
      for (int i = 0; i < children.size(); i++) {
        int childIdx = children.get(i);
        int child = getFact(nums[childIdx]);

        int j = path.size() - 1;
        for (; j >= 0; j--) {
          if ((child & facts[path.get(j)]) == 0) {
            result[childIdx] = path.get(j);
            break;
          }
        }
        if (j == -1) {
          result[childIdx] = -1;
        }

        path.add(childIdx);
        fill(nums, result, childIdx);
        path.remove(path.size() - 1);
      }
    }
  }


  private int getFact(int n) {
    int res = 0;
    for (int i = 0; i < primes.length; i++) {
      if (n % primes[i] == 0) {
        res += (1 << i);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, 3, 2};
    int[][] edges = {{0, 1}, {2, 1}, {3, 1}};
    int[] result = new TreeOfCoprimes1766().getCoprimes(nums, edges);
    Arrays.stream(result).forEach(System.out::println);
  }

}
