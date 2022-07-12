package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NamingACompany2306 {

  public static class Trie {

    Node root;

    public static class Node {

      boolean ending;
      int count;
      Node[] children = new Node[26];
      Set<Character> firstCharSet = new HashSet<>();
    }

    public Trie() {
      root = new Node();
    }

    public void add(String word) {
      Node cur = root;
      int i = word.length() - 1;
      while (i > 0) {
        if (cur.children[word.charAt(i) - 'a'] == null) {
          cur.children[word.charAt(i) - 'a'] = new Node();
        }
        cur = cur.children[word.charAt(i) - 'a'];
        i--;
      }
      cur.ending = true;
      cur.count++;
      cur.firstCharSet.add(word.charAt(0));
    }
  }

  private static class Data {

    int count;
    Set<Character> firstCharSet = new HashSet<>();

    public Data(int c) {
      count = c;
    }
  }

  public long distinctNames(String[] ideas) {
    int[] startCharCount = new int[26];
    long result = (long) ideas.length * (ideas.length - 1);
    // Trie trie = new Trie();
    Map<String, Data> suffixCount = new HashMap<>();

    for (String idea : ideas) {
      startCharCount[idea.charAt(0) - 'a']++;
      String suffix = idea.substring(1);
      Data data = suffixCount.get(suffix);
      if (data == null) {
        data = new Data(0);
        suffixCount.put(suffix, data);
      }
      data.count++;
      data.firstCharSet.add(idea.charAt(0));
      // trie.add(idea);
    }

    // Queue<Node> queue = new LinkedList<>();
    // queue.add(trie.root);
    //
    // while (!queue.isEmpty()) {
    //   Node node = queue.poll();
    //   Arrays.stream(node.children).filter(Objects::nonNull).forEach(queue::add);
    //   if (node.ending) {
    //     for (int firstChar : node.firstCharSet) {
    //       result += (2L * (ideas.length - node.count - startCharCount[firstChar - 'a'] - 1));
    //     }
    //   }
    // }

    for (Data data : suffixCount.values()) {
      for (int firstChar : data.firstCharSet) {
        result -= startCharCount[firstChar - 'a'] - 1;
        // result += (2L * (ideas.length - data.count - startCharCount[firstChar - 'a'] + 1));
      }
    }

    return result;

  }

  public static void main(String[] args) {
    String[] ideas = {"coffee", "donuts", "time", "toffee"};
    System.out.println(new NamingACompany2306().distinctNames(ideas));
  }

}
