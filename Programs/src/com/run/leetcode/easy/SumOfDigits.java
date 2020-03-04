package com.run.leetcode.easy;

public class SumOfDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * Two approaches to solve this problem either do recursive call to the method 
	 * which does mod and division until the num is less than 10.
	 * 
	 * Math formula : if num % 9 == 0 then ans is 9 else its num % 9 if num is greater than 0
	 * @param num
	 * @return
	 */
	public static int sumOfDigits(int num ) {
		 int sum = 0;
			
			while(num > 0 || (sum > 9 )) {
				
				if(num == 0) {
					num = sum; 	
	                sum = 0;
				}
				sum += num % 10;
				num = num / 10;
				
			}
			return sum;
	        
	    
	}
	
	/**
	Solution 1

	 public int addDigits(int num) {
	        
	        int sum = 0;
	        if(num < 10) return num;
	        
	        while(num > 0)
	        {
	            sum += num % 10;
	            num = num / 10;
	        }
	        
	        return addDigits(sum);
	        
	    }
	    
	    
	    
	    Solution 2
	    
	       return 1 + (num - 1) % 9;
	       
	       
	     Solution 3 :
	     
	      if(num == 0 || num < 10) return num;
	        
	        return num % 9 == 0 ? 9 : num % 9;
	     
	**/

}
