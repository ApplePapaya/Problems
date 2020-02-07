package com.run.leetcode;
/**
 * https://leetcode.com/contest/biweekly-contest-15/problems/element-appearing-more-than-25-in-sorted-array/
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the
 *  array that occurs more than 25% of the time.
Return that integer.

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
 

Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
 *
 */
public class ElementComingMoreThan25PercentSortedArray {

	public static void main(String[] args) {
	
		int arr[] = new int[] {1,2,2,6,6,6,6,7,10};
		System.out.println(getTheElementMoreThan25Percent(arr));
	}

	private static int getTheElementMoreThan25Percent(int[] arr) {
	 int n= arr.length;
	  int div = n/4 +1;
	  int count =1;
	  int result =1;
	  for(int i =1;i< n;i++) {
		  if(arr[i] == arr[i-1]) {
			  count++;
			  if(count >= div) {
				  result = arr[i];
				  break;
			  }
		  }else {
			  count =1;
		  }
		  
	  }
		return result;
	}

}
