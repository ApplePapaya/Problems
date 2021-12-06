package com.run.leetcode.arrays.maxArithmetic;
/**
 * https://www.programcreek.com/2013/02/leetcode-maximum-subarray-java/
 * @author prparasu
 *
 */
public class MaximumSumSubArray {

	public static void main(String[] args) {
		
		int arr[] = {-2,1,-3,4,-1,2,1,-5,4};
		int result = findContinguousSubArrayForMaxSum(arr);
		System.out.println("Max Sum : " + result);
 
	}

	private static int findContinguousSubArrayForMaxSum(int[] nums) {
		int result = nums[0];
		int sum[] = new int[nums.length];
		sum[0] = nums[0];
		for(int i =1;i< nums.length;i++) {
			sum[i] = Math.max(nums[i], sum[i-1] + nums[i]);
			result = Math.max(result, sum[i]);
		}
		return result;
	}
	//O(1) space
	private static int findContinguousSubArrayForMaxSumO1Space(int[] nums) {
		int result = nums[0];
		int sum = nums[0];
		for(int i =1;i< nums.length;i++) {
			sum= Math.max(nums[i], sum + nums[i]);
			result = Math.max(result, sum);
		}
		return result;
	}

}
