package leetcode;

import java.util.Arrays;

public class SuperWashingMachines517 {

    public int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        if (sum % machines.length != 0) {
            return -1;
        }
        int each = sum / machines.length;
        int balance = 0;
        int moves = 0;

        // for(int clothes: machines){
        //     if(clothes < each) {
        //         if(balance > 0) {
        //             moves = Math.max(moves, )
        //         } else {
        //             balance -= (each - clothes);
        //             moves = Math.max(moves, -balance);
        //         }
        //     } else if(clothes > each) {
        //
        //     } else {
        //
        //     }
        // }

        return moves;
    }

    public static void main(String[] args) {
        // int[] machines = {1, 0, 5, 3, 0, 3};
        int[] machines = {3, 1, 0, 1, 2, 5};

        System.out.println(new SuperWashingMachines517().findMinMoves(machines));
    }
}
