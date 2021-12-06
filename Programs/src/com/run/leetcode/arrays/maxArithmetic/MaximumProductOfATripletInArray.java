package com.run.leetcode.arrays.maxArithmetic;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/find-maximum-product-of-a-triplet-in-array/
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24
 *
 */
public class MaximumProductOfATripletInArray {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		int[] arr2 = {-2,-1,0,1,25,7,3,1};
		int[] arr3 = {};
		int[] arr4 = {0,5,3,-4,-7};
		int max = maxProductOfTriplet(arr4);
		System.out.println("Max product of triplet : " + max);
	}

	/**
	 * tIME = O(nlogn)
	 * 
	 * @param arr
	 * @return
	 */
	private static int maxProductOfTriplet(int[] arr) {
		Arrays.sort(arr);
		int n = arr.length;
		if(n < 3)
				return -1;
		
			return Math.max(arr[0] * arr[1] * arr[n-1], arr[n-1] * arr[n-2] * arr[n-3]);
		
	}
	/**
	 * Below method uses single scan to get the 2 min and 3 max values.
	 * @param arr
	 * @return
	 */
	private static int maxProductOfTripletOption2(int[] arr) {
		int n = arr.length;
		int min1 = Integer.MAX_VALUE , min2 = Integer.MAX_VALUE;
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE , max3 = Integer.MIN_VALUE;
		
		for(int i =0;i<n ; i++) {
			//Should check greater than or equal to as well. Since 2 same big numbers needs to be considered.
			if(arr[i] >=max1)
			{
				max3= max2;
				max2=max1;
				max1=arr[i];
			}else if(arr[i] >=max2) {
				max3= max2;
				max2= arr[i];
			}else if(arr[i] >= max3) {
				max3=arr[i];
			}
			
			if(arr[i] <=  min1)
			{
				min2 = min1;
				min1 = arr[i];
			}else if(arr[i] <= min2)
			{
				min2 = arr[i];
			}
		}
				
			return Math.max(min1 * min2 * max1, max1 * max2 * max3);
		
	}

}
