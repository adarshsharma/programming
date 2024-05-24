package interview;

public class FibbonaciTree {

  int[] fib;

  public String findPath(int order, int start, int end) {
    if (start == end) {
      return "";
    }

    fib = new int[order + 1];
    fib[1] = 1;
    for (int i = 2; i <= order; i++) {
      fib[i] = fib[i - 2] + fib[i - 1] + 1;
    }

    String leftPath = findPath(order, start);
    String rightPath = findPath(order, end);

    int i = 0;
    while (i < leftPath.length() && i < rightPath.length()
        && leftPath.charAt(i) == rightPath.charAt(i)) {
      i++;
    }

    StringBuilder sb = new StringBuilder();
    int j = i;
    while (j < leftPath.length()) {
      sb.append("U");
      j++;
    }
    sb.append(rightPath.substring(i));

    return sb.toString();

  }

  private String findPath(int order, int n) {
    if (n == 0 || order < 2) {
      return "";
    }
    if (n <= fib[order - 2]) {
      return "L" + findPath(order - 2, n - 1);
    } else {
      return "R" + findPath(order - 1, n - fib[order - 2] - 1);
    }
  }


  public static void main(String[] args) {
    FibbonaciTree s = new FibbonaciTree();
    System.out.println(s.findPath(5, 5, 7));
  }


}
