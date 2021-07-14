package leetcode;

import java.util.Arrays;

public class EliminateMaxMonsters1921 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] times = new int[dist.length];
        for(int i=0;i<dist.length;i++) {
            times[i] = dist[i]%speed[i]==0 ? dist[i]/speed[i] : (dist[i]/speed[i])+1;
        }

        Arrays.sort(times);

        int i = 0;
        for(;i<times.length;i++) {
            if(times[i] <= i) {
                break;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] dist = {};
        int[] speed = {};
        System.out.println(new EliminateMaxMonsters1921().eliminateMaximum(dist, speed));
    }

}
