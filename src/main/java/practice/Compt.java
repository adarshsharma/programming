package practice;

public class Compt {

  public String maximumNumber(String num, int[] change) {
    char[] arr = num.toCharArray();
    boolean changeDone = false;
    for (int i = 0; i < arr.length; i++) {
      int v = arr[i] - '0';
      if (change[v] > v) {
        arr[i] = (char) ('0' + change[v]);
        changeDone = true;
      } else if (change[v] < v) {
        if (changeDone) {
          break;
        }
      }
    }

    return String.valueOf(arr);
  }

  public static void main(String[] args) {
    System.out
        .println(new Compt().maximumNumber("334111", new int[]{0, 9, 2, 3, 3, 2, 5, 5, 5, 5}));
  }

  // public int getLucky(String str, int k) {
  //   StringBuilder sb = new StringBuilder();
  //   for(char c: str.toCharArray()) {
  //     sb.append(c - 'a' + 1);
  //   }
  //   String s = sb.toString();
  //
  //   Integer ns = 0;
  //   for(int i=0;i<k;i++) {
  //     for(char c: s.toCharArray()) {
  //       ns += (c - '0');
  //     }
  //     s = ns.toString();
  //     ns = 0;
  //   }
  //
  //   return Integer.valueOf(s);
  // }
  //
  // public static void main(String[] args) {
  //   System.out.println(new Compt().getLucky("leetcode", 2));
  // }

  // public int[] canSeePersonsCount(int[] heights) {
  //   int n = heights.length;
  //   int[] lst = new int[n];
  //   int[] res = new int[n];
  //
  //   int idx = n - 1;
  //   lst[n - 1] = heights[n - 1];
  //   res[n - 1] = 0;
  //
  //   for (int i = n - 2; i >= 0; i--) {
  //     int pos = Arrays.binarySearch(lst, idx, n, heights[i]);
  //     pos = -(pos + 1);
  //     if (pos == n) {
  //       res[i] = n - idx;
  //     } else {  // should always be negative
  //       res[i] = pos - idx + 1;
  //     }
  //     pos--;
  //     lst[pos] = heights[i];
  //     idx = pos;
  //   }
  //
  //   return res;
  // }

  // public static void main(String[] args) {
  //   int[] heights = {10, 6, 8, 5, 11, 9};
  //   int[] res = new Compt().canSeePersonsCount(heights);
  //   Arrays.stream(res).forEach(System.out::println);
  //   // heights = new int[]{5, 1, 2, 3, 10};
  //   // res = new Compt().canSeePersonsCount(heights);
  //   // Arrays.stream(res).forEach(System.out::println);
  // }

  // class SegmentTree {
  //
  //   int[] segArray;
  //   int n;
  //
  //   public SegmentTree(int[] input) {
  //     n = input.length;
  //     this.segArray = new int[2 * n];
  //     System.arraycopy(input, 0, segArray, n, n);
  //
  //     for (int i = n - 1; i > 0; i--) {
  //       segArray[i] = Math.min(segArray[2 * i], segArray[2 * i + 1]);
  //     }
  //   }
  //
  //   //returns minimum element in range [left, right)
  //   int getMin(int left, int right) {
  //     int min = Integer.MAX_VALUE;
  //     left = left + n;
  //     right = right + n;
  //
  //     while (left < right) {
  //       if (left % 2 == 1) {
  //         min = Math.min(min, segArray[left]);
  //         left++;
  //       }
  //       if (right % 2 == 1) {
  //         right--;
  //         min = Math.min(min, segArray[right]);
  //       }
  //       left = left / 2;
  //       right = right / 2;
  //     }
  //
  //     return min;
  //   }
  //
  //   //updates pos with new value val
  //   void update(int pos, int val) {
  //     segArray[pos + n] = val;
  //     int p = (pos + n) / 2;
  //     while (p > 0) {
  //       segArray[p] = Math.min(segArray[2 * p], segArray[2 * p + 1]);
  //       p = p / 2;
  //     }
  //   }
  // }

  // static class T {
  //
  //   int t;
  //   boolean a;
  //   int id;
  //
  //   public T(int t, boolean a, int id) {
  //     this.t = t;
  //     this.a = a;
  //     this.id = id;
  //   }
  // }
  //
  // public int smallestChair(int[][] times, int targetFriend) {
  //   int n = times.length;
  //   TreeSet<Integer> freeSeats = new TreeSet<>();
  //   for (int i = 0; i < n; i++) {
  //     freeSeats.add(i);
  //   }
  //
  //   List<T> lst = new ArrayList<>();
  //   for (int i = 0; i < n; i++) {
  //     int[] time = times[i];
  //     lst.add(new T(time[0], true, i));
  //     lst.add(new T(time[1], false, i));
  //   }
  //
  //   lst.sort((f, s) -> {
  //     if (f.t != s.t) {
  //       return f.t - s.t;
  //     }
  //     if (f.a) {
  //       return 1;
  //     } else {
  //       return -1;
  //     }
  //   });
  //
  //   Map<Integer, Integer> seats = new HashMap<>();
  //   for (T t : lst) {
  //     if (t.a) {
  //       Integer open = freeSeats.first();
  //       if (t.id == targetFriend) {
  //         return open;
  //       }
  //       seats.put(t.id, open);
  //       freeSeats.remove(open);
  //     } else {
  //       Integer open = seats.remove(t.id);
  //       freeSeats.add(open);
  //     }
  //   }
  //
  //   return 0;
  // }
  //
  // public static void main(String[] args) {
  //   int[][] times = {{1, 4}, {2, 3}, {4, 6}};
  //   System.out.println(new Compt().smallestChair(times, 1));
  // }

}
