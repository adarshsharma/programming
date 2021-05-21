package leetcode;

/**
 * Created by adarsh.sharma on 04/09/18.
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if(num==null || num.length() < 3) {
            return false;
        }
        int n = num.length();
        for(int len=1;len<=n/2;len++) {
            String f = num.substring(0, len);
            for(int len2=1;len2<=(n-len)/2;len2++) {
                String second = num.substring(len, len+len2);
                if(second.startsWith("0") && second.length() > 1) {
                    break;
                }
                int start = len+len2;
                String first = f;
                String sum = sum(first, second);
                while(start < n && num.substring(start).startsWith(sum)) {
                    first = second;
                    second = sum;
                    start += sum.length();
                    sum = sum(first, second);
                }
                if(start==n) {
                    return true;
                }
            }
        }
        return false;
    }

    private String sum(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int s = 0;
        int i=a.length()-1,j=b.length()-1;
        while(i>=0 || j>=0) {
            if(i>=0) {
                s+=a.codePointAt(i--) - '0';
            }
            if(j>=0) {
                s+=b.codePointAt(j--) - '0';
            }
            sb.append(s%10);
            s/=10;
        }
        if(s>0) {
            sb.append(s);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new AdditiveNumber().isAdditiveNumber("0011"));
    }
}
