package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetsII {

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Map<Integer, Integer> mp = new HashMap<>();
    for (int num : nums) {
      mp.merge(num, 1, Integer::sum);
    }
    int[] unums = new int[mp.size()];
    int[] freq = new int[mp.size()];
    int i = 0;
    for (int key : mp.keySet()) {
      unums[i] = key;
      freq[i++] = mp.get(key);
    }
    List<Integer> ls = new ArrayList<>();
    subset(unums, freq, res, 0, ls);
    return res;
  }

  private void subset(int[] unums, int[] freq, List<List<Integer>> res, int idx, List<Integer> ls) {
    if (idx == unums.length) {
      res.add(new ArrayList<>(ls));
      return;
    }

    for (int j = 0; j <= freq[idx]; j++) {
      if (j > 0) {
        ls.add(unums[idx]);
      }
      subset(unums, freq, res, idx + 1, ls);
    }
    for (int j = 0; j < freq[idx]; j++) {
      ls.remove(ls.size() - 1);
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 2};
    System.out.println(new SubsetsII().subsetsWithDup(nums));
  }

}
