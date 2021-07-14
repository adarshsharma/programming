package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DesignMovieRentingSystem1912 {

    Map<Integer, TreeSet<int[]>> availableMovieMap;
    TreeSet<int[]> rentedMovieSet;
    Map<Integer, Map<Integer, Integer>> priceMap;

    public DesignMovieRentingSystem1912(int n, int[][] entries) {
        priceMap = new HashMap<>();
        rentedMovieSet = new TreeSet<>((a, b) -> {
            if (a[2] != b[2]) {
                return a[2] - b[2];
            }

            if (a[0] != b[0]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        availableMovieMap = new HashMap<>();
        for (int[] entry : entries) {
            if (!availableMovieMap.containsKey(entry[1])) {
                availableMovieMap.put(entry[1], new TreeSet<>((a, b) -> {
                    if (a[2] != b[2]) {
                        return a[2] - b[2];
                    }
                    return a[0] - b[0];
                }));
            }

            availableMovieMap.get(entry[1]).add(new int[]{entry[0], entry[1], entry[2]});
            if (!priceMap.containsKey(entry[0])) {
                priceMap.put(entry[0], new HashMap<>());
            }
            priceMap.get(entry[0]).put(entry[1], entry[2]);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> cheapest = new ArrayList<>();
        TreeSet<int[]> shops = availableMovieMap.get(movie);
        int i = 0;

        if (shops != null) {
            Iterator<int[]> iterator = shops.iterator();
            while (iterator.hasNext() && i < 5) {
                cheapest.add(iterator.next()[0]);
                i++;
            }
        }
        return cheapest;
    }

    public void rent(int shop, int movie) {
        TreeSet<int[]> availableShops = availableMovieMap.get(movie);
        int[] entry = {shop, movie, priceMap.get(shop).get(movie)};
        availableShops.remove(entry);
        rentedMovieSet.add(entry);
    }

    public void drop(int shop, int movie) {
        int[] entry = {shop, movie, priceMap.get(shop).get(movie)};
        boolean remove = rentedMovieSet.remove(entry);
        availableMovieMap.get(movie).add(new int[]{shop, movie, priceMap.get(shop).get(movie)});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> cheapest = new ArrayList<>();
        int i = 0;

        Iterator<int[]> iterator = rentedMovieSet.iterator();
        while (iterator.hasNext() && i < 5) {
            int[] next = iterator.next();
            List<Integer> cur = new ArrayList<>();
            cur.add(next[0]);
            cur.add(next[1]);
            cheapest.add(cur);
            i++;
        }
        return cheapest;
    }

    public static void main(String[] args) {
        // int[][] entries = {{0, 1, 5}, {0, 2, 6}, {0, 3, 7}, {1, 1, 4}, {1, 2, 7}, {2, 1, 5}};
        // MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, entries);
        // System.out.println(movieRentingSystem.search(1));
        // movieRentingSystem.rent(0, 1);
        // movieRentingSystem.rent(1, 2);
        // System.out.println(movieRentingSystem.report());
        // movieRentingSystem.drop(1, 2);
        // System.out.println(movieRentingSystem.search(2));
        int[][] entries = {{0, 418, 3}, {9, 5144, 46}, {2, 8986, 29}, {6, 1446, 28}, {3, 8215, 97},
            {7, 9105, 34}, {6, 9105, 30}, {5, 1722, 94}, {9, 528, 40}, {3, 850, 77}, {3, 7069, 40},
            {8, 1997, 42}, {0, 8215, 28}, {7, 4050, 80}, {4, 7100, 97}, {4, 9686, 32},
            {4, 2566, 93}, {2, 8320, 12}, {2, 5495, 56}};
        DesignMovieRentingSystem1912 movieRentingSystem = new DesignMovieRentingSystem1912(10, entries);
        System.out.println(movieRentingSystem.search(7837));
        System.out.println(movieRentingSystem.search(5495));
        movieRentingSystem.rent(4, 7100);
        System.out.println(movieRentingSystem.search(9105));
        System.out.println(movieRentingSystem.search(1446));
        System.out.println(movieRentingSystem.report());
        System.out.println(movieRentingSystem.search(9869));
        movieRentingSystem.drop(4, 7100);

        // ["MovieRentingSystem","search","search","rent","search","search","report","search","drop"]
// [, {7837}, {5495}, {4,7100}, {9105}, {1446}, {}, {9869}, {4,7100]]

    }

}
