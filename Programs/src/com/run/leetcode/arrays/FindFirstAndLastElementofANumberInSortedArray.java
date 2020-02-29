package com.run.leetcode.arrays;
//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
public class FindFirstAndLastElementofANumberInSortedArray {

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
