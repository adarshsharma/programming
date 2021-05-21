package interviewBit;

import java.util.ArrayList;

/**
 * Created by adarsh.sharma on 03/07/18.
 */
public class MergeIntervals {
    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if(intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }

        boolean inserted = false;
        for(int i=0;i<intervals.size() && !inserted;i++) {
            if(newInterval.start < intervals.get(i).start) {
                intervals.add(i, newInterval);
                inserted = true;
            }
        }
        if(!inserted) {
            intervals.add(newInterval);
        }

        return merge(intervals);
    }

    private ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end  = intervals.get(0).end;

        for(int i=1;i<intervals.size();i++) {
            if(intervals.get(i).start <= end) {
                end = Math.max(end, intervals.get(i).end);
            } else {
                result.add(new Interval(start, end));
                start = intervals.get(i).start;
                end = intervals.get(i).end;
            }
        }

        result.add(new Interval(start, end));

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Interval> input = new ArrayList<>();
        input.add(new Interval(3, 6));
        input.add(new Interval(8, 10));

        ArrayList<Interval> insert = new MergeIntervals().insert(input, new Interval(1, 2));
        System.out.println(insert);
    }
}
