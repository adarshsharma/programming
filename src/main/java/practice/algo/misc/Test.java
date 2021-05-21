package practice.algo.misc;

/**
 * Created by adarsh.sharma on 11/06/18.
 */
public class Test {
    private static final int REGION_COUNT = 501;

    public static void main(String[] args) {
        int x = "sdfdsfsdfsdfsd".hashCode();
        System.out.println(x);
        System.out.println(String.format("%03d", (x % REGION_COUNT + REGION_COUNT)%REGION_COUNT));
        System.out.println(String.format("%03d", ((x % REGION_COUNT + REGION_COUNT) % REGION_COUNT)));

    }
}


