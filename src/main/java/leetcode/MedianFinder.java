package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by adarsh.sharma on 12/09/18.
 */
public class MedianFinder {
    PriorityQueue<Integer> maxQ;
    PriorityQueue<Integer> minQ;

    public MedianFinder() {
        maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        minQ = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxQ.add(num);
        minQ.add(maxQ.poll());
        if(maxQ.size() < minQ.size()) {
            maxQ.add(minQ.poll());
        }
    }

    public double findMedian() {
        if(maxQ.size() == minQ.size() && maxQ.size()==0) {
            return 0.0;
        } else {
            return maxQ.size()>minQ.size()?maxQ.peek():((double)maxQ.peek() + (double)minQ.peek())/2;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(41);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(35);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(62);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(97);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(108);
        System.out.println(medianFinder.findMedian());
    }
}
