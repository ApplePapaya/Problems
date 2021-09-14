package com.run.leetcode.intervalProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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