package interviewBit;

/**
 * Created by adarsh.sharma on 06/07/18.
 */
public class SquareRoot {
    public int sqrt(int a) {
        if(a==0) {
            return 0;
        }
        long floor = 1;
        long low = 1;
        long high = a/2;
        int count=0;
        while(low<=high) {
            long mid = (low+high)/2;
            if(mid*mid==a) {
                return (int) mid;
            } else if(mid*mid<a) {
                floor = mid;
                low = mid+1;
            } else {
                high = mid -1;
            }
            count++;
        }
        System.out.println(count);
        return (int) floor;
    }

    public static void main(String[] args) {
        System.out.println(new SquareRoot().sqrt(586123487));
    }
}
