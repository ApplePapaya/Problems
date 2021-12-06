package com.run.leetcode.arrays.maxArithmetic;
/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
https://www.programcreek.com/2014/03/leetcode-maximum-product-subarray-java/

https://www.geeksforgeeks.org/maximum-product-subarray-set-3/

https://leetcode.com/problems/maximum-product-subarray/
 * @author prparasu
 *
 */
public class MaximumProductSubArray {

	public static void main(String[] args) {
		int arr[] = {2,3,-2,4};
		System.out.println(maxProductSubArray(arr));

	}

	private static int maxProductSubArray(int[] arr) {
		int result = arr[0];
		int maxV = arr[0];
		int minV = arr[0];
		
		for(int i =1; i< arr.length;i++)
		{
			  
            if(arr[i] <0)
            {
                int temp = maxV;
                maxV= minV;
                minV=temp;
            }
            maxV = Math.max(arr[i], maxV * arr[i]);
            minV = Math.min(arr[i] , minV * arr[i]);
			result = Math.max(result, maxV);
		}
		return result;
	}

}
