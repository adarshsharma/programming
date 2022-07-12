package leetcode;

import java.util.TreeMap;

public class KthLargestInArray {

  public String kthLargestNumber(String[] nums, int k) {
    TreeMap<String, Integer> tm = new TreeMap<>((o1, o2) -> {
      if (o1.length() != o2.length()) {
        return o1.length() - o2.length();
      }
      return o1.compareTo(o2);
    });

    int count = 0;
    for (String num : nums) {
      tm.merge(num, 1, Integer::sum);
      if (count <= k) {
        count++;
      }
      if (count > k) {
        String smallest = tm.firstKey();
        if (tm.get(smallest) == 1) {
          tm.remove(smallest);
        } else {
          tm.merge(smallest, -1, Integer::sum);
        }
      }
    }

    return tm.firstKey();
  }

  public static void main(String[] args) {
    String[] nums = {"2", "21", "12", "1"};
    int k = 3;
    System.out.println(new KthLargestInArray().kthLargestNumber(nums, k));
  }
}
