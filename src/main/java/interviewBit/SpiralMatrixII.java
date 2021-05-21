package interviewBit;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 12/08/18.
 */
public class SpiralMatrixII {
    public ArrayList<ArrayList<Integer>> generateMatrix(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(n==0) {
            return result;
        }

        for(int i=0;i<n;i++) {
            result.add(new ArrayList<>());
            for(int j=0;j<n;j++) {
                result.get(i).add(0);
            }
        }

        int k=1;

        int r1 = 0, r2 = n-1;
        int c1 = 0, c2 = n-1;

        while(r1<=r2 && c1<=c2) {
            for(int c=c1;c<=c2;c++) {
                result.get(r1).set(c, k++);
            }
            for(int r=r1+1;r<=r2;r++) {
                result.get(r).set(c2, k++);
            }
            if(r2>r1) {
                for(int c=c2-1;c>=c1;c--) {
                    result.get(r2).set(c, k++);
                }
            }
            if(c2>c1) {
                for(int r=r2-1;r>r1;r--) {
                    result.get(r).set(c1, k++);
                }
            }

            r1++;r2--;c1++;c2--;
        }


        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SpiralMatrixII().generateMatrix(2));
    }
}
