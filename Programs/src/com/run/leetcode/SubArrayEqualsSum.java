package com.run.leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubArrayEqualsSum {

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
