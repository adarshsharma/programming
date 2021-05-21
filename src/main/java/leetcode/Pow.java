package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 28/07/18.
 */
public class Pow {
    Map<Long, Double> pMap = new HashMap<>();
    public double myPow(double x, int n) {
        if(n<0) {
            double d = 1;
            return d/myPow(x, -n);
        }

        pMap.put(0L, 1.0);
        pMap.put(1L, x);
        for(long i=1;i<32;i++) {
            pMap.put(1L<<i, pMap.get(1L<<(i-1))*pMap.get(1L<<(i-1)));
        }

        double res = 1;

        long i=0;
        while(n > 0) {
            if((n & 1) > 0) {
                res*=pMap.get(1L<<i);
            }
            n = n>>1;
            i++;
        }

        return res;
    }

    public int pow(int x, int n, int d) {

        long rem = 1;
        int check = 0;

        // simple cases
        if(x == 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }

        // make x positive, check if power is odd
        if(x < 0){
            x = Math.abs(x);
            if(n%2 != 0){
                check = 1;
            }
        }

        long temp = x%d;

        while(n != 0){
            if(n%2 != 0){
                rem = (rem*temp)%d;
            }

            temp = temp*temp;
            temp = temp%d;

            n = n/2;
            if(rem > d){
                rem = rem%d;
            }
        }

        // if power is odd and x < 0, return d-rem
        if(check == 1){
            return d-(int)rem;
        }

        return (int)rem;
    }

    public static void main(String[] args) {
        System.out.println(new Pow().pow(2, 7, 1000));
//        System.out.println(new Pow().myPow(2.0, 10));
    }
}
