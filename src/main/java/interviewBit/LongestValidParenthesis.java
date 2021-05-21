package interviewBit;

/**
 * Created by adarsh.sharma on 16/07/18.
 */
public class LongestValidParenthesis {
//    public int longestValidParentheses(String A) {
//        Stack<Integer> stk = new Stack<>();
//        Stack<Integer> lastEndStk = new Stack<>();
//        Stack<Integer> lastLStk = new Stack<>();
//        int max = 0;
//        lastEndStk.push(-1);
//        lastLStk.push(0);
//
//        for (int i = 0; i < A.length(); i++) {
//            char c = A.charAt(i);
//            if (c == '(') {
//                stk.push(i);
//            } else {
//                if (stk.size() > 0 && A.charAt(stk.peek()) == '(') {
//                    int idx = stk.pop();
//                    int vl = i - idx + 1;
//                    if (lastEndStk.peek() + 1 == idx) {
//                        vl = lastLStk.peek() + vl;
//                        lastEndStk.pop();
//                        lastLStk.pop();
//                    } else {
//                        if(lastEndStk.peek() - lastLStk.peek() + 1 > idx) {
//                            lastEndStk.pop();
//                            lastLStk.pop();
//                            if (lastEndStk.peek() + 1 == idx) {
//                                vl = lastLStk.peek() + vl;
//                                lastEndStk.pop();
//                                lastLStk.pop();
//                            }
//                        }
//                    }
//                    max = Math.max(vl, max);
//                    lastEndStk.push(i);
//                    lastLStk.push(vl);
//                }
//            }
//        }
//
//        return max;
//    }

    public int longestValidParentheses(String A) {
        int max = 0;
        int[] longest = new int[A.length()];

        for (int i = 1; i < A.length(); i++) {
            if (A.charAt(i) == ')') {
                if (A.charAt(i - 1) == '(') {
                    longest[i] = 2;
                    if (i - 2 >= 0) {
                        longest[i] += longest[i - 2];
                    }
                } else {
                    if (longest[i - 1] > 0 && (i - longest[i - 1] - 1) >= 0 && A.charAt(i - longest[i - 1] - 1) == '(') {
                        longest[i] = 2 + longest[i - 1];
                        if (i - longest[i - 1] - 2 >= 0) {
                            longest[i] += longest[i - longest[i - 1] - 2];
                        }
                    }
                }
            }
            max = Math.max(max, longest[i]);
        }

        return max;
    }

    public static void main(String[] args) {
//        System.out.println(new LongestValidParenthesis().longestValidParentheses(")()())"));
//        System.out.println(new LongestValidParenthesis().longestValidParentheses("(()())(())((((())))())((()()))"));
        System.out.println(new LongestValidParenthesis().longestValidParentheses(")((()))))))))("));
    }
}
