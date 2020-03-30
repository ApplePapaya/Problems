package com.run.leetcode.medium;

import java.util.Arrays;
/**
 * For some fixed N, an array A is beautiful if it is a permutation of the integers 1, 2, ..., N, such that:

For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] + A[j].

Given N, return any beautiful array A.  (It is guaranteed that one exists.)

 

Example 1:

Input: 4
Output: [2,1,4,3]
Example 2:

Input: 5
Output: [3,1,2,5,4]
 * 
 * **/
public class BeautifulArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(beautifulArray(5)));
	}
	
	 public static int[] beautifulArray(int N) {
	        int ret[] = new int[N];
	        for (int i = 0; i < ret.length; i++) {
	            int index = 0;
	            int off = N;
	            for (int n = i; n > 0; n >>= 1) {
	                int obit = (off & 1);
	                off = (off + 1) / 2;
	                if ((n & 1) != 0) {
	                    index += off;
	                    off -= obit;
	                }
	            }
	            ret[index] = i + 1;
	        }
	        return ret;
	    }

}
