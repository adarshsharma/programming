package leetcode;

import java.util.Arrays;

public class SpiralMatrixIV2326 {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }


  public int[][] spiralMatrix(int m, int n, ListNode head) {
    int[][] matrix = new int[m][n];
    int rs = 0, cs = 0, re = m - 1, ce = n - 1;
    int total = 0;

    while (total < m * n) {
      for (int c = cs, r = rs; c <= ce && total < m * n; c++) {
        head = updateMatrix(head, matrix, r, c);
        total++;
      }

      for (int c = ce, r = rs + 1; r <= re && total < m * n; r++) {
        head = updateMatrix(head, matrix, r, c);
        total++;
      }

      for (int r = re, c = ce - 1; c >= cs && total < m * n; c--) {
        head = updateMatrix(head, matrix, r, c);
        total++;
      }

      for (int r = re - 1, c = cs; r > rs && total < m * n; r--) {
        head = updateMatrix(head, matrix, r, c);
        total++;
      }
      rs++;
      re--;
      cs++;
      ce--;
    }

    return matrix;
  }

  private ListNode updateMatrix(ListNode head, int[][] matrix, int r, int c) {
    int val = -1;
    if (head != null) {
      val = head.val;
      head = head.next;
    }
    matrix[r][c] = val;
    return head;
  }

  public static void main(String[] args) {
    int m = 3, n = 5;

    // [3,0,2,6,8,1,7,9,4,2,5,5,0]

    ListNode head = new ListNode(0);
    head = new ListNode(5, head);
    head = new ListNode(5, head);
    head = new ListNode(2, head);
    head = new ListNode(4, head);
    head = new ListNode(9, head);
    head = new ListNode(7, head);
    head = new ListNode(1, head);
    head = new ListNode(8, head);
    head = new ListNode(6, head);
    head = new ListNode(2, head);
    head = new ListNode(0, head);
    head = new ListNode(3, head);
    int[][] res = new SpiralMatrixIV2326().spiralMatrix(m, n, head);
    Arrays.stream(res).forEach(x -> System.out.println(Arrays.toString(x)));
  }

}
