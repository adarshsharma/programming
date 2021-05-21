package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyOfMostFrequentElement1838 {

  public static int maxFrequency(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.merge(num, 1, Integer::sum);
    }

    List<Integer> a = map.keySet().stream().sorted().collect(Collectors.toList());

    int l = a.size();
    int maxF = 1;
    int start = l - 2;
    int count = 0;
    int tempK = k;

    for (int cur = l - 1; cur >= 0; cur--) {
      int curKey = a.get(cur);
      int curValue = map.get(curKey);
      if (cur < l - 1) {
        tempK += (count * (a.get(cur + 1) - curKey));
        count -= curValue;
      }

      while (tempK > 0 && start >= 0) {
        int startKey = a.get(start);
        int startF = map.get(startKey);

        int diff = curKey - startKey;
        if (tempK >= diff * startF) {
          tempK -= (diff * startF);
          count += startF;
          start--;
        } else {
          int c = tempK / diff;
          maxF = Math.max(count + c + curValue, maxF);
          break;
        }
      }
      if (start == -1) {
        maxF = Math.max(count + curValue, maxF);
      }
    }

    return maxF;
  }


  public static void main(String[] args) {
    int[] nums = {1, 4, 8, 13};
    int k = 5;
    System.out.println(maxFrequency(nums, k));
  }
}
