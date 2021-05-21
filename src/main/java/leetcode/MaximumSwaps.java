package leetcode;

/**
 * Created by adarsh.sharma on 09/08/18.
 */
public class MaximumSwaps {
    public int maximumSwap(int num) {
        int d = (int) (Math.log10(num) + 1);
        int[] digit = new int[d];
        int temp = num;
        while(d>0) {
            digit[d-1] = temp%10;
            temp = temp/10;
            d--;
        }

        int i=1;
        int big = -1;
        d = (int) (Math.log10(num) + 1);
        while(i<d) {
            if(digit[i] > digit[i-1]) {
                big = i;
                break;
            }
            i++;
        }

        if(big == -1) {
            return num;
        } else {
            int j=big-1;
            while(j>0 && digit[j-1] < digit[big]) {
                j--;
            }
            int diff = digit[big] - digit[j];
            num = num - diff*new Double(Math.pow(10, d-big-1)).intValue();
            num = num + diff*new Double(Math.pow(10, d-j-1)).intValue();
            return num;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSwaps().maximumSwap(2736));
    }
}
