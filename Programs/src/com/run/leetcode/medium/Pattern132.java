package com.run.leetcode.medium;

import java.util.Stack;

public class Pattern132 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(find132pattern(new int[] {1,2,4,6,5,8}));
	}
	
	 public static boolean find132pattern(int[] nums) {
	        Stack<Integer> stack = new Stack<>();
	        int max = Integer.MIN_VALUE;
	        for (int i = nums.length - 1; i >= 0; i--) {
	        	int val = nums[i];
	            while (!stack.isEmpty() && stack.peek() < nums[i]) {
	            	int peekStack = stack.peek();
	                max = stack.pop();
	            }
	            if (nums[i] > max) stack.push(nums[i]);
	            if (nums[i] < max) return true;
	        }
	        return false;
	    }

}
