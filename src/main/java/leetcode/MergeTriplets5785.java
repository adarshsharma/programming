package leetcode;

public class MergeTriplets5785 {

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int done = 0;
        int i = 0;
        boolean[] idx = new boolean[3];

        while (done < 3 && i < triplets.length) {
            int[] triplet = triplets[i];

            if (target[0] >= triplet[0] && target[1] >= triplet[1] && target[2] >= triplet[2]) {
                for (int j = 0; j < 3; j++) {
                    if (!idx[j] && triplet[j] == target[j]) {
                        idx[j] = true;
                        done++;
                    }
                }
            }
            i++;
        }

        return done == 3;
    }

    public static void main(String[] args) {
        int[][] triplets = {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}};
        int[] target = {2, 7, 5};
        System.out.println(new MergeTriplets5785().mergeTriplets(triplets, target));
    }

    }
