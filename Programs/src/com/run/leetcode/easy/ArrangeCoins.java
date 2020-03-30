package com.run.leetcode.easy;

public class ArrangeCoins {

	public static void main(String[] args) {
		System.out.println(arrangeCoins(1804289383));

	}
	
	 public static int arrangeCoins(int n) {
	        
	        long start = 0;
	        long end = (long)n;
	        long mid = 0;
	        if(n <= 1) return n;
	        
	        while(start < end){
	            mid = start + (end - start)/2;
	            
	            long val = mid * (mid + 1)/2;
	            if(val == n)
	                return (int)mid;
	            else if (val < n)
	                start = mid + 1;
	            else
	                end = end - 1;
	        }
	        return (int)(start - 1);
	        
	    }

}
