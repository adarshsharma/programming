package leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LongestSubstringOfOneRepeatingCharacter2213 {

  // static class Node {
  //
  //   char c;
  //   int len;
  //   Node left;
  //   Node right;
  //
  //   public Node(char c, int len, Node left, Node right) {
  //     this.c = c;
  //     this.len = len;
  //     this.left = left;
  //     this.right = right;
  //   }
  // }
  //
  // static class LL {
  //   Node first;
  //   Node last;
  //
  //   void addLast(Node node) {
  //
  //   }
  // }

  class Data {

    char c;
    int count;

    public Data(char c, int count) {
      this.c = c;
      this.count = count;
    }
  }

  public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
    int[] res = new int[queryCharacters.length()];
    TreeMap<Integer, Data> tMap = new TreeMap<>();
    TreeMap<Integer, Integer> lenMap = new TreeMap<>();

    char[] chars = s.toCharArray();
    fillMap(tMap, lenMap, chars, 0, chars.length - 1);

    for (int i = 0; i < queryIndices.length; i++) {
      int idx = queryIndices[i];
      if (chars[idx] != queryCharacters.charAt(i)) {
        Integer lower = tMap.lowerKey(idx);
        Integer cur = tMap.floorKey(idx);
        Integer higher = tMap.higherKey(idx);
        int end = cur;

        if (lower == null) {
          lower = cur;
        }
        if (idx == (cur + tMap.get(cur).count - 1)) {
          if (higher == null) {
            end = idx;
          } else {
            end = higher + (tMap.get(higher).count - 1);
          }
        } else {
          end = cur + tMap.get(cur).count - 1;
        }

        reduceAndRemoveIfLast(lenMap, tMap.get(cur).count);
        tMap.remove(cur);
        if (tMap.containsKey(lower)) {
          reduceAndRemoveIfLast(lenMap, tMap.get(lower).count);
          tMap.remove(lower);
        }
        if (higher != null && (higher - 1) == cur && tMap.containsKey(higher)) {
          reduceAndRemoveIfLast(lenMap, tMap.get(higher).count);
          tMap.remove(higher);
        }

        chars[idx] = queryCharacters.charAt(i);
        fillMap(tMap, lenMap, chars, lower, end);
      }

      res[i] = lenMap.lastKey();
    }

    return res;
  }

  private void reduceAndRemoveIfLast(Map<Integer, Integer> lenMap, int curLen) {
    lenMap.merge(curLen, -1, Integer::sum);
    if (lenMap.get(curLen) == 0) {
      lenMap.remove(curLen);
    }
  }

  private void fillMap(TreeMap<Integer, Data> tMap, Map<Integer, Integer> lenMap, char[] chars,
      int start, int end) {
    int cur = start;
    for (int i = start + 1; i <= end; i++) {
      if (chars[i] != chars[cur]) {
        lenMap.merge(i - cur, 1, Integer::sum);
        tMap.put(cur, new Data(chars[cur], i - cur));
        cur = i;
      }
    }
    lenMap.merge(end - cur + 1, 1, Integer::sum);
    tMap.put(cur, new Data(chars[cur], end - cur + 1));
  }

  public static void main(String[] args) {
    // String s = "babacc";
    // String queryCharacters = "bcb";
    // int[] queryIndices = {1, 3, 3};

    String s = "seeu";
    String queryCharacters = "qjcqvs";
    int[] queryIndices = {3, 1, 0, 2, 1, 3};
    // ans: [2,1,1,2,2,1,1,1,1]
    // ans:
    /*
    seeq
    sjeq
    cjeq
    cjqq
    cvqq
    cvqs
     */

    int[] res = new LongestSubstringOfOneRepeatingCharacter2213().longestRepeating(s,
        queryCharacters, queryIndices);
    System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
  }

}
