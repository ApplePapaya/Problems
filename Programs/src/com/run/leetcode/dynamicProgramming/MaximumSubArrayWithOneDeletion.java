package com.run.leetcode.dynamicProgramming;

public class MaximumSubArrayWithOneDeletion {

	public static void main(String[] args) {
		System.out.println(maxSubArrayWithOneDeletion(new int[]{1,-2,0,3}));
		System.out.println(maxSubArrayWithOneDeletion(new int[]{1,-2,-2,3}));
		System.out.println(maxSubArrayWithOneDeletion(new int[]{-1,-1,-1,-1}));
		System.out.println(maxSubArrayWithOneDeletion(new int[]{-1,-1,-1,-1}));

	}
	
	public static int maxSubArrayWithOneDeletion(int[] arr) {
		int n= arr.length;
		int left[] = new int[n];
		int right[] = new int[n];
		
		int result = arr[0];
		left[0] = arr[0];
		right[n-1] = arr[n-1];
		
		for(int i = 1;i<n ;i++ ) {
			left[i] = Math.max(left[i-1]+ arr[i], arr[i]);
			result = Math.max(left[i], result);
		}
		
		for(int i =n-2;i>=0;i--) {
			right[i]  = Math.max(right[i+1] + arr[i] , arr[i]);
		}
		
		for(int i=1;i<n-1;i++) {
			result = Math.max(left[i-1]+right[i+1], result);
		}
		return result;
	}

}
