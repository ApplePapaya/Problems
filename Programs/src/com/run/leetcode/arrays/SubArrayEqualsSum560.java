package com.run.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubArrayEqualsSum560 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(subArraySum(new int[] {1, 1, 2}, 2));

	}
	
	public static int subArraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int count = 0;
		map.put(0, 1);
		for(int i = 0; i < nums.length; i++) {
			 sum += nums[i];
			int n = map.getOrDefault(sum - k, 0);
			count += n;
			
			map.put(sum, map.getOrDefault(sum, 0)+ 1);
		}
		return count;	
	}

}
