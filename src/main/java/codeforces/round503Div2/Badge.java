package codeforces.round503Div2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by adarsh.sharma on 11/08/18.
 */
public class Badge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] p = new int[n];
        Set<Integer> rem = new HashSet<>();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt() - 1;
            rem.add(i);
            res[i] = -1;
        }


        while (!rem.isEmpty()) {
            int idx = rem.iterator().next();
            List<Integer> cycle = new ArrayList<>();
            Set<Integer> curSet = new HashSet<>();
            while (!rem.isEmpty()) {
                if(res[idx] == -1) {
                    if(curSet.contains(idx)) {
                        int k = cycle.size() - 1;
                        while(cycle.get(k) != idx) {
                            res[cycle.get(k)] = cycle.get(k) + 1;
                            rem.remove(cycle.get(k));
                            k--;
                        }
                        res[idx] = idx + 1;
                        rem.remove(idx);
                        k--;
                        while(k>=0) {
                            res[cycle.get(k)] = idx + 1;
                            rem.remove(cycle.get(k));
                            k--;
                        }
                        break;

                    } else {
                        curSet.add(idx);
                        cycle.add(idx);
                        idx = p[idx];
                    }
                } else {
                    int k = cycle.size()-1;
                    while(k>=0) {
                        res[cycle.get(k)] = res[idx];
                        rem.remove(cycle.get(k));
                        k--;
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
            if(i>0) {
                sb.append(" ");
            }
            sb.append(res[i]);
        }
        System.out.println(sb);
    }
}
