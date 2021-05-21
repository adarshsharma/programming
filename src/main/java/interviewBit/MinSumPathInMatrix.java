package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 23/07/18.
 */
public class MinSumPathInMatrix {
    public class Cell implements Comparable<Cell> {
        int idx;
        int val;
        int prev;

        public Cell(int idx) {
            this.idx = idx;
            this.val = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Cell other) {
            return this.val - other.val;
        }
    }

    public int minPathSum(List<List<Integer>> A) {
        if (A == null) {
            return -1;
        }

        int m = A.size();
        int n = A.get(0).size();

        // MaxHeap maxHeap = new MaxHeap(A, m , n);
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        Cell cell = new Cell(0);
        cell.val = A.get(0).get(0);
        cell.prev = -1;
        pq.add(cell);
        Map<Integer, Cell> cellMap = new HashMap<>();
        cellMap.put(0, cell);
        boolean[] set = new boolean[m * n];

        while (!pq.isEmpty() && !set[m * n - 1]) {
            cell = pq.poll();
            set[cell.idx] = true;
            int i = cell.idx / n;
            int j = cell.idx - i * n;
            if (i > 0 && !set[(i - 1) * n + j]) {
                Cell nc = cellMap.get((i - 1) * n + j);
                if (nc == null) {
                    nc = new Cell((i - 1) * n + j);
                    cellMap.put((i - 1) * n + j, nc);
                } else {
                    pq.remove(nc);
                }
                if (nc.val > A.get(i - 1).get(j) + cell.val) {
                    nc.val = A.get(i - 1).get(j) + cell.val;
                    nc.prev = cell.idx;
                }
                pq.add(nc);
            }
            if (i < m - 1 && !set[(i + 1) * n + j]) {
                Cell nc = cellMap.get((i + 1) * n + j);
                if (nc == null) {
                    nc = new Cell((i + 1) * n + j);
                    cellMap.put((i + 1) * n + j, nc);

                } else {
                    pq.remove(nc);
                }
                if (nc.val > A.get(i + 1).get(j) + cell.val) {
                    nc.val = A.get(i + 1).get(j) + cell.val;
                    nc.prev = cell.idx;
                }
                pq.add(nc);
            }
            if (j > 0 && !set[i * n + j - 1]) {
                Cell nc = cellMap.get(i * n + j - 1);
                if (nc == null) {
                    nc = new Cell(i * n + j - 1);
                    cellMap.put(i * n + j - 1, nc);
                } else {
                    pq.remove(nc);
                }
                if (nc.val > A.get(i).get(j - 1) + cell.val) {
                    nc.val = A.get(i).get(j - 1) + cell.val;
                    nc.prev = cell.idx;
                }
                pq.add(nc);
            }
            if (j < n - 1 && !set[i * n + j + 1]) {
                Cell nc = cellMap.get(i * n + j + 1);
                if (nc == null) {
                    nc = new Cell(i * n + j + 1);
                    cellMap.put(i * n + j + 1, nc);
                } else {
                    pq.remove(nc);
                }
                if (nc.val > A.get(i).get(j + 1) + cell.val) {
                    nc.val = A.get(i).get(j + 1) + cell.val;
                    nc.prev = cell.idx;
                }
                pq.add(nc);
            }
        }
        int idx = m * n - 1;
        while (cellMap.get(idx).prev != -1) {
            int i = idx/n;
            int j = idx - i*n;
            System.out.println("[" + i + "," + j + "]");
            idx = cellMap.get(idx).prev;
        }
        return cellMap.get(m * n - 1).val;
    }

    public static void main(String[] args) {
        List<List<Integer>> A = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(20, 29, 84, 4, 32, 60, 86, 8, 7, 37),
                        Arrays.asList(77, 69, 85, 83, 81, 78, 22, 45, 43, 63),
                        Arrays.asList(60, 21, 0, 94, 59, 88, 9, 54, 30, 80),
                        Arrays.asList(40, 78, 52, 58, 26, 84, 47, 0, 24, 60),
                        Arrays.asList(40, 17, 69, 5, 38, 5, 75, 59, 35, 26),
                        Arrays.asList(64, 41, 85, 22, 44, 25, 3, 63, 33, 13),
                        Arrays.asList(31, 45, 47, 100, 65, 10, 94, 96, 81, 14)
                )
        );

        System.out.println(new MinSumPathInMatrix().minPathSum(A));

    }
}
