package practice.algo.misc;

/**
 * Created by adarsh.sharma on 03/06/18.
 */
public class TowerOfHanoi {
    public static void main(String[] args) {
        toh(3, 'A', 'B', 'C');
    }

    private static void toh(int n, char s, char t, char a) {
        if (n == 1) {
            System.out.println("1 " +  s + " -> " + t);
            return;
        }

        toh(n-1, s, a, t);
        System.out.println(n + " " + s + " -> " + t);
        toh(n-1, a, t, s);
    }
}
