package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import  java.util.Arrays;

public class MeetingRoomsIII2402 {

  public int mostBooked(int n, int[][] meetings) {
    PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
    Map<Integer, Integer> meetingCount = new HashMap<>();
    PriorityQueue<Integer[]> occupiedRooms = new PriorityQueue<>(
        Comparator.comparing(meeting -> meeting[0]));

    for (int i = 0; i < n; i++) {
      availableRooms.offer(i);
    }
    Arrays.sort(meetings, Comparator.comparing(meeting -> meeting[0]));

    int currentTime = 0;
    int i = 0;
    while(i < meetings.length) {
      currentTime = meetings[i][0];
      while(!occupiedRooms.isEmpty() && (occupiedRooms.peek()[0] <= currentTime)) {
          Integer[] completed = occupiedRooms.poll();
          availableRooms.offer(completed[1]);
      }

      if(availableRooms.isEmpty()) {
        if(!occupiedRooms.isEmpty()) {
          Integer[] completed = occupiedRooms.poll();
          currentTime = completed[0];
          availableRooms.offer(completed[1]);
          while(!occupiedRooms.isEmpty() && occupiedRooms.peek()[0] <= currentTime) {
            completed = occupiedRooms.poll();
            availableRooms.offer(completed[1]);
          }
        }
      }

      if(!availableRooms.isEmpty()) {
        Integer[] assign = new Integer[2];
        assign[0] = currentTime + (meetings[i][1] - meetings[i][0]);
        assign[1] = availableRooms.poll();
        occupiedRooms.offer(assign);
        meetingCount.merge(assign[1], 1, Integer::sum);
        i++;
      }
    }

    int max = 0;
    for (Integer key : meetingCount.keySet()) {
      if (meetingCount.get(key) > meetingCount.get(max)) {
        max = key;
      }
    }

    return max;
  }

  public static void main(String[] args){
    int n = 3;
    // int[][] meetings = {{0,10},{1,5},{2,7},{3,4}};
    int[][] meetings = {{1,20},{2,10},{3,5},{4,9},{6,8}};
    System.out.println(new MeetingRoomsIII2402().mostBooked(n, meetings));
  }

}
