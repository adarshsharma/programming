package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by adarsh.sharma on 28/07/18.
 */
public class CourseScheduleIII {
    public int scheduleCourse(int[][] courses) {
        if(courses.length == 0) {
            return 0;
        }
        Arrays.sort(courses, (entry1, entry2) -> {
            if (entry1[1] == entry2[1])
                return entry1[0] - entry2[0];
            return entry1[1] - entry2[1];
        });

        ArrayList<Integer> v = new ArrayList<>();
        for(int i=0;i<courses.length;i++) {
            if(courses[i][0] <= courses[i][1]) {
                if(v.size()==0) {
                    v.add(courses[i][0]);
                } else {
                    int j=v.size()-1;
                    if(courses[i][1] >= v.get(j) + courses[i][0]) {
                        v.add(v.get(j) + courses[i][0]);
                    }

                    for(;j>0;j--) {
                        if(courses[i][1] >= v.get(j-1) + courses[i][0] && v.get(j-1) + courses[i][0] < v.get(j)) {
                            v.set(j, v.get(j-1) + courses[i][0]);
                        }
                    }

                    if(v.get(0) > courses[i][0]) {
                        v.set(0, courses[i][0]);
                    }
                }
            }
        }
//        v.forEach(System.out::println);
        return v.size();
    }

    public static void main(String[] args) {
        int[][] courses = {{1,19},{2,2},{1,17}};
        System.out.println(new CourseScheduleIII().scheduleCourse(courses));
    }
}
