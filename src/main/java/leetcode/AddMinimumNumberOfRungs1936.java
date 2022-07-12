package leetcode;

public class AddMinimumNumberOfRungs1936 {

    public int addRungs(int[] rungs, int dist) {
        int prev = 0;
        int res = 0;
        for (int i = 0; i < rungs.length; i++) {
            res += ((rungs[i] - prev - 1) / dist);
            prev = rungs[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] rungs = {1, 3, 5, 10};
        int dist = 2;
        System.out.println(new AddMinimumNumberOfRungs1936().addRungs(rungs, dist));
    }

}
