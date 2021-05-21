package leetcode;

/**
 * Created by adarsh.sharma on 19/06/18.
 */
public class ContainerWithMostWater11 {
    public int maxArea(int[] height) {
        int maxArea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }



    public static void main(String[] args) {
        ContainerWithMostWater11 c = new ContainerWithMostWater11();
        int[] h = {3, 2, 1, 1, 1, 2, 3, 4, 5};
        System.out.println(c.maxArea(h));
    }
}
