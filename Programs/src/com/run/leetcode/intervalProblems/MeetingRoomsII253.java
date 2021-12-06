package com.run.leetcode.intervalProblems;

import java.util.*;

/**
 * 253bpaint
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
Example 1:
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:
Input: [[7,10],[2,4]]
Output: 1
 * @author Home-Laptop
 *0 5 15
 * 10 20 30
 */
public class MeetingRoomsII253 {

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
	public int minMeetingRooms2(int[][] intervals) {
		Arrays.sort(intervals, (a, b)->(a[0]-b[0])); // sort by start times
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]) );
		Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[1]-b[1])); // sort by end times
		int maxsize = 0;
		for (int[] interval : intervals) {
			if (!pq.isEmpty() && pq.peek()[1] <= interval[0]) {
				pq.poll();
			}
			pq.add(interval);
			if(pq.size() > maxsize) maxsize = pq.size();
		}
		return maxsize;
	}

	public int minMeetingRooms5(int[][] intervals) {
		if(intervals == null || intervals.length == 0) return 0;
		Arrays.sort(intervals,(a,b)->a[0]-b[0]);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
		pq.add(intervals[0]);
		for(int i=1; i<intervals.length; i++) {
			if(intervals[i][0] >= pq.peek()[1]) {
				pq.poll();
			}
			pq.offer(intervals[i]);
		}
		return pq.size();
	}

	//Besttime performance in leet code 1-3 ms
	public int minMeetingRooms(int[][] intervals) {
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for(int i=0; i<intervals.length; i++) {
			starts[i] = intervals[i][0];
			ends[i] = intervals[i][1];
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int rooms = 0;
		int endsItr = 0;
		for(int i=0; i<starts.length; i++) {
			if(starts[i]<ends[endsItr])
				rooms++;
			else
				endsItr++;
		}
		return rooms;
	}

}
