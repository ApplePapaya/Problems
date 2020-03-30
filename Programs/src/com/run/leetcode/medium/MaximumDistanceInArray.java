package com.run.leetcode.medium;
/***
 * 
 * 
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
 * https://leetcode.com/articles/maximum-distance-in-array/
 *
 */
public class MaximumDistanceInArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] input = {{1, 2, 3},
							{4, 5,9},
							{1, 2, 3}};
		System.out.println(maxDistance(input));
	}
	
	public static int maxDistance(int[][] list) {
		int res = 0;
		int minValue = list[0][0];
		int maxValue = list[0][list[0].length - 1];
		
		for(int i = 1; i < list.length; i++) {
			int diff1 = Math.abs(list[i][list[i].length - 1] - minValue);
			int diff2 = Math.abs(maxValue - list[i][0]);
			res = Math.max(res, Math.max(diff1, diff2));
		}
		
		return res;
	}

}
