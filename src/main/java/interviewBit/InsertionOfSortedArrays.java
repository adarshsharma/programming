package interviewBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh.sharma on 06/07/18.
 */
public class InsertionOfSortedArrays {
    public ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> result = new ArrayList<>();
        int i=0;
        int j=0;

        while(i<A.size() && j<B.size()) {
            if(A.get(i).equals(B.get(j))) {
                result.add(A.get(i));
                i++;
                j++;
            } else if(A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}
