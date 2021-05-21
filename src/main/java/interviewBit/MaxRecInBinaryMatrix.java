package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 17/07/18.
 */
public class MaxRecInBinaryMatrix {
//    class P {
//        int r;
//        int c;
//
//        public P(int r, int c) {
//            this.r = r;
//            this.c = c;
//        }
//    }
//    public int maximalRectangle(List<List<Integer>> A) {
//        if (A == null || A.size() == 0 || A.get(0).size() == 0) {
//            return 0;
//        }
//
//        int m = A.size();
//        int n = A.get(0).size();
//        P[] dp = new P[n];
//
//        dp[0] = A.get(0).get(0) == 1 ? new P(1, 1) : new P(0, 0);
//        int j = 1;
//        int max = dp[0].r * dp[0].c;
//        for (int i = 0; i < m; i++) {
//            for (; j < n; j++) {
//                if (A.get(i).get(j) == 0) {
//                    dp[j] = new P(0, 0);
//                } else {
//                    if (i == 0) {
//                        P left = dp[j - 1];
//                        dp[j] = new P(1, left.c + 1);
//                    } else if (j == 0) {
//                        P up = dp[0];
//                        dp[0] = new P(up.r + 1, 1);
//                    } else {
//                        P up = dp[j];
//                        P left = dp[j - 1];
//                        if (A.get(i).get(j - 1) == 0 || A.get(i - 1).get(j) == 0) {
//                            if(A.get(i).get(j - 1) == 0) {
//                                dp[j] = new P(1 + up.r, 1);
//                            } else {
//                                dp[j] = new P(1, left.c + 1);
//                            }
//                        } else {
//                            dp[j] = new P(Math.min(left.r, up.r + 1), Math.min(left.c, up.c+1));
//                        }
//                    }
//                }
//                if(max < (dp[j].r) * (dp[j].c)) {
//                    max = (dp[j].r) * (dp[j].c);
//                }
//            }
//            j = 0;
//        }
//
//        return max;
//    }

    public int maximalRectangle(List<List<Integer>> A) {
        if (A.size() == 0) return 0;
        int maxRec = 0;
        int m = A.size();
        int n = A.get(0).size();

        int[] h = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    h[j] = A.get(i).get((j));
                } else {
                    h[j] = A.get(i).get(j) == 0 ? 0 : h[j] + 1;
                }
            }

            int area = maxHistogram(h);
            if (area > maxRec) {
                maxRec = area;
            }
        }
        return maxRec;
    }

    public int maxHistogram(int input[]) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int i;
        for (i = 0; i < input.length; ) {
            if (stack.isEmpty() || input[stack.peekFirst()] <= input[i]) {
                stack.addFirst(i++);
            } else {
                maxArea = getMaxArea(input, stack, maxArea, i);
            }
        }
        while (!stack.isEmpty()) {
            maxArea = getMaxArea(input, stack, maxArea, i);
        }
        return maxArea;
    }

    private int getMaxArea(int[] input, Deque<Integer> stack, int maxArea, int i) {
        int area;
        int top = stack.pollFirst();
        //if stack is empty means everything till i has to be
        //greater or equal to input[top] so get area by
        //input[top] * i;
        if (stack.isEmpty()) {
            area = input[top] * i;
        }
        //if stack is not empty then everything from i-1 to input.peek() + 1
        //has to be greater or equal to input[top]
        //so area = input[top]*(i - stack.peek() - 1);
        else {
            area = input[top] * (i - stack.peekFirst() - 1);
        }
        if (area > maxArea) {
            maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        List<List<Integer>> A = new ArrayList<>();
        A.add(Arrays.asList(1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1));
        A.add(Arrays.asList(1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0));
        A.add(Arrays.asList(1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1));
        A.add(Arrays.asList(1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1));
        A.add(Arrays.asList(1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1));
        A.add(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1));
        System.out.println(new MaxRecInBinaryMatrix().maximalRectangle(A));
    }
}
