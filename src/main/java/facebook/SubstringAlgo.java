package facebook;

/**
 * Created by adarsh.sharma on 09/07/18.
 */
public class SubstringAlgo {
    public static void main(String[] args) {
        String str = "ABACUS";
//        String str = "FACEBOOK";
//        String str = "XYZXZYX";
//        String str = "FBFBF";

//        String pattern = "ABACUS";
//        String pattern = "XYZXZYX";
//        String pattern = "FACEBOOK";
        String pattern = "FBFBA";
        String abacus = getHaystack(pattern);
        System.out.println(abacus);
        System.out.println(subStringCheck(abacus, pattern));
//        System.out.println(Arrays.asList(computePrefixFunction("XYZXZYX")));
//        System.out.println(getHaystack("XYZXZYX"));
        //FBFBA
        //FBFBFBA
    }

    public static String getHaystack(String pattern) {
        Integer[] lps = computePrefixFunction(pattern);

        int pos = -1;
        int i = 0;
        for (; i < lps.length; i++) {
            if (lps[i] == 0 && pos != -1) {
                break;
            } else if(lps[i] > 0) {
                pos = i;
            }
        }

        if (i == lps.length || pos == -1) {
            return "Impossible";
        } else {
            return pattern.substring(0, pos+1) + pattern.substring(lps[pos]);
        }
    }

    public static boolean subStringCheck(String pattern, String str) {
        int i=1;
        int j=1;
        while(true) {
            if(i > pattern.length()) {
                return true;
            }
            if(j > str.length()) {
                return false;
            }
            if(pattern.charAt(i-1) == str.charAt(j-1)) {
                i++;
                j++;
            } else {
                if(i==1) {
                    j++;
                } else {
                    i=1;
                }
            }
        }
    }

    public static Integer[] computePrefixFunction(String pattern) {
        int length = pattern.length();
        Integer[] lps = new Integer[length];

        int k = 0;
        lps[0] = 0;

        for (int i = 1; i < length; i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = lps[k - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(k)) {
                k++;
            }
            lps[i] = k;
        }

        return lps;
    }
}
