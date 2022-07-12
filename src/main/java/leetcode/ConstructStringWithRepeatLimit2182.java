package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class ConstructStringWithRepeatLimit2182 {

  public String repeatLimitedString(String s, int repeatLimit) {
    TreeMap<Character, Integer> tmap = new TreeMap<>();
    for (char c : s.toCharArray()) {
      tmap.merge(c, 1, Integer::sum);
    }

    StringBuilder sb = new StringBuilder();
    while (!tmap.keySet().isEmpty()) {
      Map.Entry<Character, Integer> lastEntry = tmap.lastEntry();
      append(sb, tmap, repeatLimit, tmap.lastEntry());
      if (tmap.containsKey(lastEntry.getKey())) {
        Map.Entry<Character, Integer> secondLastEntry = tmap.lowerEntry(lastEntry.getKey());
        if (secondLastEntry == null) {
          break;
        } else {
          append(sb, tmap, 1, secondLastEntry);
        }
      }
    }

    return sb.toString();
  }

  private void append(StringBuilder sb, TreeMap<Character, Integer> tmap, int limit,
      Map.Entry<Character, Integer> entry) {
    int count = Math.min(limit, entry.getValue());
    append(sb, entry.getKey(), count);
    if (entry.getValue() > limit) {
      tmap.merge(entry.getKey(), -limit, Integer::sum);
    } else {
      tmap.remove(entry.getKey());
    }
  }

  private void append(StringBuilder sb, char c, int count) {
    while (count > 0) {
      sb.append(c);
      count--;
    }
  }

  public static void main(String[] args) {
    System.out.println(new ConstructStringWithRepeatLimit2182().repeatLimitedString("bbbbbba", 2));
  }

}
