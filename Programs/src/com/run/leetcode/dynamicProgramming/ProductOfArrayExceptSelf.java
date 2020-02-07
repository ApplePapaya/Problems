package com.run.leetcode.dynamicProgramming;
/**
 * https://www.programcreek.com/2014/07/leetcode-product-of-array-except-self-java/
 *Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

https://github.com/varunu28/LeetCode-Java-Solutions/blob/master/Medium/Product%20of%20Array%20Except%20self.java

https://www.programcreek.com/2014/07/leetcode-product-of-array-except-self-java/

https://medium.com/algorithm-and-datastructure/the-product-of-array-except-for-self-in-o-n-and-constant-space-b29aa7ad3749
 *
 */
public class ProductOfArrayExceptSelf {

	public static void main(String[] args) {
		int arr[] = {1,2,3,4};
		int[] res = getMaxProductExceptSelf(arr);
		for(int i =0;i< arr.length;i++) {
		System.out.println(res[i]);
		}

	}

	private static int[] getMaxProductExceptSelf(int[] arr) {
		int n = arr.length;
		int result[] = new int[n];
		int[] leftProduct = new int[n];
		int[] rightProduct = new int[n];
		
		leftProduct[0] =1;// there is no element to the left of first element
		rightProduct[n-1]=1;// there is no element to the right of first element , so setting it as 1
		
		for(int i =0;i<n-1;i++) {
			leftProduct[i+1]  = arr[i] * leftProduct[i];
		}
		
		for(int i= n-1; i>0;i--)
		{
			rightProduct[i-1] = rightProduct[i] * arr[i];
		}
		
		for(int i =0;i<n;i++) {
			result[i]  = leftProduct[i] * rightProduct[i];
		}
		
		return result;
	}

	
	private static int[] getMaxProductExceptSelfLessSpace(int[] arr) {
		int n = arr.length;
		int result[] = new int[n];
		int[] rightProduct = new int[n];
		result[n-1] =1;
		for(int i = n-2;i>=0;i--) {
			result[i] = result[i+1] * arr[i+1];
		}
		int left =1;
		for(int i=0;i< n;i++) {
			result[i] = result[i] * left;
			left = left * arr[i];
		}
		return result;
	}
}
