package com.run.leetcode.intervalProblems;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
Example 1:
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:
Input: [[7,10],[2,4]]
Output: 1
 * @author Home-Laptop
 *
 */
public class MInimumMeetingRooms {

	public static void main(String[] args) {
		int[][] arr1 = {{0,30},{5,10},{15,20}};
		
		int[][] arr2 = {{7,10},{2,4}};
		
		System.out.println(meetingRooms(arr1));
		System.out.println(meetingRooms(arr2));

	}
	
	public static int meetingRooms(int[][] intervals) {
		Map<Integer, Integer> map = new TreeMap<>();
		
		for(int[] interval : intervals)
		{
			map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
			map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
		}
		
		int room = 0;// running variable to keep adding the count
		int k = 0; //Max rooms at any point
		
		for(int v : map.values())
		{
			k = Math.max(k, room += v);
		}
		return k;
	}

}
