package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 18/07/18.
 */
public class RemoveDuplicates {
    public int removeDuplicates(List<Integer> a) {
        if(a==null) {
            return 0;
        }
        if(a.size()<2) {
            return a.size();
        }

        int len = 1;
        for(int i=1;i<a.size();i++) {
            if(!a.get(i).equals(a.get(len-1))) {
                a.set(len, a.get(i));
                len++;
            }
        }

        for(int i=a.size()-1;i>=len;i--) {
            a.remove(i);
        }

        return a.size();
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3));
        System.out.println(new RemoveDuplicates().removeDuplicates(a));
        System.out.println(a);
    }
}
