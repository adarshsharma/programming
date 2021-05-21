package interviewBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh.sharma on 06/07/18.
 */
public class IntToRoman {
    class Roman {
        int val;
        String c;
        public Roman(int val, String c) {
            this.val = val;
            this.c = c;
        }
    }
    public String intToRoman(int A) {
        List<Roman> rl = new ArrayList<>();
        rl.add(new Roman(1, "I"));
        rl.add(new Roman(4, "IV"));
        rl.add(new Roman(5, "V"));
        rl.add(new Roman(9, "IX"));
        rl.add(new Roman(10, "X"));
        rl.add(new Roman(40, "XL"));
        rl.add(new Roman(50, "L"));
        rl.add(new Roman(90, "XC"));
        rl.add(new Roman(100, "C"));
        rl.add(new Roman(400, "CD"));
        rl.add(new Roman(500, "D"));
        rl.add(new Roman(900, "CM"));
        rl.add(new Roman(1000, "M"));

        String result = "";
        while(A >= 1000) {
            result += "M";
            A-=1000;
        }

        return result + getRoman(rl, A);
    }

    private String getRoman(List<Roman> rl, int A) {
        if(A==0) {
            return "";
        }

        int low = 0;
        int high = rl.size() - 1;

        while(low + 1 < high) {
            int mid = (low+high)/2;
            if(rl.get(mid).val == A) {
                return rl.get(mid).c;
            } else if(rl.get(mid).val > A) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if(A==rl.get(high).val) {
            return rl.get(high).c;
        }

        if(A==rl.get(low).val) {
            return rl.get(low).c;
        }

        String result = "";
        while(A>=rl.get(low).val) {
            result+=rl.get(low).c;
            A-=rl.get(low).val;
        }
        return result + getRoman(rl, A);
    }

    public static void main(String[] args) {
        System.out.println(new IntToRoman().intToRoman(1001));
    }
}
