package practice;

public class TestInterview {

  static void moveAllWordsToRight(char[] arr) {
// Ad: This method could have been simplified as it has a lot of if/else conditions which are not really required.
    int spacePointer = arr.length - 1;
    int wordPointer = arr.length - 1;
    boolean wordStartflag = false;
    while (spacePointer >= 0) {

      while (spacePointer >= 0) {

        if (arr[spacePointer] != ' ' && !wordStartflag) {
          wordPointer = spacePointer;
          spacePointer--;
          wordStartflag = true;
        }

        if (arr[spacePointer] != ' ' && wordStartflag) {
          spacePointer--;
        }

        if (arr[spacePointer] == ' ' && !wordStartflag) {
          break;
        }
        if (arr[spacePointer] == ' ' && wordStartflag) {
          spacePointer--;
          wordStartflag = false;

        }
      }
      wordPointer = spacePointer;

      while (wordPointer >= 0 && arr[wordPointer] == ' ') {
        wordPointer--;
      }

      while (wordPointer >= 0 && arr[wordPointer] != ' ') {
// Ad: Since we are replacing existing characters with space, we can simply do that instead of using a temp variable
        char temp = arr[wordPointer];
        arr[wordPointer] = arr[spacePointer];
        arr[spacePointer] = temp;

        wordPointer--;
        spacePointer--;

      }
    }

  }

  public static void main(String[] args) {
    char[] a = "  DC  BA  ".toCharArray();
    moveAllWordsToRight(a);
    System.out.println(a);
  }

}
