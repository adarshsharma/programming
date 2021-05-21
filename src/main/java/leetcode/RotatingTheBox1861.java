package leetcode;

public class RotatingTheBox1861 {

  public static char[][] rotateTheBox(char[][] box) {
    int m = box.length;
    int n = box[0].length;

    char[][] res = new char[n][m];

    for (int r = 0; r < m; r++) {
      int b = n - 1;
      for (int c = n - 1; c >= 0; c--) {
        char ch = box[r][c];
        switch (ch) {
          case '#':
            res[c][m - r - 1] = '.';
            res[b][m - r - 1] = '#';
            b--;
            break;
          case '*':
            res[c][m - r - 1] = '*';
            b = c - 1;
            break;
          case '.':
            res[c][m - r - 1] = '.';
            break;
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    char[][] box = {{'#', '.', '#'}};
    rotateTheBox(box);
  }

}
