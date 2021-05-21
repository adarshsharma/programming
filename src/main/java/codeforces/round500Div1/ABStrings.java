package codeforces.round500Div1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 30/07/18.
 */
public class ABStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        List<List<Integer>> ops = getCount(a.toCharArray(), 0, b.toCharArray(), 0);
        System.out.println(ops.size());
        for (int i = 0; i < ops.size(); i++) {
            System.out.println(ops.get(i).get(0) + " " + ops.get(i).get(1));
        }
    }

    private static List<List<Integer>> getCount(char[] a, int s1, char[] b, int s2) {
        List<List<Integer>> ops = new ArrayList<>();
        int l1 = getFirstCharLength(a, s1);
        int l2 = getFirstCharLength(b, s2);
        char f1 = a[s1];
        char f2 = b[s2];

        if (l1 == a.length - s1 && l2 == b.length - s2) {
            if (f1 == f2) {
                add(ops, l1, l2);
                return ops;
            } else {
                return ops;
            }
        } else if ((l1 == a.length - s1 || l2 == b.length - s2) && f1 == f2) {
            if (l1 == a.length) {
                add(ops, 0, l2);
                int first = l1 + l2 - 1;
                int second = 0;
                List<List<Integer>> ops2 = getCount(a, s1 + l1 - 1, b, s2 + l2);
                update(ops, first, second, ops2);
            } else {
                add(ops, l1, 0);
                int first = 0;
                int second = l1 + l2 - 1;
                List<List<Integer>> ops2 = getCount(a, s1 + l1, b, s2 + l2 - 1);
                update(ops, first, second, ops2);
            }
        } else {
            if (f1 != f2) {
                add(ops, l1, l2);
                int first = l2 - 1;
                int second = l1 - 1;

                a[s1 + l1 - 1] = f2;
                b[s2 + l2 - 1] = f1;

                List<List<Integer>> ops2 = getCount(a, s1 + l1 - 1, b, s2 + l2 - 1);
                update(ops, first, second, ops2);
            } else {
                add(ops, l1, 0);
                int second = l1 + l2 - 1;
                int first = 0;

                List<List<Integer>> ops2 = getCount(a, s1 + l1, b, s2 + l2 - 1);
                update(ops, first, second, ops2);
            }
        }

        return ops;
    }

    private static void update(List<List<Integer>> ops, int first, int second, List<List<Integer>> ops2) {
        for (List<Integer> op : ops2) {
            op.set(0, op.get(0) + first);
            op.set(1, op.get(1) + second);
        }
        ops.addAll(ops2);
    }

    private static void add(List<List<Integer>> ops, int l1, int l2) {
        List<Integer> lst = new ArrayList<>();
        lst.add(l1);
        lst.add(l2);
        ops.add(lst);
    }

    private static int getFirstCharLength(char[] a, int s) {
        if (s >= a.length) {
            return 0;
        }
        int i = 0;
        int c = a[s];
        while ((i + s) < a.length && a[i + s] == c) {
            i++;
        }
        return i;
    }
}
