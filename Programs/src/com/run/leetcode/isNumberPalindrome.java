package com.run.leetcode;
//check if the number is palindrome or not
public class isNumberPalindrome {

	public static void main(String[] args) {
		System.out.println(isNumberPalindrome(100));
		System.out.println(isNumberPalindrome(545));
		System.out.println(isNumberPalindrome(678876));
		System.out.println(isNumberPalindrome(0));
		

	}
	
	public static boolean isNumberPalindrome(int val) {
		//Negative number is not a palindrome.
		//Number is less than 0 or number is not zero but ends in 0 . verified if %10==0
		if(val<0 || (val!=0 && val%10 == 0)) {
			return false;
		}
		int rev =0;
		// We dont need to completely swap the val to verify the palindrome.
		//Half way through if the values are equal or differ by 1 integer (odd digits). then its
		//palindrome.
		while(rev < val) {
			int mod = val%10;
			rev = rev * 10 +mod;
			val = val/10;
		}
		
		return (val == rev || val == rev/10);
		
	}

}
