package hackerrank;

/**
 * Created by adarsh.sharma on 25/07/18.
 */
public class Candies {
//    static long candies(int n, int[] arr) {
//        Deque<Integer> dq = new LinkedList<>();
//        int[] c = new int[n];
//        for(int i=0;i<n;i++) {
//            if(dq.isEmpty() || arr[i] <= arr[dq.peekLast()]) {
//                dq.addLast(i);
//            } else {
//                int v = Math.max(1, c[dq.peekLast()]);
//                c[i] = v+1;
//                while(!dq.isEmpty()) {
//                    int j = dq.pollLast();
//                    c[j] = Math.max(c[j], v);
//                    v++;
//                }
//                dq.addLast(i);
//            }
//        }
//        int v = 1;
//        while(!dq.isEmpty()) {
//            int j = dq.pollLast();
//            c[j] = Math.max(c[j], v);
//            v++;
//        }
//
//        int sum = 0;
//        for(int i=0;i<n;i++) {
//            sum+=c[i];
//        }
//        return sum;
//
//    }

    static long candies(int n, int[] arr) {
        int[] c = new int[n];
        c[0] = 1;
        for(int i=1;i<n;i++) {
            if(arr[i] <= arr[i-1]) {
                c[i] = 1;
            } else {
                c[i] = c[i-1] + 1;
            }
        }

        for(int i=n-2;i>=0;i--) {
            if(arr[i] > arr[i+1]) {
                c[i] = Math.max(c[i], c[i+1] + 1);
            }
        }
        int sum = 0;
        for(int i=0;i<n;i++) {
            sum+=c[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {10, 1, 2, 3, 4, 3, 2, 5, 8, 9, 10, 1};
        System.out.println(Candies.candies(12, a));
    }
}
