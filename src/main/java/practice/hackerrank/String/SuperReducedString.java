package practice.hackerrank.String;

/**
 * Created by adarsh.sharma on 30/11/17.
 */
public class SuperReducedString {
    static String super_reduced_string(String s){
        int i = 0;
        while(i < s.length() - 1) {
            if(s.charAt(i) == s.charAt(i+1)) {
                String newS = s.substring(0,i);
                if(i+2 < s.length()) {
                    s = newS + s.substring(i+2);
                } else {
                    s = newS;
                }
                i = Math.max(0, i - 1);
            } else {
                i++;
            }
        }

        if(s.length() > 0) {
            return s;
        } else {
            return "Empty String";
        }
    }

    public static void main(String[] args) {
        String result = super_reduced_string("aaabccddd");
        System.out.println(result);
    }
}
