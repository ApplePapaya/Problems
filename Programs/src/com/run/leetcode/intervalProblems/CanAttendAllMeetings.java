package com.run.leetcode.intervalProblems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
/***
 * 
 * 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
Example 1:
Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:
Input: [[7,10],[2,4]]
Output: true
 * @author Home-Laptop
 *
 */
public class CanAttendAllMeetings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr1 = {{0,30},{5,10},{15,20}};
		
		int[][] arr2 = {{7,10},{2,4}};
		
		System.out.println(canAttendMeetings(arr1));
		System.out.println(canAttendMeetings(arr2));
	}
	/**
	 * Time complexity is O(n) and space is also O(n)
	 * @param intervals
	 * @return
	 */
	public static boolean canAttendMeetings(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] itl : intervals) {
            map.put(itl[0], map.getOrDefault(itl[0], 0) + 1);
            map.put(itl[1], map.getOrDefault(itl[1], 0) - 1);
        }
        int room = 0; 
        for (int v : map.values()) {
            room += v; 
            if (room > 1) return false; 
        }
        return true; 
    }
	
	/**
	 * Time complexity : 
O(nlog⁡n)O(n \log n)
O(nlogn). The time complexity is dominated by sorting. Once the array has been sorted, only 
O(n)O(n)
O(n) time is taken to go through the array and determine if there is any overlap.
Space complexity : 
O(1)O(1)
O(1). Since no additional space is allocated. 
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetingsSimpleSolution(int[][] intervals) {
        Comparator<int[]> c=(int[] a, int[] b) -> (a[0]-b[0]);
        Arrays.sort(intervals, c);
        for(int i=0; i<intervals.length-1; i++)
            if(intervals[i][1]>intervals[i+1][0]) return false;
        return true;
}

}
