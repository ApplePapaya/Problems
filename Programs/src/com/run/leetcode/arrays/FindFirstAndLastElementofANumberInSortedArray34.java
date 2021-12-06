package com.run.leetcode.arrays;
//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class FindFirstAndLastElementofANumberInSortedArray34 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = searchLeftRange(nums, target);
		result[1] = searchRightRange(nums, target);
		return result;
	}
	
	public static int searchLeftRange(int[] nums, int target) {
		int index = -1;
		int start = 0;
		int end = nums.length - 1;
		
		while(start <= end) {
			int mid = start + (end - start)/2;
			
			if(nums[mid] >= target)
				end = mid - 1;
			else
				start = mid + 1;
			
			if(nums[mid] == target)
				index = mid;
		}
		
		return index;
	}
	
	
	public static int searchRightRange(int[] nums, int target) {
		int index = -1;
		int start = 0;
		int end = nums.length - 1;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(nums[mid] <= target)
				start = mid + 1;
			else
				end = mid - 1;
			if(nums[mid] == target)
				index = mid;
		}
		return index;
	}
}
