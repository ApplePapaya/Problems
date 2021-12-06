package com.run.leetcode.intervalProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 *
 * Constraints:
 *
 *     1 <= intervals.length <= 104
 *     intervals[i].length == 2
 *     0 <= starti <= endi <= 104
 */
public class MergeIntervals56 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] result = mergeIntervals(new int [][] {{1,3}, {2,6}, {8,11},{9,15}});
	}
	//[[1,3],[2,6], [8,11]]
	public static int[][] mergeIntervals(int[][] intervals){
		
		if(intervals == null || intervals.length <=1)
			return intervals;
		Arrays.sort(intervals, (ar1, ar2) -> Integer.compare(ar1[0], ar2[0]));
		
		List<int[]> intervalList = new ArrayList<>();// Since we dont know how many intervals will be 
		//in output hence using arraylist instead of array since  weneed to define the length for array
		
		int[] currentInterval = intervals[0];
		intervalList.add(currentInterval);
		
		for(int[] interval : intervals) {
			int current_start = currentInterval[0];
			int current_end = currentInterval[1];
			int interval_start = interval[0];
			int interval_end = interval[1];
			
			if(current_end >= interval_start) {
				currentInterval[1] = Math.max(current_end, interval_end);
				
			}else {
				currentInterval = interval;
				intervalList.add(currentInterval);
			}
			
		}
		intervalList.stream()
					.forEach(i -> System.out.println(Arrays.toString(i)));
		return intervalList.toArray(new int [intervalList.size()][]);
	}
	
	
}