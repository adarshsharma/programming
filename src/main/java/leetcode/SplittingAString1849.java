package leetcode;

public class SplittingAString1849 {

  public boolean splitString(String s) {
    String str = trim(s);
    for (int l = 1; l <= (str.length() + 1) / 2; l++) {
      Long first = Long.parseLong(str.substring(0, l));
      String rem = str.substring(l);
      int count = 1;

      while (rem.length() != 0 && first >= 0) {
        first--;
        String next = first.toString();
        String nextRem = first == 0 ? rem : trim(rem);
        if (nextRem.startsWith(next)) {
          count++;
          rem = nextRem.substring(next.length());
          if (first == 0) {
            rem = trim(nextRem);
          }
        } else {
          break;
        }
      }
      if (rem.length() == 0 && count > 1) {
        return true;
      }

    }
    return false;
  }

  private String trim(String in) {
    int i = 0;
    while (i < in.length()) {
      if (in.charAt(i) == '0') {
        i++;
      } else {
        return in.substring(i);
      }
    }

    return "";
  }

  public static void main(String[] args) {
    SplittingAString1849 obj = new SplittingAString1849();
    System.out.println(obj.splitString("200100"));
  }

}
