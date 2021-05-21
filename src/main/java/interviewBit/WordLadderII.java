package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 21/07/18.
 */
public class WordLadderII {
    boolean[][] w;
    int n;
    public ArrayList<ArrayList<String>> findLadders(String start, String end,
                                                    ArrayList<String> dict) {
        // dict.add(start);
        // dict.add(end);
        Set<String> words = new HashSet<>();
        words.addAll(dict);
        dict = new ArrayList<>();
        dict.addAll(words);

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if(start.equals(end)) {
            ArrayList<String> lst = new ArrayList<>();
            lst.add(start);
            res.add(lst);
            return res;
        }
        n = dict.size();
        w = new boolean[n][n];
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(diffByOne(dict.get(i), dict.get(j))) {
                    w[i][j] = true;
                    w[j][i] = true;
                }
            }
        }

        int s = -1;
        int e = -1;
        for(int i=0;i<n && (s==-1 || e==-1);i++) {
            if(start.equals(dict.get(i))) {
                s = i;
            } else if(end.equals(dict.get(i))) {
                e = i;
            }
        }

        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Integer>> prev = new ArrayList<>();
        for(int i=0;i<n;i++) {
            prev.add(new ArrayList<>());
        }
        int[] dist = new int[n];
        Deque<Integer> dq = new LinkedList<>();
        visited[s] = true;
        dist[s] = 0;
        dq.addLast(s);

        while(!dq.isEmpty()) {
            int i = dq.pollFirst();
            if(visited[e] && dist[i] >= dist[e]) {
                break;
            }
            int d = dist[i];
            for(int j=0;j<n;j++) {
                if(w[i][j]) {
                    if(!visited[j]) {
                        dist[j] = d+1;
                        prev.get(j).add(i);
                        visited[j] = true;
                        dq.addLast(j);
                    } else {
                        if(dist[j] > d) {
                            prev.get(j).add(i);
                        }
                    }
                }
            }
        }

        traverse(res, e, s, dict, new ArrayList<>(), prev);
        return res;
    }

    private void traverse(ArrayList<ArrayList<String>> res, int e, int s,
                          ArrayList<String> dict, ArrayList<String> r,
                          ArrayList<ArrayList<Integer>> prev) {
        r.add(0, dict.get(e));
        if(e != s) {
            ArrayList<Integer> p = prev.get(e);
            for(int i=0;i<p.size();i++) {
                traverse(res, p.get(i), s, dict, r, prev);

            }
        } else {
            ArrayList<String> lst = new ArrayList<>();
            lst.addAll(r);
            res.add(lst);
        }
        r.remove(0);
    }

    private boolean diffByOne(String one, String two) {
        int count = 0;
        for(int i=0;i<one.length() && count < 2;i++) {
            if(one.charAt(i) != two.charAt(i)) {
                count++;
            }
        }

        return count==1;
    }

    public static void main(String[] args) {
        ArrayList<String> dict = new ArrayList<>();
        dict.addAll(Arrays.asList("baba", "abba", "aaba", "bbbb", "abaa", "abab", "aaab", "abba", "abba", "abba", "bbba", "aaab", "abaa", "baba", "baaa",
                "bbaa", "babb"));
        System.out.println(new WordLadderII().findLadders(dict.get(dict.size() - 2), dict.get(dict.size() - 1), dict));
    }
}
