import java.util.ArrayList;
import java.util.List;

public class Test {

    public static int findPaths(int h, int w) {
        int[][] pathMat = new int[2][w];
        pathMat[0][0] = 1;

        int toggle = 1;
        int count = 1;
        int i = 0;
        int prevToggle = 0;
        while(i < h - 1) {
            // clean up the previous values
            for (int j = 0; j < w; j++) {
                pathMat[toggle][j] = 0;
            }
            // fill up values at toggle using the previous toggle values
            for (int j = 0; j < w; j++) {
                if ((j - 1) > -1) {
                    pathMat[toggle][j - 1] += pathMat[prevToggle][j];
                }
                pathMat[toggle][j] += pathMat[prevToggle][j];
                if (j + 1 < w) {
                    pathMat[toggle][j + 1] += pathMat[prevToggle][j];
                }
            }
            // change the toggle values
            prevToggle = toggle;
            toggle = (toggle+1)%2;
            i++;
        }

        return pathMat[prevToggle][0];

    }

    public static void main(String[] args) {
        System.out.println(Test.findWays(5, 4));
    }

    public static int findWays(int w,int h){
        int[][] col = new int[2][w];
        int current=1;
        col[0][0] = 1;
        for(int j=0;j<h;j++){
            for(int i=0;i<w;i++){
                col[current][i] = 0;
                if(isSafe(i-1,w)){
                    col[current][i] += col[(current+1)%2][i-1];
                }
                col[current][i] += col[(current+1)%2][i];
                if(isSafe(i+1,w)){
                    col[current][i] += col[(current+1)%2][i+1];
                }
            }
            current = (current+1)%2;
        }
        return col[(current+1)%2][0];
    }

    static boolean isSafe(int i,int w){
        return i>=0 && i<w;
    }
}

