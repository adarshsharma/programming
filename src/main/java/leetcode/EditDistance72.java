package leetcode;

import org.w3c.dom.ls.LSOutput;

public class EditDistance72 {
    public int minDistance(String word1, String word2) {
        int[] pDP = new int[word1.length()+1];

        for(int i=0;i<=word2.length();i++) {
            int[] nDP = new int[word1.length()+1];
            for(int j=0;j<=word1.length();j++) {
                if(i==0) {
                    nDP[j] = j;
                } else if(j==0) {
                    nDP[j] = i;
                } else {
                    nDP[j] = Math.max(i, j);
                    if(word1.charAt(j-1)==word2.charAt(i-1)) {
                        nDP[j] = Math.min(nDP[j], pDP[j-1]);
                    } else {
                        nDP[j] = Math.min(nDP[j], pDP[j-1] + 1);
                        nDP[j] = Math.min(nDP[j], 1+ Math.min(pDP[j], nDP[j-1]));
                    }
                }
            }
            pDP = nDP;
        }

        return pDP[word1.length()];
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance72().minDistance("horse", "ros"));
    }

}
