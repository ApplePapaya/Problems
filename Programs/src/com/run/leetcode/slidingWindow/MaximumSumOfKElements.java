package com.run.leetcode.slidingWindow;
/**
 * Given an array of integers and a number k, find maximum sum of a subarray of size k.
Input  : arr[] = {100, 200, 300, 400}
         k = 2
Output : 700

Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
         k = 4 
Output : 39
We get maximum sum by adding subarray {4, 2, 10, 23}
of size 4.

Input  : arr[] = {2, 3}
         k = 3
Output : Invalid
There is no subarray of size 3 as size of whole
array is 2.
 *
 */
public class MaximumSumOfKElements {
	
	static int sumBruteForce(int[] arr , int n ,int k) {
		int maxSum=0;
		if(n< k)
		{
			return -1;
		}
		
		for(int i =0;i < n-k+1;i++) {
			int sum =0;
			for(int j =0;j<k ;j++) {
				sum += arr[i+j];
				
			}
			maxSum= Math.max(maxSum, sum);
		}
		return maxSum;
	}
	
	static int windowSliding(int[] arr , int n , int k) {
		int maxSum=0;
		int windowSum = 0;
		// Compute the first window sum
		for(int i =0; i<k;i++) {
			windowSum += arr[i];
		}
		maxSum = windowSum;
		
		for(int i =k; i< n; i++ ) {
			windowSum += arr[i] - arr[i-k];
			maxSum = Math.max(maxSum, windowSum);
		}
		
		return maxSum;
	}
	
	
	
	
	 public static void main(String[] args) 
	    { 
	        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 }; 
	        int k = 4; 
	        int n = arr.length; 
	        System.out.println(sumBruteForce(arr, n, k));
	        System.out.println(windowSliding(arr, n, k)); 
	    }


}
