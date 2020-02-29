package com.run.leetcode.hardProblems;

public class JumpGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(jumpGame(new int[] {3,2,1,0,4}));
	}
	
	public static boolean jumpGame(int[] nums) {
		int lastGoodPosition = nums.length - 1;
		
		for(int i = nums.length - 1; i >= 0; i--) {
			if(i + nums[i] >= lastGoodPosition) {
				lastGoodPosition = i;
			}
		}
		return lastGoodPosition == 0;
	}

}
