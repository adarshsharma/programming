package leetcode;

public class MinimumSkipsToArriveAtMeeting1883 {

    static class TimeTaken {

        int full;
        int partial;    // this should be divided by speed;

        public boolean isLess(TimeTaken that) {
            if (this.full != that.full) {
                return (this.full - that.full) < 0;
            }
            return (this.partial - that.partial) < 0;
        }

        public boolean isLte(int hoursBefore) {
            if (this.full > hoursBefore) {
                return false;
            }
            if (this.full < hoursBefore) {
                return true;
            }
            return this.partial == 0;
        }
    }

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        TimeTaken[] prev = new TimeTaken[dist.length];

        for (int s = 0; s < dist.length; s++) {
            TimeTaken[] cur = new TimeTaken[dist.length];
            for (int r = 0; r < dist.length; r++) {
                if (r == 0) {
                    cur[r] = getTime(dist[0], speed);
                } else {
                    if (s == 0) {
                        cur[r] = addWithoutSkip(cur[r - 1], getTime(dist[r], speed));
                    } else {
                        TimeTaken timeTaken = getTime(dist[r], speed);
                        cur[r] = addWithoutSkip(cur[r - 1], timeTaken);
                        TimeTaken withRest = addWithSkip(prev[r - 1], timeTaken, speed);
                        if (withRest.isLess(cur[r])) {
                            cur[r] = withRest;
                        }
                    }
                }
            }
            if (cur[dist.length - 1].isLte(hoursBefore)) {
                return s;
            }

            prev = cur;
        }

        return -1;
    }

    private TimeTaken addWithoutSkip(TimeTaken first, TimeTaken second) {
        TimeTaken timeTaken = new TimeTaken();
        timeTaken.full = first.full + second.full;
        if (first.partial > 0) {
            timeTaken.full++;
        }
        timeTaken.partial = second.partial;

        return timeTaken;
    }

    private TimeTaken addWithSkip(TimeTaken first, TimeTaken second, int speed) {
        TimeTaken timeTaken = new TimeTaken();
        timeTaken.full = first.full + second.full;
        timeTaken.partial = first.partial + second.partial;
        timeTaken.full += timeTaken.partial / speed;
        timeTaken.partial = timeTaken.partial % speed;

        return timeTaken;
    }

    private TimeTaken getTime(int d, int s) {
        TimeTaken timeTaken = new TimeTaken();
        timeTaken.full = d / s;
        timeTaken.partial = d % s;
        return timeTaken;
    }

    public static void main(String[] args) {
        int[] dist = {1,3,2};
        int speed = 4;
        int hoursBefore = 2;
        System.out
            .println(new MinimumSkipsToArriveAtMeeting1883().minSkips(dist, speed, hoursBefore));
    }

}
