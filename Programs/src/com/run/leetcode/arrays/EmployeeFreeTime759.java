package com.run.leetcode.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= schedule.length , schedule[i].length <= 50
 *     0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTime759 {

//7 ms better runt iem
    public List<Interval> employeeFreeTimeUsingList(List<List<Interval>> schedule) {
        List<Interval> freeSlots = new ArrayList<>();
        List<Interval> allIntervals = new ArrayList<>();
        for(List<Interval> intervals: schedule){
            allIntervals.addAll(intervals);
        }
        Collections.sort(allIntervals, (o1, o2)-> o1.start - o2.start);
        Interval currentInterval = allIntervals.get(0);
        for(Interval interval: allIntervals){
            if(currentInterval.end < interval.start){
                freeSlots.add(new Interval(currentInterval.end, interval.start));
                currentInterval = interval;
            }else{
                currentInterval = currentInterval.end < interval.end? interval: currentInterval;
            }
        }
        return freeSlots;
    }


    // Using Priority Queue

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<int[]> allEmployees = new PriorityQueue<>((a, b) -> schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);

        for (int i = 0; i < schedule.size(); i++) {
            allEmployees.add(new int[] { i, 0 });
        }
        List<Interval> employeesFreeTime = new ArrayList<>();

        int[] prev = allEmployees.peek();

        while (!allEmployees.isEmpty()) {
            int[] current = allEmployees.poll();

            Interval previousInterval = schedule.get(prev[0]).get(prev[1]);
            Interval currentInterval = schedule.get(current[0]).get(current[1]);

            if (currentInterval.start > previousInterval.end) {
                employeesFreeTime.add(new Interval(previousInterval.end, currentInterval.start));
            }

            if (currentInterval.end > previousInterval.end) {
                prev = current;
            }

            if (schedule.get(current[0]).size() > current[1] + 1) {
                allEmployees.add(new int[] { current[0], current[1] + 1 });
            }
        }

        return employeesFreeTime;
    }
}
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
