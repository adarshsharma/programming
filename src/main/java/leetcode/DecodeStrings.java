package leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by adarsh.sharma on 01/08/18.
 */
public class DecodeStrings {
    public String decodeString(String s) {
        if(s==null || s.length() == 0) {
            return "";
        }
        Set<String> set = new HashSet<>();

        Deque<String> dq = new LinkedList<>();
        char[] ch = s.toCharArray();
        for(int i=0;i<ch.length;i++) {
            char c = ch[i];
            if((c >= '0' && c<= '9') || c == '[') {
                dq.addFirst(c + "");
            } else if(c == ']') {
                String a = "";
                while(!dq.isEmpty() && !dq.peekFirst().equals("[")) {
                    a = dq.pollFirst() + a;
                }
                dq.removeFirst();

                String num = "";
                while(!dq.isEmpty()) {
                    String peek = dq.peekFirst();
                    char lastC = peek.charAt(peek.length() - 1);
                    if(lastC >= '0' && lastC <= '9') {
                        a = dq.pollFirst() + a;
                    } else {
                        break;
                    }
                }

                int n = Integer.parseInt(num);

                String res = "";
                while(n > 0) {
                    res += a;
                    n--;
                }

                dq.addFirst(res);
            }
        }

        return dq.pollFirst();

    }

    public static void main(String[] args) {
        System.out.println(new DecodeStrings().decodeString("3[a]2[bc]"));
    }
}
