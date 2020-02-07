package com.run.leetcode.dynamicProgramming;
//https://www.geeksforgeeks.org/find-missing-number-arithmetic-progression/
/**
 * Given an array that represents elements of arithmetic progression in order. One element is missing in the progression, find the missing number.
Examples:

Input: arr[]  = {2, 4, 8, 10, 12, 14}
Output: 6

Input: arr[]  = {1, 6, 11, 16, 21, 31};
Output: 26
The exectation is first or last number cannot be missing .
Also there is exactly one element missing int he arry .Else incorrect ouput.
 *
 */
public class MissingNumberInArithmeticProgression {

	public static void main(String[] args) {
		 int arr[] = {2, 6, 8, 10, 12, 14,16,18}; 
		    int n = arr.length; 
		    System.out.println("The missing element is Recursive "+    
		                            findMissing(arr, n)); 
		    System.out.println("The missing element is Iterative "+    
                    findMissingIterative(arr, n)); 

	}

	// The function uses findMissingUtil()  to find the missing element in AP. It assumes that there is exactly one missing element and may give  
	// incorrect result when there is no   missing element or more than one missing elements. This function also  
	// assumes that the difference in AP is  an integer. 
	
	private static int  findMissing(int[] arr, int n) {
		int missingNum=0;
		
		   // If exactly one element is missing,   then we can find difference of arithmetic progression using following formula. Example, 2, 4,  
	    // 6, 10, diff = (10-2)/4 = 2.  The assumption in formula is that the difference is an integer.
		int diff = (arr[n-1] -arr[0])/n;
		// Binary search for the missing number using above calculated diff 
		missingNum = findMissingNumUtility(0, n-1, arr ,diff);
		 
		return missingNum;
	}

	/**
	 * A binary search based recursive function that returns the missing element in arithmetic progression.
	 */
	private static int findMissingNumUtility(int low,  int high, int[] arr, int diff) {
		//There must be 2 elements to find the missing number.
		if(high<=low)
			return  Integer.MAX_VALUE;
		//Find the index of the middle element.
		int mid =low+ (high-low)/2;
		
		 // The element just after the  middle element is missing. The arr[mid+1] must exist, because we return when 
	    // (low == high) and take  floor of (high-low)/2 
	    if (arr[mid + 1] - arr[mid] != diff) 
	        return (arr[mid] + diff); 
	  
	    // The element just  before mid is missing 
	    if (mid > 0 && arr[mid] -  arr[mid - 1] != diff) 
	        return (arr[mid - 1] + diff); 
	    
	    if(arr[mid] == arr[0] + mid * diff)
	    		return findMissingNumUtility(mid+1, high, arr, diff);
		
		
	    return findMissingNumUtility(low, mid-1, arr, diff);
	}
	
	private static int  findMissingIterative(int[] arr, int n) {
		
		   // If exactly one element is missing,   then we can find difference of arithmetic progression using following formula. Example, 2, 4,  
	    // 6, 10, diff = (10-2)/4 = 2.  The assumption in formula is that the difference is an integer.
		int diff = (arr[n-1] -arr[0])/n;
		int high = n-1;
		int low =0;
		while(low<=high) {
			int mid =low+ (high-low)/2;
			 // The element just after the  middle element is missing. The arr[mid+1] must exist, because we return when 
		    // (low == high) and take  floor of (high-low)/2 
		    if (arr[mid + 1] - arr[mid] != diff) 
		        return (arr[mid] + diff); 
		  
		    // The element just  before mid is missing 
		    if (mid > 0 && arr[mid] -  arr[mid - 1] != diff) 
		        return (arr[mid - 1] + diff); 
		    
		    if(arr[mid] == arr[0] + mid * diff)
		    	low = mid+1;
		    else
		    	high= mid-1;
		    
		}
		 
		return Integer.MAX_VALUE;
	}

}
