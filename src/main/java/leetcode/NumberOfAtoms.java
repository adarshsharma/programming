package leetcode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by adarsh.sharma on 10/09/18.
 */
public class NumberOfAtoms {
//    public String countOfAtoms(String formula) {
//        Stack<Map<String, Integer>> mpStack = new Stack<>();
//        mpStack.push(new HashMap<>());
//        Stack<Integer> mulStack = new Stack<>();
//        boolean lastNumber = false;
//
//        for(int i=formula.length()-1;i>=0;) {
//            if(formula.charAt(i) == '(') {
//                int mul = mulStack.pop();
//                i--;
//                Map<String, Integer> tMap = mpStack.pop();
//                tMap.forEach((k, v) -> tMap.put(k, v*mul));
//                if(mpStack.isEmpty()) {
//                    mpStack.push(tMap);
//                } else {
//                    Map<String, Integer> mp = mpStack.peek();
//                    tMap.forEach((key, value) -> mp.merge(key, value, Integer::sum));
//                }
//                lastNumber = false;
//            } else if(formula.charAt(i) == ')') {
//                i--;
//                if(lastNumber) {
//                    mpStack.push(new HashMap<>());
//                    lastNumber = false;
//                }
//            } else {
//                String s = getNext(formula, i);
//                if(isNumber(s)) {
//                    mulStack.push(Integer.parseInt(s));
//                    lastNumber = true;
//                } else {
//                    Map<String, Integer> mp = mpStack.peek();
//                    if(lastNumber) {
//                        mp.merge(s, mulStack.pop(), Integer::sum);
//                        lastNumber = false;
//                    } else {
//                        mp.merge(s, 1, Integer::sum);
//                    }
//                }
//
//                i-=s.length();
//            }
//        }
//
//        if(mpStack.size() != 1) {
//            throw new IllegalStateException();
//        }
//
//        StringBuilder sb = new StringBuilder();
//        Map<String, Integer> mp = mpStack.pop();
//        mp.keySet().stream().sorted().forEach(k -> {
//            sb.append(k);
//            if(mp.get(k) > 1) {
//                sb.append(mp.get(k));
//            }
//        });
//
//        return sb.toString();
//    }
//
//    private String getNext(String s, int i) {
//        int end = i;
//        char c = s.charAt(i);
//        boolean isNumber = c >= '0' && c<='9';
//        if(!isNumber && c >= 'A' && c <= 'Z') {
//            return c + "";
//        }
//        i--;
//        while(i >= 0) {
//            c = s.charAt(i);
//            if(isNumber) {
//                if(c >= '0' && c<= '9') {
//                    i--;
//                } else {
//                    break;
//                }
//            } else {
//                if(c=='(' || c==')' || (c >= '0' && c<= '9')) {
//                    break;
//                } else {
//                    i--;
//                    if(c >= 'A' && c<= 'Z') {
//                        break;
//                    }
//                }
//            }
//        }
//        return s.substring(i+1, end+1);
//    }
//
//    private boolean isNumber(String s) {
//        for(int i=0;i<s.length();i++) {
//            if(s.charAt(i) < '0' || s.charAt(i) > '9') {
//                return false;
//            }
//        }
//        return true;
//    }

    public String countOfAtomsNew(String formula) {
        Stack<Map<String, Integer>> mpStack = new Stack<>();
        mpStack.push(new HashMap<>());
        Stack<Integer> mulStack = new Stack<>();
        boolean lastNumber = false;
        Pattern pattern = Pattern.compile("([A-Z][a-z]*)|(\\()|(\\))|(\\d+)");
        Matcher matcher = pattern.matcher(formula);
        List<String> parts = new ArrayList<>();
        while(matcher.find()) {
            parts.add(matcher.group());
        }
//        int end = formula.length();
        for(int i=parts.size()-1;i>=0;i--) {
            String s = parts.get(i);
            if(s.startsWith("(")) {
                int mul = mulStack.pop();
                Map<String, Integer> tMap = mpStack.pop();
                tMap.forEach((k, v) -> tMap.put(k, v*mul));
                if(mpStack.isEmpty()) {
                    mpStack.push(tMap);
                } else {
                    Map<String, Integer> mp = mpStack.peek();
                    tMap.forEach((key, value) -> mp.merge(key, value, Integer::sum));
                }
                lastNumber = false;
            } else if(s.startsWith(")")) {
                if(lastNumber) {
                    mpStack.push(new HashMap<>());
                    lastNumber = false;
                }
            } else {
                if(Character.isDigit(s.charAt(0))) {
                    mulStack.push(Integer.parseInt(s));
                    lastNumber = true;
                } else {
                    Map<String, Integer> mp = mpStack.peek();
                    if(lastNumber) {
                        mp.merge(s, mulStack.pop(), Integer::sum);
                        lastNumber = false;
                    } else {
                        mp.merge(s, 1, Integer::sum);
                    }
                }
            }
        }

        if(mpStack.size() != 1) {
            throw new IllegalStateException();
        }

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> mp = mpStack.pop();
        mp.keySet().stream().sorted().forEach(k -> {
            sb.append(k);
            if(mp.get(k) > 1) {
                sb.append(mp.get(k));
            }
        });

        return sb.toString();
    }

    public String countOfAtoms(String formula) {
        if (formula == null || formula.length() == 0) return "";
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> curMap = new HashMap<>();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < formula.length()) {
            char c = formula.charAt(index);
            if (c >= 'A' && c <= 'Z') {
                sb.delete(0, sb.length());
                ++index;
                sb.append(c);
                while (index < formula.length() && formula.charAt(index) >= 'a' && formula.charAt(index) <= 'z') {
                    sb.append(formula.charAt(index++));
                }
                int[] cnt = getInteger(formula, index);
                index = cnt[1];
                String str = sb.toString();
                curMap.put(str, curMap.getOrDefault(str, 0) + cnt[0]);
            } else if (c == '(') {
                stack.push(curMap);
                curMap = new HashMap<>();
                ++index;
            } else if (c == ')') {
                ++index;
                int[] cnt = getInteger(formula, index);
                index = cnt[1];
                Map<String, Integer> popped = stack.pop();
                for (Map.Entry<String, Integer> entry : curMap.entrySet()) {
                    popped.put(entry.getKey(), popped.getOrDefault(entry.getKey(), 0) + entry.getValue() * cnt[0]);
                }
                curMap = popped;
            }
        }
        sb.delete(0, sb.length());
        List<String> keyList = new ArrayList<>(curMap.keySet());
        Collections.sort(keyList);
        for (String key : keyList) {
            sb.append(key);
            if (curMap.get(key) > 1) sb.append(curMap.get(key));
        }
        return sb.toString();
    }

    // return {count, newIndex}
    private int[] getInteger(String formula, int index) {
        int[] res = new int[]{1, index};
        int val = 0;
        while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
            val = val * 10 + formula.charAt(index++) - '0';
        }
        if (val > 0) res[0] = val;
        res[1] = index;
        return res;
    }

    public static void main(String[] args) {
        NumberOfAtoms numberOfAtoms = new NumberOfAtoms();
//        System.out.println(numberOfAtoms.countOfAtoms("H2O"));
//        System.out.println(numberOfAtoms.countOfAtoms("Mg(OH)2"));
        System.out.println(numberOfAtoms.countOfAtoms("K4(ON(SO3)2)2"));
    }
}
