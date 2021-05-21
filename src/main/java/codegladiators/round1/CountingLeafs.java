package codegladiators.round1;

import java.util.*;

/**
 * Created by adarsh.sharma on 04/04/18.
 */
public class CountingLeafs {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(new FileInputStream("/Users/adarsh.sharma/code/test/src/main/resources/countingleafs.in"));

        Map<Integer, List<Integer>> parentChildMap = new HashMap<>();

        int n = in.nextInt();

        for(int i=0;i<n;i++) {
            int p = in.nextInt();
            List<Integer> children = parentChildMap.computeIfAbsent(p, k -> new ArrayList<>());
            children.add(i);
        }
//        System.out.println(parentChildMap);

        int d = in.nextInt();
        Set<Integer> nodesToRemove = new HashSet<>();
        nodesToRemove.add(d);


        while(!nodesToRemove.isEmpty()) {
            Integer next = nodesToRemove.iterator().next();
            List<Integer> children = parentChildMap.get(next);
            nodesToRemove.remove(next);
            parentChildMap.remove(next);
            n--;
            if(children != null && !children.isEmpty()) {
                nodesToRemove.addAll(children);
            }
        }

//        System.out.println(parentChildMap);
        System.out.println(n-(parentChildMap.size()-1));

    }
}
