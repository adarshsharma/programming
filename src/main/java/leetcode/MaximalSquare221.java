package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by adarsh.sharma on 19/07/18.
 */
public class MaximalSquare221 {
    public int maximalSquare(char[][] mt) {
        if (mt.length == 0) return 0;
        int maxRec = 0;
        int m = mt.length;
        int n = mt[0].length;

        int[] h = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    h[j] = (int) mt[i][j] - '0';
                } else {
                    h[j] = mt[i][j] == '0' ? 0 : h[j] + 1;
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
        Deque<Integer> stack = new LinkedList<Integer>();
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
        int w, h;
        int top = stack.pollFirst();
        if (stack.isEmpty()) {
            w = input[top];
            h = i;
        } else {
            w = input[top];
            h = i - stack.peekFirst() - 1;
        }
        int area = Math.min(w, h) * Math.min(w, h);
        if (area > maxArea) {
            maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        char[][] c = {{'1', '0', '1', '0', '0'},
                      {'1', '0', '1', '1', '1'},
                      {'1', '1', '1', '1', '1'},
                      {'1', '0', '0', '1', '0'}};
        System.out.println(new MaximalSquare221().maximalSquare(c));
    }
}
