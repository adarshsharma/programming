package practice.hackerrank.String;

import java.util.*;

/**
 * Created by adarsh.sharma on 03/12/17.
 */
public class SherlockAndValidString {
    static String isValid(String s){
        int[] freq = new int[26];

        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            freq[c - 'a'] = freq[c - 'a'] + 1;
        }
        Map<Integer, Integer> fMap = new HashMap<>();

        for(int i=0;i<26;i++){
            if(freq[i] != 0){
                if(!fMap.containsKey(freq[i])){
                    fMap.put(freq[i], 1);
                } else {
                    fMap.put(freq[i], fMap.get(freq[i]) + 1);
                }
            }
        }

        if(fMap.size() <= 1) {
            return "YES";
        } else if(fMap.size() > 2) {
            return "NO";
        } else {
            List<Integer> fList = new ArrayList<>(fMap.keySet());
            Integer f1 = fList.get(0);
            Integer f2 = fList.get(1);

            if(fMap.get(f1) == 1) {
                if(f1 == 1 || f1 == f2+1){
                    return "YES";
                } else {
                    return "NO";
                }
            } else if (fMap.get(f2) == 1) {
                if(f2 == 1 || f2 == f1+1){
                    return "YES";
                } else {
                    return "NO";
                }
            } else {
                return "NO";
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = isValid(s);
        System.out.println(result);
    }
}
