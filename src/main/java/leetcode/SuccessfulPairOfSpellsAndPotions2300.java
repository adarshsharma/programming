package leetcode;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SuccessfulPairOfSpellsAndPotions2300 {

  // public int[] successfulPairs(int[] spells, int[] potions, long success) {
  //   TreeMap<Integer, Integer> map = new TreeMap<>();
  //   for (int potion : potions) {
  //     map.merge(potion, 1, Integer::sum);
  //   }
  //   long[] arr = new long[map.size()];
  //   int[] freq = new int[map.size()];
  //   long[] prefixSum = new long[map.size()];
  //
  //   int i = arr.length - 1;
  //   while (i >= 0) {
  //     int key = map.lastKey();
  //     arr[i] = key;
  //     freq[i] = map.remove(key);
  //     prefixSum[i] = freq[i] + ((i != arr.length - 1) ? prefixSum[i + 1] : 0);
  //     i--;
  //   }
  //
  //   int[] pairs = new int[spells.length];
  //   for (i = 0; i < spells.length; i++) {
  //     int spell = spells[i];
  //     long req = success / spell;
  //     if (success % spell != 0) {
  //       req++;
  //     }
  //
  //     int idx = Arrays.binarySearch(arr, 0, arr.length, req);
  //     if (idx < 0) {
  //       idx = -(idx + 1);
  //     }
  //     pairs[i] = (idx == prefixSum.length) ? 0 : Long.valueOf(prefixSum[idx]).intValue();
  //   }
  //
  //   return pairs;
  // }

  public int[] successfulPairs(int[] spells, int[] potions, long success) {
    Arrays.sort(potions);
    int[] arr=new int[spells.length];
    for(int i=0; i<spells.length; i++){
      int start=0; int end=potions.length-1;
      while(start<=end){
        int mid=(start+end)/2;
        long mul=spells[i]*(long)potions[mid];
        if(mul>=success){ end=mid-1; }
        else{ start=mid+1; }
      }
      arr[i]=potions.length-start;
    }
    return arr;
  }

  public static void main(String[] args) {
    int[] spells = {3, 1, 2};
    int[] potions = {8, 5, 8};
    long success = 16;
    int[] res = new SuccessfulPairOfSpellsAndPotions2300().successfulPairs(spells, potions,
        success);
    System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
  }

}
