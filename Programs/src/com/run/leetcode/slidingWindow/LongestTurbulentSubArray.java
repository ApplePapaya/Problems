package com.run.leetcode.slidingWindow;
/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.
Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
Example 2:

Input: [4,8,12,16]
Output: 2
Example 3:

Input: [100]
Output: 1
 *
 */
public class LongestTurbulentSubArray {

	public static void main(String[] args) {
	 int arr[] = {9,4,2,10,7,8,8,1,9};
	 System.out.println(longestTurbulentArraySoln1(arr));
	}

	private static int longestTurbulentArraySoln1(int[] arr) {
		int longestTurb =1;
		int n = arr.length;
		int anchor =0;
		for(int i =1; i< n ;++i) {
			int c = Integer.compare(arr[i-1], arr[i]);// This order is very important else it will give -1 instead of 1 or vice versa.
			//causing the below comparisong in else block to go wrong
			int p = Integer.compare(arr[i], arr[i-1]);// This should not be used is incorrect for below
			if(c==0) // if adjacent numbers are equal,then change the anchor to i
			 anchor =i;
			else if (i== n-1 || c * Integer.compare(arr[i], arr[i+1]) != -1 )//Either we reached end of array or the next element is not satisfying the math.
			{
				longestTurb = Math.max(longestTurb, i-anchor +1 );
				anchor =i;
			}
		}
		return longestTurb;
	}

	private static int longestTurbulentArraySoln2(int[] arr) {
		int longestTurb = 1;
		int inc=1, dec =1;
		for(int i =1;i< arr.length;i++) {
			
			if(arr[i] > arr[i-1]) {
				inc = dec +1;
				dec=1;
			}else if (arr[i] < arr[i-1]) {
				dec = inc +1;
				inc =1;
			}else {
				inc=1;
				dec=1;
			}
			longestTurb = Math.max(longestTurb, Math.max(dec, inc));
		}
		return longestTurb;
	}
	
}
