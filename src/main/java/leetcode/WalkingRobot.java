package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 22/07/18.
 */
public class WalkingRobot {

    public int robotSim(int[] coms, int[][] obs) {
        Map<Integer, List<Integer>> ymp = new HashMap<>();
        Map<Integer, List<Integer>> xmp = new HashMap<>();

        boolean origin = false;
        for (int i = 0; i < obs.length; i++) {
            if (obs[i][0] == 0 && obs[i][1] == 0) {
                origin = true;
            } else {
                List<Integer> xl = xmp.computeIfAbsent(obs[i][0], k -> new ArrayList<>());
                xl.add(obs[i][1]);
                List<Integer> yl = ymp.computeIfAbsent(obs[i][1], k -> new ArrayList<>());
                yl.add(obs[i][0]);
            }
        }

        int posX = 0;
        int posY = 0;
        int dx = 0; //up 0, left 1, down 2, right 3
        int[][] steps = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        xmp.values().forEach(Collections::sort);
        ymp.values().forEach(Collections::sort);

        for (int i = 0; i < coms.length; i++) {
            if (coms[i] == -2) {
                dx = (dx + 1 + 4) % 4;
            } else if (coms[i] == -1) {
                dx = (dx - 1 + 4) % 4;
            } else if (coms[i] > 0) {
                int nx = posX + steps[dx][0] * coms[i];
                int ny = posY + steps[dx][1] * coms[i];
                if (nx == posX) {
                    ny = getMax(xmp.get(nx), posY, ny);
                } else {
                    nx = getMax(ymp.get(ny), posX, nx);
                }
                posX = nx;
                posY = ny;
                if (origin) {
                    if (posX != 0 || posY != 0) {
                        origin = false;
                        List<Integer> xl = xmp.computeIfAbsent(0, k -> new ArrayList<>());
                        xl.add(0);
                        List<Integer> yl = ymp.computeIfAbsent(0, k -> new ArrayList<>());
                        yl.add(0);
                        Collections.sort(xl);
                        Collections.sort(yl);
                    }
                }
            }
        }

        return posX * posX + posY * posY;
    }

    private int getMax(List<Integer> ls, int start, int end) {
        if (end > start) {
            if (ls == null) {
                return end;
            }

            int s = 0;
            int e = ls.size() - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                if (ls.get(mid) < start) {
                    s = mid + 1;
                } else if (ls.get(mid) > start) {
                    e = mid - 1;
                }
            }

            if (s == ls.size()) {
                return end;
            } else {
                if (end >= ls.get(s)) {
                    return ls.get(s) - 1;
                }
                return end;
            }
        } else {
            if (ls == null) {
                return end;
            }

            int s = 0;
            int e = ls.size() - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                if (ls.get(mid) < start) {
                    e = mid - 1;
                } else if (ls.get(mid) > start) {
                    s = mid + 1;
                }
            }

            if (s == ls.size()) {
                return end;
            } else {
                if (end <= ls.get(s)) {
                    return ls.get(s) + 1;
                }
                return end;
            }
        }
    }

    public static void main(String[] args) {
        int[] coms = {-2, -1, -2, 3, 7};
        int[][] obs = {{1, -3}, {2, -3}, {4, 0}, {-2, 5}, {-5, 2}, {0, 0}, {4, -4}, {-2, -5}, {-1, -2}, {0, 2}};
        System.out.println(new WalkingRobot().robotSim(coms, obs));
    }
}
