package com.run.leetcode.slidingWindow;
/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * 
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 

Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1 
 */
public class MaxConsecutiveOnes {

	public static void main (String[] args)
	{
		int[] arr = new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
		int k =3;
		System.out.println(maxConsecutiveOnes(arr, k));
	}

	private static int maxConsecutiveOnes(int[] arr, int k) {
		int maxOnes =0;
		for(int l =0,r =0;r< arr.length;r++) {
			
			
			
		}
		
		return maxOnes;
	}
	
}
