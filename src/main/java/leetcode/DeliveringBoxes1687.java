package leetcode;

public class DeliveringBoxes1687 {

  public static int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
    int trips = 0;
    // List<Integer> ports = new ArrayList<>();
    // int boxCount = 0;
    // int weight = 0;
    //
    // for (int[] box : boxes) {
    //   if (boxCount == maxBoxes || weight + box[1] > maxWeight) {
    //     trips += (ports.size() + 1);
    //     ports = new ArrayList<>();
    //     boxCount = 0;
    //     weight = 0;
    //   }
    //
    //   if (ports.size() == 0 || ports.get(ports.size() - 1) != box[0]) {
    //     ports.add(box[0]);
    //   }
    //   boxCount++;
    //   weight += box[1];
    // }
    //
    // if (ports.size() > 0) {
    //   trips += (ports.size() + 1);
    // }
    //
    return trips;
  }

  public static void main(String[] args) {
    int[][] boxes = {{2, 4}, {2, 5}, {3, 1}, {3, 2}, {3, 7}, {3, 1}, {4, 4}, {1, 3}, {5, 2}};

    System.out.println(boxDelivering(boxes, 5, 5, 7));
  }

}
