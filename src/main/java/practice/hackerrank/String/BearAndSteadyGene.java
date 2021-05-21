package practice.hackerrank.String;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by adarsh.sharma on 05/12/17.
 */
public class BearAndSteadyGene {
        private static Integer getBearAndSteadyGene1(int n, String s) {
            Map<Character, Integer> sMap = new HashMap<>();
            sMap.put('G',0);
            sMap.put('A',0);
            sMap.put('C',0);
            sMap.put('T',0);

            for(int i=0;i<s.length();i++){
                sMap.put(s.charAt(i), sMap.get(s.charAt(i)) + 1);
            }
            int r = n/4;
            int extraCount = 0;
            Map<Character, Integer> eMap = new HashMap<>();
            Map<Character, Integer> cMap = new HashMap<>();
            for(Character c: sMap.keySet()) {
                if(sMap.get(c) > r) {
                    int e = sMap.get(c) - r;
                    eMap.put(c, e);
                    extraCount+=e;
                    cMap.put(c, 0);
                }
            }
            //System.out.println(eMap.toString());
            //System.out.println(cMap.toString());

            int ret = s.length();
            LinkedList<Integer> subS = new LinkedList<>();
            for(int i=0; i<s.length(); i++){
                char c = s.charAt(i);
                if(eMap.containsKey(c)) {
                    subS.add(i);
                    //System.out.println(subS.toString());

                    if(cMap.get(c) < eMap.get(c)) {
                        cMap.put(c, cMap.get(c) + 1);
                        extraCount--;
                    } else {
                        cMap.put(c, cMap.get(c) + 1);
                        if(c == s.charAt(subS.getFirst())) {
                            //System.out.println(subS.toString());
                            while(cMap.get(c) > eMap.get(c)) {
                                subS.removeFirst();
                                cMap.put(c, cMap.get(c) - 1);
                                c = s.charAt(subS.getFirst());
                            }
                            //System.out.println(subS.toString());
                        }
                    }

                    if(extraCount == 0) {
                        int len = subS.getLast() - subS.getFirst() + 1;
                        if(ret > len) {
                            ret = len;
                            //System.out.println(ret);
                        }
                    }
                }
            }

            return ret;
        }

        private static Integer getBearAndSteadyGene(int n, String s) {
            Map<Character, Integer> sMap = new HashMap<>();
            sMap.put('G',0);
            sMap.put('A',0);
            sMap.put('C',0);
            sMap.put('T',0);

            for(int i=0;i<s.length();i++){
                sMap.put(s.charAt(i), sMap.get(s.charAt(i)) + 1);
            }
            int r = n/4;

            Map<Character, Integer> eMap = new HashMap<>();
            for(Character c: sMap.keySet()) {
                if(sMap.get(c) > r) {
                    int e = sMap.get(c) - r;
                    eMap.put(c, e);
                }
            }
            //System.out.println(eMap.toString());
            //System.out.println(cMap.toString());

            int ret = s.length();
            int left=0,right=0;
            while(right<s.length()) {
                while(right<s.length() && !valid(eMap)) {
                    char c = s.charAt(right);
                    if(eMap.containsKey(c)) {
                        eMap.put(c, eMap.get(c) -1 );
                    }
                    right++;
                }

                while(left<right && valid(eMap)) {
                    ret = Math.min(ret, right - left);
                    //System.out.println(left + " " + right);
                    char c = s.charAt(left);
                    if(eMap.containsKey(c)) {
                        eMap.put(c, eMap.get(c) + 1);
                    }
                    left++;
                }
            }

            return ret;
        }

        private static boolean valid(Map<Character, Integer> map) {
            for(Integer v: map.values()) {
                if(v > 0)
                    return false;
            }
            return true;
        }

        public static void main(String[] args) {
            int n = 40;
            String s = "TGATGCCGTCCCCTCAACTTGAGTGCTCCTAATGCGTTGC";
            System.out.println(getBearAndSteadyGene1(n, s));
        }
}
