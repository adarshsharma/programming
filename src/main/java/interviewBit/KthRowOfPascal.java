package interviewBit;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 02/07/18.
 */
public class KthRowOfPascal {
    public ArrayList<Integer> getRow(int A) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        if(A==1) {
            return arr;
        }

        for(int a=2;a<=A;a++){
            int i = arr.size()-1;
            if(a%2==0) {
                arr.add(2*arr.get(arr.size()-1));
                i=arr.size()-2;
            }
            for(;i>0;i--) {
                arr.set(i, arr.get(i) + arr.get(i-1));
            }
        }
        int i=arr.size()-1;
        if(A%2==0) {
            i--;
        }
        for(;i>=0;i--) {
            arr.add(arr.get(i));
        }

        return arr;
    }

    public static void main(String[] args) {
        System.out.println(new KthRowOfPascal().getRow(4));
    }
}
