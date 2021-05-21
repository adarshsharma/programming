package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 16/07/18.
 */
public class ShortestCommonSuperString {
    //    Map<Integer, String> mp = new HashMap<>();
//
//    public int solve(List<String> A) {
//        mp.put(0, "");
//        String longest = getLongest(A, (1 << A.size()) - 1);
//        return longest.length();
//    }
//
//    private String getLongest(List<String> A, int a) {
//        if (mp.containsKey(a)) {
//            return mp.get(a);
//        }
//        List<Integer> idxs = getSetBitIndexes(a);
//
//        String min = null;
//        int minLength = Integer.MAX_VALUE;
//        for (Integer i : idxs) {
//            String longest = getLongest(A, unset(a, i));
//            String s = getLongest(A.get(i), longest);
//            if (min == null || s.length() < minLength) {
//                min = s;
//                minLength = s.length();
//            }
//        }
//        mp.put(a, min);
//        return min;
//    }
//
//    private List<Integer> getSetBitIndexes(int a) {
//        List<Integer> res = new ArrayList<>();
//        int i = 0;
//        while (a > 0) {
//            if ((a & 1) > 0) {
//                res.add(i);
//            }
//            a = a >> 1;
//            i++;
//        }
//        return res;
//    }
//
//    private int unset(int a, int i) {
//        return a & ~(1 << i);
//    }
//
//    private String findOverlap(String a, String b) {
//        Set<String> res = new HashSet<>();
//        if (b.length() > a.length()) {
//            return findOverlap(b, a);
//        }
//
//        if (b.length() == 0 || a.contains(b)) {
//            return a;
//        }
//
//        for (int i = 1; i < a.length(); i++) {
//            if (b.startsWith(a.substring(i))) {
//                return a.substring(0, i) + b;
//            }
//
//            if (b.endsWith(a.substring(0, a.length() - i))) {
//                return b + a.substring(a.length() - i);
//            }
//        }
//
//        return a + b;
//    }

//    public int solve(List<String> listStr) {
//        if (listStr == null) return 0;
//
//        int n = listStr.size();
//        if (n == 0) return 0;
//
//        String[] arrStr = listStr.toArray(new String[n]);
//
//        int len = n;
//        while (len > 1) {
//            int overlapMax = 0;
//            int I = 0, J = 0;
//            String resStr = "";
//            for (int i = 0; i < len; i++) {
//                for (int j = i + 1; j < len; j++) {
//                    String curStr = findOverlap(arrStr[i], arrStr[j]);
//                    int overlapCur = -curStr.length() + arrStr[i].length() + arrStr[j].length();
//                    //System.out.println(i + " " + j + " " + curStr);
//                    if (overlapMax < overlapCur) {
//                        overlapMax = overlapCur;
//                        resStr = curStr;
//                        I = i;
//                        J = j;
//                    }
//                }
//            }
//            --len;
//            if (overlapMax == 0) {
//                arrStr[0] += arrStr[len];
//            } else {
//                arrStr[I] = resStr;
//                arrStr[J] = arrStr[len];
//            }
//        }
//        return arrStr[0].length();
//    }
//
//    private String findOverlap(String a, String b) {
//        if (a.length() < b.length()) {
//            return findOverlap(b, a);
//        }
//        if (a.contains(b)) {
//            return a;
//        }
//        int la = a.length();
//        int lb = b.length();
//        String res = a + b;
//        for (int k = 1; k <= la; k++) {
//            if (k > lb) break;
//            if (b.endsWith(a.substring(0, k))) {
//                res = b + a.substring(k);
//            }
//        }
//        for (int k = 1; k <= lb; k++) {
//            if (k > la) break;
//            if (a.endsWith(b.substring(0, k))) {
//                String tmpRes = a + b.substring(k);
//                if (res.length() > tmpRes.length())
//                    res = tmpRes;
//            }
//        }
//        return res;
//    }

    Map<Integer, String> mp = new HashMap<>();
    Map<String, Map<String, String>> pmap = new HashMap<>();

    public int solve(ArrayList<String> A) {
        mp.put(0, "");
        String longest = getLongest(A, (1 << A.size()) - 1);
        return longest.length();
    }

    private String getLongest(List<String> A, int a) {
        if (mp.containsKey(a)) {
            return mp.get(a);
        }
        List<Integer> idxs = getSetBitIndexes(a);

        String min = null;
        int minLength = Integer.MAX_VALUE;
        for (Integer i : idxs) {
            String longest = getLongest(A, unset(a, i));
            String s = getLongest(A.get(i), longest);
            if (min == null || s.length() < minLength) {
                min = s;
                minLength = s.length();
            }
        }
        mp.put(a, min);
        return min;
    }

    private List<Integer> getSetBitIndexes(int a) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (a > 0) {
            if ((a & 1) > 0) {
                res.add(i);
            }
            a = a >> 1;
            i++;
        }
        return res;
    }

    private int unset(int a, int i) {
        return a & ~(1 << i);
    }

    private String getLongest(String a, String b) {
        if (b.length() > a.length()) {
            return getLongest(b, a);
        }

        if (!pmap.computeIfAbsent(a, k -> new HashMap<>()).containsKey(b)) {
            pmap.get(a).put(b, a + b);
            if (b.length() == 0 || b.contains(a)) {
                pmap.get(a).put(b, a);
            } else {
                for (int i = 1; i < a.length(); i++) {
                    if (b.startsWith(a.substring(i))) {
                        pmap.get(a).put(b, a.substring(0, i) + b);
                        break;
                    }

                    if (b.endsWith(a.substring(0, a.length() - i))) {
                        pmap.get(a).put(b, b + a.substring(a.length() - i));
                        break;
                    }
                }
            }
        }

        return pmap.get(a).get(b);
    }

    public static void main(String[] args) {
//        List<String> A = Arrays.asList("abcd", "cdef", "fgh", "de");
//        List<String> A = Arrays.asList("cdef", "cd", "ab");
        List<String> A = Arrays.asList("nejqokaplfbrqe", "cjqpuidbwnbaml", "naiwqahtpubspt", "jvidmdlrhjhkjt", "pjvyhpbqlsm", "lcgkneuqsydk", "mruvnqlapmjhp", "sioft", "nehtaxnb", "xlpsgn");
        ArrayList<String> a = new ArrayList<>();
        a.addAll(A);
        System.out.println(new ShortestCommonSuperString().solve(a));
    }
}
