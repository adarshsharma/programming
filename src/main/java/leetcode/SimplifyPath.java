package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by adarsh.sharma on 07/08/18.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        path = path.replaceAll("//+","/");
        String[] strs = path.split("/");
        Deque<String> stack = new ArrayDeque<>();

        for(String str: strs) {
            if(str.length() > 0) {
                switch (str) {
                    case ".":
                        break;
                    case "..":
                        if(!stack.isEmpty()) {
                            stack.removeFirst();
                        }
                        break;
                    default:
                        stack.addFirst(str);
                        break;
                }
            }
        }

        String res = "";

        if(stack.isEmpty()) {
            return "/";
        }

        while(!stack.isEmpty()) {
            res = "/" + stack.removeFirst() + res;
        }

        return res;
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/abc/..."));
    }
}
